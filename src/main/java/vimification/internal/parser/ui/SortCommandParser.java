package vimification.internal.parser.ui;

import java.util.function.Consumer;

import vimification.internal.command.ui.SortCommand;
import vimification.internal.command.ui.SortRequest;
import vimification.internal.parser.ApplicativeParser;
import vimification.internal.parser.ArgumentCounter;
import vimification.internal.parser.CommandParser;
import vimification.internal.parser.CommandParserUtil;
import vimification.internal.parser.ComposedArgumentFlag;
import vimification.internal.parser.LiteralArgumentFlag;
import vimification.internal.parser.Pair;
import vimification.internal.parser.ParserException;

/**
 * The parser that can parses and creates new {@link SortCommand}.
 */
public class SortCommandParser implements CommandParser<SortCommand> {

    private static final ApplicativeParser<ApplicativeParser<SortCommand>> INTERNAL_PARSER =
            ApplicativeParser
                    .string("s") // sort
                    .constMap(SortCommandParser::parseArguments);

    private static final SortCommandParser INSTANCE = new SortCommandParser();

    private SortCommandParser() {}

    private static ApplicativeParser<SortCommand> parseArguments() {
        SortRequest request = new SortRequest();
        ArgumentCounter counter = new ArgumentCounter(Pair.of(CommandParserUtil.SORT_FLAG, 1));

        Consumer<ComposedArgumentFlag> sortFlagConsumer = flag -> {
            counter.add(flag);
            LiteralArgumentFlag actualFlag = flag.getActualFlag();
            if (actualFlag.equals(CommandParserUtil.DEADLINE_FLAG)) {
                request.setMode(SortRequest.Mode.DEADLINE);
                return;
            }
            if (actualFlag.equals(CommandParserUtil.PRIORITY_FLAG)) {
                request.setMode(SortRequest.Mode.PRIORITY);
                return;
            }
            if (actualFlag.equals(CommandParserUtil.STATUS_FLAG)) {
                request.setMode(SortRequest.Mode.STATUS);
                return;
            }
            throw new ParserException("Should not reach here!");
        };

        return ApplicativeParser
                .skipWhitespaces1()
                .takeNext(CommandParserUtil.SORT_FLAG_PARSER.consume(sortFlagConsumer))
                .constMap(new SortCommand(request))
                .dropNext(CommandParserUtil.END_OF_COMMAND_PARSER);
    }

    public static SortCommandParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ApplicativeParser<ApplicativeParser<SortCommand>> getInternalParser() {
        return INTERNAL_PARSER;
    }
}
