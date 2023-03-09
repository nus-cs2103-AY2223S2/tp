package seedu.address.model.video.exceptions;

/**
 * Signals that the operation will result in duplicate Videos (Videos are considered duplicates if they have the same
 * name).
 */
public class DuplicateVideoException extends RuntimeException {
    public DuplicateVideoException() {
        super("Operation would result in duplicate videos");
    }
}
