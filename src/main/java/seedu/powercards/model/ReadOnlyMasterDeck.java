package seedu.powercards.model;

import javafx.collections.ObservableList;
import seedu.powercards.model.card.Card;
import seedu.powercards.model.deck.Deck;

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
