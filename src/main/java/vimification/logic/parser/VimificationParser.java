package vimification.logic.parser;

import vimification.logic.commands.LogicCommand;

public class VimificationParser {

    private static final LogicCommandParser<LogicCommand> INTERNAL_PARSER =
            CreateCommandParser.getInstance()
                    .cast()
                    .or(DeleteCommandParser.getInstance())
                    .updateInternalParser(parser -> parser.throwIfFail("Unknown command"));

    public static VimificationParser getInstance() {
        return new VimificationParser();
    }

    public LogicCommand parse(String userInput) {
        return INTERNAL_PARSER.parse(userInput);
    }
}
