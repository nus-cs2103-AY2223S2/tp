package seedu.address.model.card;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_PHOTOSYNTHESIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_PHOTOSYNTHESIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HARD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MEDIUM;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCards.LOOP;
import static seedu.address.testutil.TypicalCards.PHOTOSYNTHESIS;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class CardTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Card card = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> card.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(LOOP.isSameCard(LOOP));

        // null -> returns false
        assertFalse(LOOP.isSameCard(null));

        // same name, all other attributes different -> returns true
        Card editedAlice = new PersonBuilder(LOOP)
                .withAddress(VALID_ANSWER_PHOTOSYNTHESIS).withTags(VALID_TAG_HARD).build();
        assertTrue(LOOP.isSameCard(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(LOOP).withName(VALID_NAME_PHOTOSYNTHESIS).build();
        assertFalse(LOOP.isSameCard(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Card editedBob = new PersonBuilder(PHOTOSYNTHESIS).withName(VALID_NAME_PHOTOSYNTHESIS.toLowerCase()).build();
        assertFalse(PHOTOSYNTHESIS.isSameCard(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_PHOTOSYNTHESIS + " ";
        editedBob = new PersonBuilder(PHOTOSYNTHESIS).withName(nameWithTrailingSpaces).build();
        assertFalse(PHOTOSYNTHESIS.isSameCard(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Card aliceCopy = new PersonBuilder(LOOP).build();
        assertTrue(LOOP.equals(aliceCopy));

        // same object -> returns true
        assertTrue(LOOP.equals(LOOP));

        // null -> returns false
        assertFalse(LOOP.equals(null));

        // different type -> returns false
        assertFalse(LOOP.equals(5));

        // different card -> returns false
        assertFalse(LOOP.equals(PHOTOSYNTHESIS));

        // different name -> returns false
        Card editedAlice = new PersonBuilder(LOOP).withName(VALID_NAME_PHOTOSYNTHESIS).build();
        assertFalse(LOOP.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PersonBuilder(LOOP).withAddress(VALID_ANSWER_PHOTOSYNTHESIS).build();
        assertFalse(LOOP.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(LOOP).withTags(VALID_TAG_MEDIUM).build();
        assertFalse(LOOP.equals(editedAlice));
    }
}
