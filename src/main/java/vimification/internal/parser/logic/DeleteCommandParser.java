package vimification.internal.parser.logic;

import vimification.common.core.Index;
import vimification.internal.command.logic.DeleteCommand;
import vimification.internal.command.logic.DeleteFieldsCommand;
import vimification.internal.command.logic.DeleteFieldsRequest;
import vimification.internal.command.logic.DeleteTaskCommand;
import vimification.internal.parser.ApplicativeParser;
import vimification.internal.parser.ArgumentCounter;
import vimification.internal.parser.CommandParser;
import vimification.internal.parser.CommandParserUtil;
import vimification.internal.parser.Pair;

/**
 * The parser that can parses and creates new {@link DeleteCommand}.
 */
public class DeleteCommandParser implements CommandParser<DeleteCommand> {

    private static final ApplicativeParser<DeleteCommand> COMMAND_PARSER =
            CommandParserUtil.ONE_BASED_INDEX_PARSER
                    .flatMap(DeleteCommandParser::parseArguments)
                    .dropNext(CommandParserUtil.END_OF_COMMAND_PARSER);

    private static final ApplicativeParser<ApplicativeParser<DeleteCommand>> INTERNAL_PARSER =
            ApplicativeParser
                    .string("d")
                    .takeNext(ApplicativeParser.skipWhitespaces1())
                    .constMap(COMMAND_PARSER);

    private static final DeleteCommandParser INSTANCE = new DeleteCommandParser();

    private DeleteCommandParser() {}

    private static ApplicativeParser<DeleteCommand> parseArguments(Index index) {
        DeleteFieldsRequest request = new DeleteFieldsRequest();
        ArgumentCounter counter = new ArgumentCounter(
                Pair.of(CommandParserUtil.LABEL_FLAG, Integer.MAX_VALUE),
                Pair.of(CommandParserUtil.DEADLINE_FLAG, 1));

        ApplicativeParser<Void> flagParser = ApplicativeParser.choice(
                CommandParserUtil.LABEL_FLAG_PARSER
                        .consume(counter::add)
                        .takeNext(ApplicativeParser.skipWhitespaces1())
                        .takeNext(CommandParserUtil.LABEL_PARSER)
                        .consume(label -> request.getDeletedLabels().add(label)),
                CommandParserUtil.DEADLINE_FLAG_PARSER
                        .consume(flag -> {
                            counter.add(flag);
                            request.setDeleteDeadline(true);
                        }));

        return ApplicativeParser
                .skipWhitespaces1()
                .takeNext(flagParser.sepBy1(ApplicativeParser.skipWhitespaces1()))
                .<DeleteCommand>constMap(() -> new DeleteFieldsCommand(index, request))
                .orElse(() -> new DeleteTaskCommand(index));
    }

    public static DeleteCommandParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ApplicativeParser<ApplicativeParser<DeleteCommand>> getInternalParser() {
        return INTERNAL_PARSER;
    }
}
