package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in duplicate Classes (Classes are considered duplicates if they have the same
 */
public class DuplicateClassException extends Exception {
    /**
     * Constructs a DuplicateClassException with the specified detail message.
     */
    public DuplicateClassException() {
        super("Operation would result in duplicate classes");
    }
}
