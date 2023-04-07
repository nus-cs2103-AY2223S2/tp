package seedu.address.model.entity.shop.exception;

import seedu.address.model.entity.person.Email;

/**
 * Thrown when email is already in use
 */
public class DuplicateEmailException extends Exception {
    public DuplicateEmailException(Email email) {
        super(String.format("Email %s already in use", email));
    }
}
