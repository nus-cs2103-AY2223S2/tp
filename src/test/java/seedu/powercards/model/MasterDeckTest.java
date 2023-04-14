package seedu.powercards.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.powercards.logic.commands.CommandTestUtil.VALID_TAG_MEDIUM;
import static seedu.powercards.testutil.Assert.assertThrows;
import static seedu.powercards.testutil.TypicalCards.LOOP;
import static seedu.powercards.testutil.TypicalCards.getTypicalMasterDeck;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.powercards.model.card.Card;
import seedu.powercards.model.card.exceptions.DuplicateCardException;
import seedu.powercards.model.deck.Deck;
import seedu.powercards.testutil.CardBuilder;

public class MasterDeckTest {

    private final MasterDeck masterDeck = new MasterDeck();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), masterDeck.getCardList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> masterDeck.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyMasterDeck_replacesData() {
        MasterDeck newData = getTypicalMasterDeck();
        masterDeck.resetData(newData);
        assertEquals(newData, masterDeck);
    }

    @Test
    public void resetData_withDuplicateCards_throwsDuplicateCardException() {
        // Two cards with the same identity fields
        Card editedLoop = new CardBuilder(LOOP).withTag(VALID_TAG_MEDIUM).build();
        List<Card> newCards = Arrays.asList(LOOP, editedLoop);
        MasterDeckStub newData = new MasterDeckStub(newCards);

        assertThrows(DuplicateCardException.class, () -> masterDeck.resetData(newData));
    }

    @Test
    public void hasCard_nullCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> masterDeck.hasCard(null));
    }

    @Test
    public void hasCard_cardNotInMasterDeck_returnsFalse() {
        assertFalse(masterDeck.hasCard(LOOP));
    }

    @Test
    public void hasCard_cardInMasterDeck_returnsTrue() {
        masterDeck.addCard(LOOP);
        assertTrue(masterDeck.hasCard(LOOP));
    }

    @Test
    public void hasCard_cardWithSameIdentityFieldsInMasterDeck_returnsTrue() {
        masterDeck.addCard(LOOP);
        Card editedLoop = new CardBuilder(LOOP).withTag(VALID_TAG_MEDIUM).build();
        assertTrue(masterDeck.hasCard(editedLoop));
    }

    @Test
    public void getCardList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> masterDeck.getCardList().remove(0));
    }

    /**
     * A stub ReadOnlyDeck whose cards list can violate interface constraints.
     */
    private static class MasterDeckStub implements ReadOnlyMasterDeck {
        private final ObservableList<Card> cards = FXCollections.observableArrayList();
        private final ObservableList<Deck> decks = FXCollections.observableArrayList();
        MasterDeckStub(Collection<Card> cards) {
            this.cards.setAll(cards);
        }

        @Override
        public ObservableList<Card> getCardList() {
            return cards;
        }

        @Override
        public ObservableList<Deck> getDeckList() {
            return decks;
        }
    }

}
