package vimification.logic.parser;

import vimification.logic.commands.LogicCommand;

public class VimificationParser {

    private static final LogicCommandParser<LogicCommand> LOGIC_COMMAND_PARSER =
            CreateCommandParser.getInstance()
                    .safeCast()
                    .or(DeleteCommandParser.getInstance())
                    .or(InvalidCommandParser.getInstance())
                    .throwIfFail("Unknown command");

    public static VimificationParser getInstance() {
        return new VimificationParser();
    }

    public LogicCommand parse(String userInput) {
        return LOGIC_COMMAND_PARSER.parse(userInput);
    }
}
