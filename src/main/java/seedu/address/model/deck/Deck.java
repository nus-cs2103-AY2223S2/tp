package seedu.address.model.deck;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * A group of cards
 */
public class Deck {

    public final String deckName;

    /**
     * Constructing a deck.
     * Every field must be present and not null.
     */
    public Deck(String deckName) {
        requireAllNonNull(deckName);
        this.deckName = deckName;
    }

    /**
     * Returns the name of the deck
     * @return deckName
     */
    public String getDeckName() {
        return this.deckName;
    }

    /**
     * Returns true if there is a deck of same name.
     * This defines a weaker notion of equality between two decks.
     */
    public boolean isSameDeck(Deck otherDeck) {
        if (otherDeck == this) {
            return true;
        }

        return otherDeck != null
                && otherDeck.getDeckName().equals(getDeckName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Deck)) {
            return false;
        }

        Deck otherDeck = (Deck) other;
        return otherDeck.getDeckName().equals(getDeckName());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(deckName);
    }

    @Override
    public String toString() {
        return this.deckName;
    }

}
