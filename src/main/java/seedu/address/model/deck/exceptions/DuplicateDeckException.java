package seedu.address.model.deck.exceptions;

public class DuplicateDeckException extends RuntimeException {
    public DuplicateDeckException() { super("Operation would result in duplicate cards");}
}
