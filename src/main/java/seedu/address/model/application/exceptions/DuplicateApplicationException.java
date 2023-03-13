package seedu.address.model.application.exceptions;

/**
 * Signals that the operation will result in duplicate Applications.
 * Comment to let merge operation detect file. To be deleted subsequently.
 */
public class DuplicateApplicationException extends RuntimeException {
    public DuplicateApplicationException() {
        super("Operation would result in duplicate entries.");
    }
}
