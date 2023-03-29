package seedu.address.model.task.exceptions;

/**
 * Signals that the operation will result in duplicate Todos (Todos are considered duplicates if they have the same
 * title or company name).
 */
public class DuplicateTodoException extends RuntimeException {

    /**
     * Creates an instance of DuplicateTodoException.
     */
    public DuplicateTodoException() {
        super("Operation would result in duplicate todos");
    }
}
