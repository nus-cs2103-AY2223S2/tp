package seedu.address.model.person.exceptions;

/**
 * Signals user that input index is invalid.
 */
public class InvalidContactIndexException extends RuntimeException {
    public InvalidContactIndexException() {
        super("Contact index should be strictly positive! Only user has index 0!");
    }
}
