package seedu.address.storage.exceptions;

/**
 * Represents an exception that occurs when an AddressBook is unexpectedly empty.
 */
public class EmptyAddressBookException extends Exception {
    public EmptyAddressBookException(String message) {
        super(message);
    }
}
