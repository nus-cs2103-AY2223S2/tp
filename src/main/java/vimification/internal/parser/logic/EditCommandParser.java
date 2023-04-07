package vimification.internal.parser.logic;

import vimification.common.core.Index;
import vimification.internal.command.logic.EditCommand;
import vimification.internal.command.logic.EditRequest;
import vimification.internal.parser.ApplicativeParser;
import vimification.internal.parser.ArgumentCounter;
import vimification.internal.parser.CommandParser;
import vimification.internal.parser.CommandParserUtil;
import vimification.internal.parser.Pair;

/**
 * The parser that can parses and creates new {@link EditCommand}.
 */
public class EditCommandParser implements CommandParser<EditCommand> {

    private static final ApplicativeParser<EditCommand> COMMAND_PARSER =
            CommandParserUtil.ONE_BASED_INDEX_PARSER
                    .flatMap(EditCommandParser::parseArguments)
                    .dropNext(CommandParserUtil.END_OF_COMMAND_PARSER);

    private static final ApplicativeParser<ApplicativeParser<EditCommand>> INTERNAL_PARSER =
            ApplicativeParser
                    .string("e")
                    .takeNext(ApplicativeParser.skipWhitespaces1())
                    .constMap(COMMAND_PARSER);

    private static final EditCommandParser INSTANCE = new EditCommandParser();

    private EditCommandParser() {}

    private static ApplicativeParser<EditCommand> parseArguments(Index index) {
        EditRequest request = new EditRequest();
        ArgumentCounter counter = new ArgumentCounter(
                Pair.of(CommandParserUtil.TITLE_FLAG, 1),
                Pair.of(CommandParserUtil.LABEL_FLAG, Integer.MAX_VALUE),
                Pair.of(CommandParserUtil.STATUS_FLAG, 1),
                Pair.of(CommandParserUtil.PRIORITY_FLAG, 1),
                Pair.of(CommandParserUtil.DEADLINE_FLAG, 1));

        ApplicativeParser<Void> flagParser = ApplicativeParser.choice(
                CommandParserUtil.TITLE_FLAG_PARSER
                        .consume(counter::add)
                        .takeNext(ApplicativeParser.skipWhitespaces1())
                        .takeNext(CommandParserUtil.TITLE_PARSER)
                        .consume(request::setEditedTitle),
                CommandParserUtil.LABEL_FLAG_PARSER
                        .consume(counter::add)
                        .takeNext(ApplicativeParser.skipWhitespaces1())
                        .takeNext(CommandParserUtil.LABEL_PARSER)
                        .dropNext(ApplicativeParser.skipWhitespaces1())
                        .combine(CommandParserUtil.LABEL_PARSER, Pair::of)
                        .consume(pair -> request.getEditedLabels().add(pair)),
                CommandParserUtil.STATUS_FLAG_PARSER
                        .consume(counter::add)
                        .takeNext(ApplicativeParser.skipWhitespaces1())
                        .takeNext(CommandParserUtil.STATUS_PARSER)
                        .consume(request::setEditedStatus),
                CommandParserUtil.PRIORITY_FLAG_PARSER
                        .consume(counter::add)
                        .takeNext(ApplicativeParser.skipWhitespaces1())
                        .takeNext(CommandParserUtil.PRIORITY_PARSER)
                        .consume(request::setEditedPriority),
                CommandParserUtil.DEADLINE_FLAG_PARSER
                        .consume(counter::add)
                        .takeNext(ApplicativeParser.skipWhitespaces1())
                        .takeNext(CommandParserUtil.DEADLINE_PARSER)
                        .consume(request::setEditedDeadline));

        return ApplicativeParser
                .skipWhitespaces1()
                // must have at least 1 flag
                .takeNext(flagParser.sepBy1(ApplicativeParser.skipWhitespaces1()))
                .constMap(new EditCommand(index, request));
    }

    public static EditCommandParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ApplicativeParser<ApplicativeParser<EditCommand>> getInternalParser() {
        return INTERNAL_PARSER;
    }

}
