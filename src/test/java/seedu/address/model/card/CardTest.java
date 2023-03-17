package seedu.address.model.card;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_PHOTOSYNTHESIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DECK_SCIENCE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_PHOTOSYNTHESIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_MEDIUM;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCards.LOOP;
import static seedu.address.testutil.TypicalCards.PHOTOSYNTHESIS;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CardBuilder;

public class CardTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Card card = new CardBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> card.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(LOOP.isSameCard(LOOP));

        // null -> returns false
        assertFalse(LOOP.isSameCard(null));

        // same question and answer, all other attributes different -> returns true
        Card editedLoop = new CardBuilder(LOOP).withTags(VALID_TAG_MEDIUM).withDeck(VALID_DECK_SCIENCE)
                .build();
        assertTrue(LOOP.isSameCard(editedLoop));

        // different question, all other attributes same -> returns false
        editedLoop = new CardBuilder(LOOP).withQuestion(VALID_QUESTION_PHOTOSYNTHESIS).build();
        assertFalse(LOOP.isSameCard(editedLoop));

        // different answer, all other attributes same -> returns false
        editedLoop = new CardBuilder(LOOP).withAnswer(VALID_ANSWER_PHOTOSYNTHESIS).build();
        assertFalse(LOOP.isSameCard(editedLoop));

        // question differs in case, all other attributes same -> returns false
        Card editedPhotosynthesis = new CardBuilder(PHOTOSYNTHESIS)
                .withQuestion(VALID_QUESTION_PHOTOSYNTHESIS.toLowerCase())
                .build();
        assertFalse(PHOTOSYNTHESIS.isSameCard(editedPhotosynthesis));

        // question has trailing spaces, all other attributes same -> returns false
        String questionWithTrailingSpaces = VALID_QUESTION_PHOTOSYNTHESIS + " ";
        editedPhotosynthesis = new CardBuilder(PHOTOSYNTHESIS).withQuestion(questionWithTrailingSpaces).build();
        assertFalse(PHOTOSYNTHESIS.isSameCard(editedPhotosynthesis));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Card loopCopy = new CardBuilder(LOOP).build();
        assertTrue(LOOP.equals(loopCopy));

        // same object -> returns true
        assertTrue(LOOP.equals(LOOP));

        // null -> returns false
        assertFalse(LOOP.equals(null));

        // different type -> returns false
        assertFalse(LOOP.equals(5));

        // different card -> returns false
        assertFalse(LOOP.equals(PHOTOSYNTHESIS));

        // different question -> returns false
        Card editedLoop = new CardBuilder(LOOP).withQuestion(VALID_QUESTION_PHOTOSYNTHESIS).build();
        assertFalse(LOOP.equals(editedLoop));

        // different answer -> returns false
        editedLoop = new CardBuilder(LOOP).withAnswer(VALID_ANSWER_PHOTOSYNTHESIS).build();
        assertFalse(LOOP.equals(editedLoop));

        // different tags -> returns false
        editedLoop = new CardBuilder(LOOP).withTags(VALID_TAG_MEDIUM).build();
        assertFalse(LOOP.equals(editedLoop));
    }
}
