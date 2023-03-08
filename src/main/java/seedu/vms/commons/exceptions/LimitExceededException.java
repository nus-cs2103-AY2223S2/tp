package seedu.vms.commons.exceptions;


/** Signals that a limit has been exceeded. */
public class LimitExceededException extends RuntimeException {
    /**
     * Constructs an {@code LimitExceededException} without a message or cause.
     */
    public LimitExceededException() {}


    /**
     * Constructs an {@code LimitExceededException} without a cause.
     *
     * @param message - message of the exception.
     */
    public LimitExceededException(String message) {
        super(message);
    }


    /**
     * Constructs an {@code LimitExceededException} without a cause.
     *
     * @param cause - the cause of the exception.
     */
    public LimitExceededException(Throwable cause) {
        super(cause);
    }


    /**
     * Constructs an {@code LimitExceededException}.
     *
     * @param message - the message of the exception.
     * @param cause - the cause of the exception.
     */
    public LimitExceededException(String message, Throwable cause) {
        super(message, cause);
    }
}
