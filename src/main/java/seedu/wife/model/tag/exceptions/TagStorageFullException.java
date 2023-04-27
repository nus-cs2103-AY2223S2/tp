package seedu.wife.model.tag.exceptions;

/**
 * Signals that the operation cannot add new tags to full tag storage.
 */
public class TagStorageFullException extends RuntimeException {

    /**
     * Constructor
     */
    public TagStorageFullException() {
        super(
            "Tag storage is full, please remove at existing tags in storage."
        );
    }
}
