package seedu.address.model.service.exception;

import seedu.address.model.service.Part;

/**
 * This exception is raised when there is insufficient parts.
 */
public class InsufficientPartException extends RuntimeException {
    /**
     * Constructs a new runtime exception with the specified part
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param part the part. The detail message is saved for
     *             later retrieval by the {@link #getMessage()} method.
     */
    public InsufficientPartException(Part part) {
        super("Insufficient part " + part.getId() + " : " + part.getName());
    }
}
