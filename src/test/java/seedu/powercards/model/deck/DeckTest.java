package seedu.powercards.model.deck;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.powercards.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.util.Pair;

public class DeckTest {
    private static final String VALID_DECK_NAME = "MyDeck";
    private static final String VALID_DECK_NAME2 = "YourDeck";

    private Deck testDeck = new Deck(VALID_DECK_NAME);

    @Test
    public void constructor_validDeckName_success() {
        assertEquals(VALID_DECK_NAME, testDeck.getDeckName());
        assertFalse(testDeck.isSelected());
    }

    @Test
    public void buildSelectedDeck_success() {
        Deck selectedDeck = testDeck.buildSelectedDeck();
        assertTrue(selectedDeck.isSelected());
    }

    @Test
    public void buildUnselectedDeck_success() {
        Deck unselectedDeck = testDeck.buildUnselectedDeck();
        assertFalse(unselectedDeck.isSelected());
    }

    @Test
    public void isValidDeckName_validDeckName_returnsTrue() {
        assertTrue(Deck.isValidDeckName(VALID_DECK_NAME));
    }

    @Test
    public void isValidDeckName_nullDeckName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Deck.isValidDeckName(null));
    }

    @Test
    public void isSameDeck_sameDeck_returnsTrue() {
        Deck sameDeck = new Deck(VALID_DECK_NAME);
        assertTrue(testDeck.isSameDeck(sameDeck));
    }

    @Test
    public void isSameDeck_differentDeck_returnsFalse() {
        Deck differentDeck = new Deck(VALID_DECK_NAME2);
        assertFalse(testDeck.isSameDeck(differentDeck));
    }

    @Test
    public void equals_sameDeck_returnsTrue() {
        Deck sameDeck = new Deck(VALID_DECK_NAME);
        assertTrue(testDeck.equals(sameDeck));
    }

    @Test
    public void equals_differentDeck_returnsFalse() {
        Deck differentDeck = new Deck(VALID_DECK_NAME2);
        assertFalse(testDeck.equals(differentDeck));
    }

    @Test
    public void getDeckNameList_validDeckName_returnsObservableList() {
        ObservableList<Pair<String, String>> deckNameList = testDeck.getDeckNameList();
        Pair<String, String> header = deckNameList.get(0);
        assertEquals(header.getKey(), "Current Deck:");
        assertEquals(header.getValue(), VALID_DECK_NAME);
    }
}
