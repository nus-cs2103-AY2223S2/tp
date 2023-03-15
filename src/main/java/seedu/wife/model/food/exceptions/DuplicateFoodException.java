package seedu.wife.model.food.exceptions;

/**
 * Signals that the operation will result in duplicate Food (Food are considered duplicates if they have the same
 * name and expiry date).
 */
public class DuplicateFoodException extends RuntimeException {
    public DuplicateFoodException() {
        super("Operation would result in duplicate persons, choose to update the quantity instead?");
    }
}
