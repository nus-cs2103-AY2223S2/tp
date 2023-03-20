package seedu.wife.model.tag.exceptions;

/**
 * Signals that the operation will result in duplicate Tag (Tag are considered duplicates if they have the same
 * name).
 */
public class DuplicateTagException extends RuntimeException {

    /**
     * Constructor
     */
    public DuplicateTagException() {
        super(
            "Operation would result in duplicate tag, try adding tags that are not in the list?"
        );
    }
}
