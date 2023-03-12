package seedu.sudohr.model.person.exceptions;

/**
 * Signals that the operation will result in duplicate emails for different persons
 */
public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException() {
        super("Operation would result in duplicate emails");
    }
}
