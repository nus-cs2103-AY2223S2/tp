package seedu.address.model.fish.exceptions;

/**
 * Signals that the operation will result in duplicate Fishes (Fishes are considered duplicates if they have the same
 * identity).
 */
public class DuplicateFishException extends RuntimeException {
    public DuplicateFishException() {
        super("Operation would result in duplicate Fishes");
    }
}
