package seedu.sudohr.model.employee.exceptions;

/**
 * Signals that the operation will result in duplicate emails for different employees
 */
public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException() {
        super("Operation would result in duplicate emails");
    }
}
