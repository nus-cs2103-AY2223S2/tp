package seedu.address.model.event.exceptions;

/**
 * Signals that the operation will result in duplicate Tutorial (Tutorial are considered
 * duplicates if they have the same identity).
 */
public class DuplicateTutorialException extends RuntimeException {
    public DuplicateTutorialException() {
        super("You have already created this Tutorial event!");
    }
}
