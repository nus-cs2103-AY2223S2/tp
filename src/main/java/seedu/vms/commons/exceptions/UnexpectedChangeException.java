package seedu.vms.commons.exceptions;


/**
 * Signals that an unexpected change has occurred.
 */
public class UnexpectedChangeException extends Exception {
    /**
     * Constructs an {@code UnexpectedChangeException} without a message or cause.
     */
    public UnexpectedChangeException() {}


    /**
     * Constructs an {@code UnexpectedChangeException} without a cause.
     *
     * @param message - message of the exception.
     */
    public UnexpectedChangeException(String message) {
        super(message);
    }


    /**
     * Constructs an {@code UnexpectedChangeException} without a cause.
     *
     * @param cause - the cause of the exception.
     */
    public UnexpectedChangeException(Throwable cause) {
        super(cause);
    }


    /**
     * Constructs an {@code UnexpectedChangeException}.
     *
     * @param message - the message of the exception.
     * @param cause - the cause of the exception.
     */
    public UnexpectedChangeException(String message, Throwable cause) {
        super(message, cause);
    }
}
