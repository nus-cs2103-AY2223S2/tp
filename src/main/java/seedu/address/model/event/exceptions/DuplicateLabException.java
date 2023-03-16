package seedu.address.model.event.exceptions;

/**
 * Signals that the operation will result in duplicate Lab (Lab are considered
 * duplicates if they have the same identity).
 */
public class DuplicateLabException extends RuntimeException {
    public DuplicateLabException() {
        super("Operation would result in duplicate labs");
    }
}
