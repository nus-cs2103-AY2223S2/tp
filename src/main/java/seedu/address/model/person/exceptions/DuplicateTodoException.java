package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in duplicate Todo (Todos are considered duplicates if they have the same
 * title or company name).
 */
public class DuplicateTodoException extends RuntimeException {
    public DuplicateTodoException() {
        super("Operation would result in duplicate todos");
    }
}
