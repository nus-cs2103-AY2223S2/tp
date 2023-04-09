package seedu.address.logic.commands.exceptions;

import seedu.address.logic.commands.Command;

/**
 * Represents an error which occurs during execution of a {@link Command}.
 */
public class CommandException extends Exception {
    public CommandException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code CommandException} with the specified detail {@code message} and {@code cause}.
     * @param message the detail message.
     * @param cause the cause of the Exception.
     */
    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
