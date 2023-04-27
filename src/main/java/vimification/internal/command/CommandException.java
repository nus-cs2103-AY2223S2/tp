package vimification.internal.command;

/**
 * Represents an error which occurs during execution of a {@link Command}.
 */
public class CommandException extends RuntimeException {

    /**
     * Constructs a new {@code CommandException} with the specified detail {@code message}.
     */
    public CommandException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code CommandException} with the specified detail {@code message} and
     * {@code cause}.
     */
    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
