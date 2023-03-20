package vimification.logic.parser;

import vimification.logic.commands.LogicCommand;

public class InvalidCommandParser implements LogicCommandParser<LogicCommand> {

    private static final InvalidCommandParser INSTANCE = new InvalidCommandParser();

    private InvalidCommandParser() {}

    public static InvalidCommandParser getInstance() {
        return INSTANCE;
    }

    @Override
    public ApplicativeParser<LogicCommand> getInternalParser() {
        return ApplicativeParser.fail();
    }
}
