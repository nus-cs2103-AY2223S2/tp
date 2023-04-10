package wingman.logic.core.exceptions;

import wingman.logic.core.Command;

/**
 * Represents an error which occurs during execution of a {@link Command}.
 */
public class CommandException extends Exception {
    public CommandException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code CommandException} with the specified detail {@code message} and {@code cause}.
     */
    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@code CommandException} that is formatted.
     *
     * @param message the message to be formatted.
     * @param args    the arguments to be formatted.
     * @return the formatted {@code CommandException}
     */
    public static CommandException formatted(String message, Object... args) {
        return new CommandException(String.format(message, args));
    }
}
