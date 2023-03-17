package seedu.address.model.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_PHOTOSYNTHESIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HARD;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCards.LOOP;
import static seedu.address.testutil.TypicalCards.PHOTOSYNTHESIS;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.card.exceptions.DuplicatePersonException;
import seedu.address.model.card.exceptions.PersonNotFoundException;
import seedu.address.testutil.CardBuilder;

public class UniqueDeckListTest {

    private final UniqueCardList uniqueCardList = new UniqueCardList();

    @Test
    public void contains_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCardList.contains(null));
    }

    @Test
    public void contains_personNotInList_returnsFalse() {
        assertFalse(uniqueCardList.contains(LOOP));
    }

    @Test
    public void contains_personInList_returnsTrue() {
        uniqueCardList.add(LOOP);
        assertTrue(uniqueCardList.contains(LOOP));
    }

    @Test
    public void contains_personWithSameIdentityFieldsInList_returnsTrue() {
        uniqueCardList.add(LOOP);
        Card editedAlice = new CardBuilder(LOOP).withAnswer(VALID_ANSWER_PHOTOSYNTHESIS).withTags(VALID_TAG_HARD)
                .build();
        assertTrue(uniqueCardList.contains(editedAlice));
    }

    @Test
    public void add_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCardList.add(null));
    }

    @Test
    public void add_duplicatePerson_throwsDuplicatePersonException() {
        uniqueCardList.add(LOOP);
        assertThrows(DuplicatePersonException.class, () -> uniqueCardList.add(LOOP));
    }

    @Test
    public void setPerson_nullTargetPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCardList.setCard(null, LOOP));
    }

    @Test
    public void setPerson_nullEditedPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCardList.setCard(LOOP, null));
    }

    @Test
    public void setPerson_targetPersonNotInList_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueCardList.setCard(LOOP, LOOP));
    }

    @Test
    public void setPerson_editedPersonIsSamePerson_success() {
        uniqueCardList.add(LOOP);
        uniqueCardList.setCard(LOOP, LOOP);
        UniqueCardList expectedUniqueCardList = new UniqueCardList();
        expectedUniqueCardList.add(LOOP);
        assertEquals(expectedUniqueCardList, uniqueCardList);
    }

    @Test
    public void setPerson_editedPersonHasSameIdentity_success() {
        uniqueCardList.add(LOOP);
        Card editedAlice = new CardBuilder(LOOP).withAnswer(VALID_ANSWER_PHOTOSYNTHESIS).withTags(VALID_TAG_HARD)
                .build();
        uniqueCardList.setCard(LOOP, editedAlice);
        UniqueCardList expectedUniqueCardList = new UniqueCardList();
        expectedUniqueCardList.add(editedAlice);
        assertEquals(expectedUniqueCardList, uniqueCardList);
    }

    @Test
    public void setPerson_editedPersonHasDifferentIdentity_success() {
        uniqueCardList.add(LOOP);
        uniqueCardList.setCard(LOOP, PHOTOSYNTHESIS);
        UniqueCardList expectedUniqueCardList = new UniqueCardList();
        expectedUniqueCardList.add(PHOTOSYNTHESIS);
        assertEquals(expectedUniqueCardList, uniqueCardList);
    }

    @Test
    public void setPerson_editedPersonHasNonUniqueIdentity_throwsDuplicatePersonException() {
        uniqueCardList.add(LOOP);
        uniqueCardList.add(PHOTOSYNTHESIS);
        assertThrows(DuplicatePersonException.class, () -> uniqueCardList.setCard(LOOP, PHOTOSYNTHESIS));
    }

    @Test
    public void remove_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCardList.remove(null));
    }

    @Test
    public void remove_personDoesNotExist_throwsPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> uniqueCardList.remove(LOOP));
    }

    @Test
    public void remove_existingPerson_removesPerson() {
        uniqueCardList.add(LOOP);
        uniqueCardList.remove(LOOP);
        UniqueCardList expectedUniqueCardList = new UniqueCardList();
        assertEquals(expectedUniqueCardList, uniqueCardList);
    }

    @Test
    public void setPersons_nullUniquePersonList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCardList.setCards((UniqueCardList) null));
    }

    @Test
    public void setPersons_uniquePersonList_replacesOwnListWithProvidedUniquePersonList() {
        uniqueCardList.add(LOOP);
        UniqueCardList expectedUniqueCardList = new UniqueCardList();
        expectedUniqueCardList.add(PHOTOSYNTHESIS);
        uniqueCardList.setCards(expectedUniqueCardList);
        assertEquals(expectedUniqueCardList, uniqueCardList);
    }

    @Test
    public void setPersons_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCardList.setCards((List<Card>) null));
    }

    @Test
    public void setPersons_list_replacesOwnListWithProvidedList() {
        uniqueCardList.add(LOOP);
        List<Card> cardList = Collections.singletonList(PHOTOSYNTHESIS);
        uniqueCardList.setCards(cardList);
        UniqueCardList expectedUniqueCardList = new UniqueCardList();
        expectedUniqueCardList.add(PHOTOSYNTHESIS);
        assertEquals(expectedUniqueCardList, uniqueCardList);
    }

    @Test
    public void setPersons_listWithDuplicatePersons_throwsDuplicatePersonException() {
        List<Card> listWithDuplicateCards = Arrays.asList(LOOP, LOOP);
        assertThrows(DuplicatePersonException.class, () -> uniqueCardList.setCards(listWithDuplicateCards));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueCardList.asUnmodifiableObservableList().remove(0));
    }
}
