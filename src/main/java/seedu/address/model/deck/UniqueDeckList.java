package seedu.address.model.deck;

/**
 * A list of decks that enforces uniqueness between its elements and does not allow nulls.
 * A deck is considered unique by comparing using {@code Deck#isSameDeck(Deck)}. As such, adding and updating of
 * decks uses Deck#isSameDeck(Deck) for equality so as to ensure that the deck being added or updated is
 * unique in terms of identity in the UniqueCardList. However, the removal of a deck uses Card#equals(Object) so
 * as to ensure that the deck with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Deck#isSameDeck(Deck)
 */
public class UniqueDeckList {
}
