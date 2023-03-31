package seedu.address.model.meetup.exceptions;

/**
 * Signals user that input index is invalid.
 */
public class InvalidMeetUpIndexException extends RuntimeException {
    public InvalidMeetUpIndexException() {
        super("Meet up index should be strictly positive!");
    }
}
