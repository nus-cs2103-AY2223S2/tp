package seedu.sudohr.model.leave.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Persons are
 * considered duplicates if they have the same
 * identity).
 */
public class DuplicateLeaveException extends RuntimeException {
    public DuplicateLeaveException() {
        super("Operation would result in duplicate event");
    }
}
