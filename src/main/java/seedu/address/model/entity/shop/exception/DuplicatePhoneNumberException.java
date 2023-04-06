package seedu.address.model.entity.shop.exception;

import seedu.address.model.entity.person.Phone;

/**
 * Thrown when phone number is already in use
 */
public class DuplicatePhoneNumberException extends Exception {
    public DuplicatePhoneNumberException(Phone phone) {
        super(String.format("Phone number %s already in use", phone));
    }
}
