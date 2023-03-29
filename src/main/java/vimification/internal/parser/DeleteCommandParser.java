package vimification.internal.parser;

import vimification.commons.core.Index;
import vimification.internal.command.logic.DeleteCommand;
import vimification.internal.command.logic.DeleteFieldsCommand;
import vimification.internal.command.logic.DeleteFieldsRequest;
import vimification.internal.command.logic.DeleteTaskCommand;

public class DeleteCommandParser implements CommandParser<DeleteCommand> {

    private static final ArgumentFlag LABEL_FLAG =
            new ArgumentFlag("-l", "--label", Integer.MAX_VALUE);

    private static final ArgumentFlag DEADLINE_FLAG = new ArgumentFlag("-d", "--deadline");

    private static final ApplicativeParser<ArgumentFlag> LABEL_FLAG_PARSER =
            CommandParserUtil.flag(LABEL_FLAG);

    private static final ApplicativeParser<ArgumentFlag> DEADLINE_FLAG_PARSER =
            CommandParserUtil.flag(DEADLINE_FLAG);

    private static final ApplicativeParser<String> LABEL_PARSER =
            CommandParserUtil.STRING_PARSER;

    private static final ApplicativeParser<DeleteCommand> COMMAND_PARSER =
            CommandParserUtil.ONE_BASED_INDEX_PARSER
                    .flatMap(index -> parseArguments()
                            .<DeleteCommand>map(req -> new DeleteFieldsCommand(index, req))
                            .orElse(new DeleteTaskCommand(index)));

    private static final ApplicativeParser<ApplicativeParser<DeleteCommand>> INTERNAL_PARSER =
            ApplicativeParser
                    .string("d")
                    .takeNext(ApplicativeParser.skipWhitespaces1())
                    .constMap(COMMAND_PARSER);

    private static final DeleteCommandParser INSTANCE = new DeleteCommandParser();

    private DeleteCommandParser() {}

    private static ApplicativeParser<DeleteFieldsRequest> parseArguments() {
        DeleteFieldsRequest request = new DeleteFieldsRequest();
        ArgumentCounter counter = new ArgumentCounter(LABEL_FLAG, DEADLINE_FLAG);
        return ApplicativeParser.choice(
                DEADLINE_FLAG_PARSER.consume(flag -> {
                    counter.add(flag);
                    request.deadline = true;
                }),
                LABEL_FLAG_PARSER.consume(counter::add)
                        .takeNext(ApplicativeParser.skipWhitespaces1())
                        .takeNext(LABEL_PARSER)
                        .consume(request.labels::add))
                .sepBy1(ApplicativeParser.skipWhitespaces1())
                .constMap(request);
    }

    public static DeleteCommandParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ApplicativeParser<ApplicativeParser<DeleteCommand>> getInternalParser() {
        return null;
    }
}
