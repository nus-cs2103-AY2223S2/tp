package seedu.sudohr.model.employee.exceptions;

/**
 * Signals that the operation will result in duplicate phone number for different employees
 */
public class DuplicatePhoneNumberException extends RuntimeException {
    public DuplicatePhoneNumberException() {
        super("Operation would result in duplicate phone numbers");
    }
}
