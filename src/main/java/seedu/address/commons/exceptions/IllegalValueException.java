package seedu.address.commons.exceptions;

/**
 * Signals that some given data does not fulfill some constraints.
 */
public class IllegalValueException extends Exception {
    /**
     * Constructs an {@code IllegalValueException} with the specified detail message.
     *
     * @param message Should contain relevant information on the failed constraint(s).
     */
    public IllegalValueException(String message) {
        super(message);
    }

    /**
     * Constructs an {@code IllegalValueException} with the specified detail message and cause.
     *
     * @param message Should contain relevant information on the failed constraint(s).
     * @param cause Of the main exception.
     */
    public IllegalValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
