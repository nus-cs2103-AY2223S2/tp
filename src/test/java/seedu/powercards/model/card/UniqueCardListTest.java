package seedu.powercards.model.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.powercards.logic.commands.CommandTestUtil.VALID_ANSWER_GRAVITY;
import static seedu.powercards.logic.commands.CommandTestUtil.VALID_TAG_HARD;
import static seedu.powercards.logic.commands.CommandTestUtil.VALID_TAG_MEDIUM;
import static seedu.powercards.testutil.Assert.assertThrows;
import static seedu.powercards.testutil.TypicalCards.LOOP;
import static seedu.powercards.testutil.TypicalCards.PHOTOSYNTHESIS;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.powercards.model.card.exceptions.CardNotFoundException;
import seedu.powercards.model.card.exceptions.DuplicateCardException;
import seedu.powercards.testutil.CardBuilder;

public class UniqueCardListTest {

    private final UniqueCardList uniqueCardList = new UniqueCardList();

    @Test
    public void contains_nullCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCardList.contains(null));
    }

    @Test
    public void contains_cardNotInList_returnsFalse() {
        assertFalse(uniqueCardList.contains(LOOP));
    }

    @Test
    public void contains_cardInList_returnsTrue() {
        uniqueCardList.add(LOOP);
        assertTrue(uniqueCardList.contains(LOOP));
    }

    @Test
    public void contains_cardWithSameIdentityFieldsInList_returnsTrue() {
        uniqueCardList.add(LOOP);
        Card editedLoop = new CardBuilder(LOOP).withTag(VALID_TAG_MEDIUM).build();
        assertTrue(uniqueCardList.contains(editedLoop));
    }

    @Test
    public void add_nullCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCardList.add(null));
    }

    @Test
    public void add_duplicateCard_throwsDuplicateCardException() {
        uniqueCardList.add(LOOP);
        assertThrows(DuplicateCardException.class, () -> uniqueCardList.add(LOOP));
    }

    @Test
    public void setCard_nullTargetCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCardList.setCard(null, LOOP));
    }

    @Test
    public void setCard_nullEditedCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCardList.setCard(LOOP, null));
    }

    @Test
    public void setCard_targetCardNotInList_throwsCardNotFoundException() {
        assertThrows(CardNotFoundException.class, () -> uniqueCardList.setCard(LOOP, LOOP));
    }

    @Test
    public void setCard_editedCardIsSameCard_success() {
        uniqueCardList.add(LOOP);
        uniqueCardList.setCard(LOOP, LOOP);
        UniqueCardList expectedUniqueCardList = new UniqueCardList();
        expectedUniqueCardList.add(LOOP);
        assertEquals(expectedUniqueCardList, uniqueCardList);
    }

    @Test
    public void setCard_editedCardHasSameIdentity_success() {
        uniqueCardList.add(LOOP);
        Card editedGravity = new CardBuilder(LOOP).withAnswer(VALID_ANSWER_GRAVITY).withTag(VALID_TAG_HARD)
                .build();
        uniqueCardList.setCard(LOOP, editedGravity);
        UniqueCardList expectedUniqueCardList = new UniqueCardList();
        expectedUniqueCardList.add(editedGravity);
        assertEquals(expectedUniqueCardList, uniqueCardList);
    }

    @Test
    public void setCard_editedCardHasDifferentIdentity_success() {
        uniqueCardList.add(LOOP);
        uniqueCardList.setCard(LOOP, PHOTOSYNTHESIS);
        UniqueCardList expectedUniqueCardList = new UniqueCardList();
        expectedUniqueCardList.add(PHOTOSYNTHESIS);
        assertEquals(expectedUniqueCardList, uniqueCardList);
    }

    @Test
    public void setCard_editedCardHasNonUniqueIdentity_throwsDuplicateCardException() {
        uniqueCardList.add(LOOP);
        uniqueCardList.add(PHOTOSYNTHESIS);
        assertThrows(DuplicateCardException.class, () -> uniqueCardList.setCard(LOOP, PHOTOSYNTHESIS));
    }

    @Test
    public void remove_nullCard_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCardList.remove(null));
    }

    @Test
    public void remove_cardDoesNotExist_throwsCardNotFoundException() {
        assertThrows(CardNotFoundException.class, () -> uniqueCardList.remove(LOOP));
    }

    @Test
    public void remove_existingCard_removesCard() {
        uniqueCardList.add(LOOP);
        uniqueCardList.remove(LOOP);
        UniqueCardList expectedUniqueCardList = new UniqueCardList();
        assertEquals(expectedUniqueCardList, uniqueCardList);
    }

    @Test
    public void setCards_nullUniqueCardList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCardList.setCards((UniqueCardList) null));
    }

    @Test
    public void setCards_uniqueCardList_replacesOwnListWithProvidedUniqueCardList() {
        uniqueCardList.add(LOOP);
        UniqueCardList expectedUniqueCardList = new UniqueCardList();
        expectedUniqueCardList.add(PHOTOSYNTHESIS);
        uniqueCardList.setCards(expectedUniqueCardList);
        assertEquals(expectedUniqueCardList, uniqueCardList);
    }

    @Test
    public void setCards_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCardList.setCards((List<Card>) null));
    }

    @Test
    public void setCards_list_replacesOwnListWithProvidedList() {
        uniqueCardList.add(LOOP);
        List<Card> cardList = Collections.singletonList(PHOTOSYNTHESIS);
        uniqueCardList.setCards(cardList);
        UniqueCardList expectedUniqueCardList = new UniqueCardList();
        expectedUniqueCardList.add(PHOTOSYNTHESIS);
        assertEquals(expectedUniqueCardList, uniqueCardList);
    }

    @Test
    public void setCards_listWithDuplicateCards_throwsDuplicateCardException() {
        List<Card> listWithDuplicateCards = Arrays.asList(LOOP, LOOP);
        assertThrows(DuplicateCardException.class, () -> uniqueCardList.setCards(listWithDuplicateCards));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueCardList.asUnmodifiableObservableList().remove(0));
    }
}
