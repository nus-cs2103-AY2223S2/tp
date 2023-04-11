package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in duplicate Parents (Parents are considered duplicates if they have the same
 * phone number).
 */
public class DuplicateParentException extends RuntimeException {
    public DuplicateParentException() {
        super("ERROR, operation would result in duplicate parents!");
    }
}
