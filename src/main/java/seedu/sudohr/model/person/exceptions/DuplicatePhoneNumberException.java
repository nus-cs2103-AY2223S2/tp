package seedu.sudohr.model.person.exceptions;

/**
 * Signals that the operation will result in duplicate phone number for different persons
 */
public class DuplicatePhoneNumberException extends RuntimeException {
    public DuplicatePhoneNumberException() {
        super("Operation would result in duplicate phone numbers");
    }
}
