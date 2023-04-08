package seedu.address.model.deck.exceptions;

/**
 * Signals that the operation will result in duplicate deckss (Cards are considered duplicates if they have the same
 * name).
 */
public class DuplicateDeckException extends RuntimeException {
    public DuplicateDeckException() {
        super("Operation would result in duplicate cards");
    }
}
