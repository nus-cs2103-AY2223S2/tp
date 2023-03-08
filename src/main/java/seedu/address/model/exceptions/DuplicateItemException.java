package seedu.address.model.exceptions;

/**
 * Signals that the operation will result in duplicate PersonTasks (PersonTasks
 * are considered duplicates if they have the same identity).
 */
public class DuplicateItemException extends RuntimeException {
    public DuplicateItemException() {
        super("Operation would result in duplicate item");
    }
}
