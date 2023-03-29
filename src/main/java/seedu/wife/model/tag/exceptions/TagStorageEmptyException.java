package seedu.wife.model.tag.exceptions;

/**
 * Signals that the operation is unable to execute on empty tag storage.
 */
public class TagStorageEmptyException extends RuntimeException {

    /**
     * Constructor
     */
    public TagStorageEmptyException() {
        super("Tag storage is empty.");
    }
}
