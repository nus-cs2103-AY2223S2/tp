package vimification.internal.parser;

import vimification.internal.command.logic.DeleteCommand;

public class DeleteCommandParser implements CommandParser<DeleteCommand> {

    private static final ApplicativeParser<DeleteCommand> COMMAND_PARSER =
            CommandParserUtil.ONE_BASED_INDEX_PARSER
                    .dropNext(ApplicativeParser.skipWhitespaces())
                    .dropNext(ApplicativeParser.eof())
                    .map(DeleteCommand::new);

    private static final ApplicativeParser<ApplicativeParser<DeleteCommand>> INTERNAL_PARSER =
            ApplicativeParser.string("d")
                    .takeNext(ApplicativeParser.skipWhitespaces1())
                    .constMap(COMMAND_PARSER);

    private static final DeleteCommandParser INSTANCE = new DeleteCommandParser();

    private DeleteCommandParser() {}

    public static DeleteCommandParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ApplicativeParser<ApplicativeParser<DeleteCommand>> getInternalParser() {
        return INTERNAL_PARSER;
    }
}
