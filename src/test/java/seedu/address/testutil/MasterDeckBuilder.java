package seedu.address.testutil;

import seedu.address.model.MasterDeck;
import seedu.address.model.card.Card;

/**
 * A utility class to help with building MasterDeck objects.
 * Example usage: <br>
 *     {@code Deck ab = new MasterDeckBuilder().withCard("What is 1 + 1", "2").build();}
 */
public class MasterDeckBuilder {

    private MasterDeck masterDeck;

    public MasterDeckBuilder() {
        masterDeck = new MasterDeck();
    }

    public MasterDeckBuilder(MasterDeck masterDeck) {
        this.masterDeck = masterDeck;
    }

    /**
     * Adds a new {@code Card} to the {@code Deck} that we are building.
     */
    public MasterDeckBuilder withCard(Card card) {
        masterDeck.addCard(card);
        return this;
    }

    public MasterDeck build() {
        return masterDeck;
    }
}
