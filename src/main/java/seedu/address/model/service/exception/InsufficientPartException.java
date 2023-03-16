package seedu.address.model.service.exception;


/**
 * This exception is raised when there is insufficient parts.
 */
public class InsufficientPartException extends RuntimeException {
    /**
     * Constructs a new runtime exception with the specified part
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param partName Name of the part with a lack of quantity.
     */
    public InsufficientPartException(String partName) {
        super("Insufficient parts for " + partName);
    }
}
