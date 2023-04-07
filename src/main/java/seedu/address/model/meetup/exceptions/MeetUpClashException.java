package seedu.address.model.meetup.exceptions;

/**
 * Signals user that meet up clashes with existent scheduled meet ups.
 */
public class MeetUpClashException extends RuntimeException {
    public MeetUpClashException() {
    }
}
