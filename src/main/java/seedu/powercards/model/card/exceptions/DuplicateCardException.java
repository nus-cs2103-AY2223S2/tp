package seedu.powercards.model.card.exceptions;

/**
 * Signals that the operation will result in duplicate cards (Cards are considered duplicates if they have the same
 * identity).
 */
public class DuplicateCardException extends RuntimeException {
    public DuplicateCardException() {
        super("Operation would result in duplicate cards");
    }
}
