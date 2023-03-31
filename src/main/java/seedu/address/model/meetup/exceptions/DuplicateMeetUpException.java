package seedu.address.model.meetup.exceptions;

/**
 * Signals that the operation will result in duplicate meet ups being created.
 */
public class DuplicateMeetUpException extends RuntimeException {
    public DuplicateMeetUpException() {
        super("Operation would result in duplicate meets");
    }
}
