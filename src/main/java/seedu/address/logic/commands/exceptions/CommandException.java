package seedu.address.logic.commands.exceptions;

import seedu.address.logic.commands.Command;

/**
 * Represents an error which occurs during execution of a {@link Command}.
 */
public class CommandException extends Exception {
    /**
     * Constructs a new {@code CommandException} with the specified detail {@code message}.
     *
     * @param message Exception message.
     */
    public CommandException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code CommandException} with the specified detail {@code message} and {@code cause}.
     *
     * @param message Exception message.
     * @param cause Cause of exception.
     */
    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
