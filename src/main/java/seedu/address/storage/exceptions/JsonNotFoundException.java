package seedu.address.storage.exceptions;

/**
 * Represents an exception that occurs when an AddressBook is unexpectedly empty.
 */
public class JsonNotFoundException extends Exception {
    public JsonNotFoundException(String message) {
        super(message);
    }
}
