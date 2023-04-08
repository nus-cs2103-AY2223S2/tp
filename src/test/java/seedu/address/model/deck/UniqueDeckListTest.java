package seedu.address.model.deck;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalDecks.VALID_DECK_SCIENCE;
import static seedu.address.testutil.TypicalDecks.VALID_DECK_HISTORY;

import org.junit.jupiter.api.Test;

import seedu.address.model.deck.exceptions.DeckNotFoundException;
import seedu.address.model.deck.exceptions.DuplicateDeckException;

public class UniqueDeckListTest {

    private final UniqueDeckList uniqueDeckList = new UniqueDeckList();

    @Test
    public void contains_nullDeck_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeckList.contains(null));
    }

    @Test
    public void contains_deckNotInList_returnsFalse() {
        assertFalse(uniqueDeckList.contains(VALID_DECK_SCIENCE));
    }

    @Test
    public void contains_deckInList_returnsTrue() {
        uniqueDeckList.add(VALID_DECK_SCIENCE);
        assertTrue(uniqueDeckList.contains(VALID_DECK_SCIENCE));
    }

    @Test
    public void add_nullDeck_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeckList.add(null));
    }

    @Test
    public void add_duplicateDeck_throwsDuplicateDeckException() {
        uniqueDeckList.add(VALID_DECK_SCIENCE);
        assertThrows(DuplicateDeckException.class, () -> uniqueDeckList.add(VALID_DECK_SCIENCE));
    }

    @Test
    public void setDeck_nullTargetDeck_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeckList.setDeck(null, VALID_DECK_SCIENCE));
    }

    @Test
    public void setDeck_nullEditedDeck_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueDeckList.setDeck(VALID_DECK_SCIENCE, null));
    }

    @Test
    public void setDeck_targetDeckNotInList_throwsDeckNotFoundException() {
        assertThrows(DeckNotFoundException.class, () -> uniqueDeckList.setDeck(VALID_DECK_SCIENCE, VALID_DECK_SCIENCE));
    }

    @Test
    public void setDeck_editedDeckIsSameDeck_success() {
        uniqueDeckList.add(VALID_DECK_SCIENCE);
        uniqueDeckList.setDeck(VALID_DECK_SCIENCE, VALID_DECK_SCIENCE);
        UniqueDeckList expectedUniqueDeckList = new UniqueDeckList();
        expectedUniqueDeckList.add(VALID_DECK_SCIENCE);
        assertEquals(expectedUniqueDeckList, uniqueDeckList);
    }

    @Test
    public void setDeck_editedDeckHasSameIdentity_success() {
        uniqueDeckList.add(VALID_DECK_SCIENCE);
        Deck editedDeckA = VALID_DECK_HISTORY;
        uniqueDeckList.setDeck(VALID_DECK_SCIENCE, editedDeckA);
        UniqueDeckList expectedUniqueDeckList = new UniqueDeckList();
        expectedUniqueDeckList.add(editedDeckA);
        assertEquals(expectedUniqueDeckList, uniqueDeckList);
    }

}
