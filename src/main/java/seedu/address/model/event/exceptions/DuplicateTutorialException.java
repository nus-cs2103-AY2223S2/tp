package seedu.address.model.event.exceptions;


public class DuplicateTutorialException extends RuntimeException {
    public DuplicateTutorialException() {
        super("Operation would result in duplicate tutorials");
    }
}
