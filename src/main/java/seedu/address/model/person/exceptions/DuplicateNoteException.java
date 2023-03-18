package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in duplicate Note (Notes are considered duplicates if they have the same
 * content).
 */
public class DuplicateNoteException extends RuntimeException {
    public DuplicateNoteException() {
        super("Operation would result in duplicate notes");
    }
}
