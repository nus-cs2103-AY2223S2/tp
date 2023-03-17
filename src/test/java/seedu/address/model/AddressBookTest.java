package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_PHOTOSYNTHESIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HARD;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCards.LOOP;
import static seedu.address.testutil.TypicalCards.getTypicalMasterDeck;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.card.Card;
import seedu.address.model.card.exceptions.DuplicatePersonException;
import seedu.address.model.deck.Deck;
import seedu.address.testutil.CardBuilder;

public class AddressBookTest {

    private final MasterDeck addressBook = new MasterDeck();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getCardList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        MasterDeck newData = getTypicalMasterDeck();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two cards with the same identity fields
        Card editedAlice = new CardBuilder(LOOP).withAnswer(VALID_ANSWER_PHOTOSYNTHESIS).withTags(VALID_TAG_HARD)
                .build();
        List<Card> newCards = Arrays.asList(LOOP, editedAlice);
        MasterDeckStub newData = new MasterDeckStub(newCards);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasCard(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasCard(LOOP));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addCard(LOOP);
        assertTrue(addressBook.hasCard(LOOP));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addCard(LOOP);
        Card editedAlice = new CardBuilder(LOOP).withAnswer(VALID_ANSWER_PHOTOSYNTHESIS).withTags(VALID_TAG_HARD)
                .build();
        assertTrue(addressBook.hasCard(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getCardList().remove(0));
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
