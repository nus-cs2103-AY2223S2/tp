package seedu.vms.logic.parser;

import java.util.Optional;

import seedu.vms.logic.CommandMessage;
import seedu.vms.logic.commands.Command;

public class ParseResult {
    private final Command command;
    private final Optional<CommandMessage> parseMessage;


    public ParseResult(Command command) {
        this(command, null);
    }


    public ParseResult(Command command, CommandMessage parseMessage) {
        this.command = command;
        this.parseMessage = Optional.ofNullable(parseMessage);
    }


    public Command getCommand() {
        return command;
    }


    public Optional<CommandMessage> getMessage() {
        return parseMessage;
    }
}
