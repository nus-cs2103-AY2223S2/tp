package seedu.address.model.meetup.exceptions;

public class DuplicateMeetUpException extends RuntimeException {
    public DuplicateMeetUpException() {
        super("Operation would result in duplicate meets");
    }
}