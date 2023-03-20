package vimification.logic.parser;

import vimification.logic.commands.LogicCommand;

public class VimificationParser {

    private static final ApplicativeParser<LogicCommand> LOGIC_COMMAND_PARSER = ApplicativeParser
            .choice(
                    CreateCommandParser.getInstance().getParser(),
                    DeleteCommandParser.getInstance().getParser());

    public static VimificationParser getInstance() {
        return new VimificationParser();
    }

    public LogicCommand parse(String userInput) {
        return LOGIC_COMMAND_PARSER.parse(userInput);
    }
}
