package seedu.address.model.service.exception;

/**
 * This exception is raised when a user attempts to add a negative quantity for parts.
 */
public class NegativeValueException extends RuntimeException {
    /**
     * Constructs a new runtime exception with the specified part
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param partName Name of the part with a lack of quantity.
     */
    public NegativeValueException(int value) {
        super("Unable to set values to negative values");
    }
}
