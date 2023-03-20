package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.card.Card;
import seedu.address.model.deck.Deck;

/**
 * Unmodifiable view of a master deck
 */
public interface ReadOnlyMasterDeck {

    /**
     * Returns an unmodifiable view of the cards list.
     * This list will not contain any duplicate cards.
     */
    ObservableList<Card> getCardList();

    ObservableList<Deck> getDeckList();
}
