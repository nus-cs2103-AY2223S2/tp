package seedu.powercards.model.deck;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.powercards.testutil.TypicalDecks.VALID_DECK_HISTORY;
import static seedu.powercards.testutil.TypicalDecks.VALID_DECK_SCIENCE;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.powercards.model.deck.exceptions.DeckNotFoundException;
import seedu.powercards.model.deck.exceptions.DuplicateDeckException;

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

    @Test
    public void equals_validUniqueDecksList_true() {
        UniqueDeckList uniqueDeckList1 = new UniqueDeckList();
        uniqueDeckList1.add(new Deck("Deck A"));
        uniqueDeckList1.add(new Deck("Deck B"));

        UniqueDeckList uniqueDeckList2 = new UniqueDeckList();
        uniqueDeckList2.add(new Deck("Deck A"));
        uniqueDeckList2.add(new Deck("Deck B"));

        assertTrue(uniqueDeckList1.equals(uniqueDeckList2));
    }

    @Test
    public void hashCode_validUniqueDecksList_sameHashCode() {
        UniqueDeckList uniqueDeckList1 = new UniqueDeckList();
        uniqueDeckList1.add(new Deck("Deck A"));
        uniqueDeckList1.add(new Deck("Deck B"));

        UniqueDeckList uniqueDeckList2 = new UniqueDeckList();
        uniqueDeckList2.add(new Deck("Deck A"));
        uniqueDeckList2.add(new Deck("Deck B"));

        assertEquals(uniqueDeckList1.hashCode(), uniqueDeckList2.hashCode());
    }

    @Test
    public void setDecks_validReplacement_replacesInternalList() {
        UniqueDeckList originalList = new UniqueDeckList();
        originalList.add(new Deck("Deck 1"));
        originalList.add(new Deck("Deck 2"));

        UniqueDeckList replacementList = new UniqueDeckList();
        replacementList.add(new Deck("Deck A"));
        replacementList.add(new Deck("Deck B"));

        originalList.setDecks(replacementList);

        assertEquals(originalList.asUnmodifiableObservableList(), replacementList.asUnmodifiableObservableList());
    }

    @Test
    public void iterator_success() {
        List<Deck> decks = Arrays.asList(new Deck("Deck 1"),
                new Deck("Deck 2"), new Deck("Deck 3"));
        uniqueDeckList.setDecks(decks);

        int count = 0;
        for (Deck deck : uniqueDeckList) {
            assertEquals(decks.get(count++), deck);
        }
    }
}
