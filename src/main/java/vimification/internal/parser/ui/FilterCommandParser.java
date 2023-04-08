package vimification.internal.parser.ui;

import java.util.function.Consumer;

import vimification.internal.command.ui.FilterCommand;
import vimification.internal.command.ui.FilterRequest;
import vimification.internal.parser.ApplicativeParser;
import vimification.internal.parser.ArgumentCounter;
import vimification.internal.parser.CommandParser;
import vimification.internal.parser.CommandParserUtil;
import vimification.internal.parser.ComposedArgumentFlag;
import vimification.internal.parser.LiteralArgumentFlag;
import vimification.internal.parser.Pair;
import vimification.internal.parser.ParserException;

/**
 * The parser that can parses and creates new {@link FilterCommand}.
 */
public class FilterCommandParser implements CommandParser<FilterCommand> {

    private static final ApplicativeParser<ApplicativeParser<FilterCommand>> INTERNAL_PARSER =
            ApplicativeParser
                    .string("f") // filter
                    .constMap(FilterCommandParser::parseArguments);

    private static ApplicativeParser<FilterCommand> parseArguments() {
        FilterRequest request = new FilterRequest();
        ArgumentCounter counter = new ArgumentCounter(
                Pair.of(CommandParserUtil.KEYWORD_FLAG, 1),
                Pair.of(CommandParserUtil.STATUS_FLAG, 1),
                Pair.of(CommandParserUtil.LABEL_FLAG, Integer.MAX_VALUE),
                Pair.of(CommandParserUtil.PRIORITY_FLAG, 1),
                Pair.of(CommandParserUtil.BEFORE_FLAG, 1),
                Pair.of(CommandParserUtil.AFTER_FLAG, 1),
                Pair.of(CommandParserUtil.FILTER_FLAG, 1));

        Consumer<ComposedArgumentFlag> filterFlagConsumer = flag -> {
            counter.add(flag);
            LiteralArgumentFlag literalFlag = flag.getActualFlag();
            if (literalFlag.equals(CommandParserUtil.AND_FLAG)) {
                request.setMode(FilterRequest.Mode.AND);
                return;
            }
            if (literalFlag.equals(CommandParserUtil.OR_FLAG)) {
                request.setMode(FilterRequest.Mode.OR);
                return;
            }
            throw new ParserException("Should not reach here!");
        };

        ApplicativeParser<Void> flagParser = ApplicativeParser.choice(
                CommandParserUtil.KEYWORD_FLAG_PARSER
                        .consume(counter::add)
                        .takeNext(ApplicativeParser.skipWhitespaces1())
                        .takeNext(CommandParserUtil.STRING_PARSER)
                        .consume(request::setSearchedKeyword),
                CommandParserUtil.STATUS_FLAG_PARSER
                        .consume(counter::add)
                        .takeNext(ApplicativeParser.skipWhitespaces1())
                        .takeNext(CommandParserUtil.STATUS_PARSER)
                        .consume(request::setSearchedStatus),
                CommandParserUtil.LABEL_FLAG_PARSER
                        .consume(counter::add)
                        .takeNext(ApplicativeParser.skipWhitespaces1())
                        .takeNext(CommandParserUtil.LABEL_PARSER)
                        .consume(label -> request.getSearchedLabels().add(label)),
                CommandParserUtil.PRIORITY_FLAG_PARSER
                        .consume(counter::add)
                        .takeNext(ApplicativeParser.skipWhitespaces1())
                        .takeNext(CommandParserUtil.PRIORITY_PARSER)
                        .consume(request::setSearchedPriority),
                CommandParserUtil.BEFORE_FLAG_PARSER
                        .consume(counter::add)
                        .takeNext(ApplicativeParser.skipWhitespaces1())
                        .takeNext(CommandParserUtil.DATE_TIME_PARSER)
                        .consume(request::setSearchedDeadlineBefore),
                CommandParserUtil.AFTER_FLAG_PARSER
                        .consume(counter::add)
                        .takeNext(ApplicativeParser.skipWhitespaces1())
                        .takeNext(CommandParserUtil.DATE_TIME_PARSER)
                        .consume(request::setSearchedDeadlineAfter),
                CommandParserUtil.FILTER_FLAG_PARSER
                        .consume(filterFlagConsumer));

        return ApplicativeParser
                .skipWhitespaces1()
                .takeNext(flagParser.sepBy1(ApplicativeParser.skipWhitespaces1()))
                .filter(ignore1 -> !request.getMode().equals(FilterRequest.Mode.DEFAULT)
                        || counter.totalCount() == 1)
                .constMap(new FilterCommand(request))
                .dropNext(CommandParserUtil.END_OF_COMMAND_PARSER);
    }

    private static final FilterCommandParser INSTANCE = new FilterCommandParser();

    private FilterCommandParser() {}

    public static FilterCommandParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ApplicativeParser<ApplicativeParser<FilterCommand>> getInternalParser() {
        return INTERNAL_PARSER;
    }
}
