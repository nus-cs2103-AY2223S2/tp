package seedu.vms.logic.parser;

import java.util.Optional;

import seedu.vms.logic.CommandMessage;
import seedu.vms.logic.commands.Command;


/**
 * An association class between a parsed {@code Command} and its parse message.
 */
public class ParseResult {
    private final Command command;
    private final Optional<CommandMessage> parseMessage;


    /**
     * Constructs a {@code ParseResult} without a message.
     */
    public ParseResult(Command command) {
        this(command, null);
    }


    /**
     * Constructs a {@code ParseResult}.
     */
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
