package seedu.address.model.application.exceptions;

/**
 * Signals that the operation will result in duplicate Applications.
 */
public class DuplicateApplicationException extends RuntimeException {
    public DuplicateApplicationException() {
        super("Operation would result in duplicate entries.");
    }
}
