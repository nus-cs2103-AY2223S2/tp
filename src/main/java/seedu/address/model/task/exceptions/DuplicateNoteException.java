package seedu.address.model.task.exceptions;

/**
 * Signals that the operation will result in duplicate Notes (Notes are considered duplicates if they have the same
 * content).
 */
public class DuplicateNoteException extends RuntimeException {

    /**
     * Creates an instance of DuplicateNoteException.
     */
    public DuplicateNoteException() {
        super("Operation would result in duplicate notes");
    }
}
