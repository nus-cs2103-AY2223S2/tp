package seedu.address.model.card.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class DuplicateCardException extends RuntimeException {
    public DuplicateCardException() {
        super("Operation would result in duplicate persons");
    }
}
