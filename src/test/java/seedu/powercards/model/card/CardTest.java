package seedu.powercards.model.card;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.powercards.logic.commands.CommandTestUtil.VALID_ANSWER_PHOTOSYNTHESIS;
import static seedu.powercards.logic.commands.CommandTestUtil.VALID_DECK_SCIENCE;
import static seedu.powercards.logic.commands.CommandTestUtil.VALID_QUESTION_PHOTOSYNTHESIS;
import static seedu.powercards.logic.commands.CommandTestUtil.VALID_TAG_MEDIUM;
import static seedu.powercards.testutil.TypicalCards.LOOP;
import static seedu.powercards.testutil.TypicalCards.PHOTOSYNTHESIS;

import org.junit.jupiter.api.Test;

import seedu.powercards.model.deck.Deck;
import seedu.powercards.model.tag.Tag;
import seedu.powercards.model.tag.Tag.TagName;
import seedu.powercards.testutil.CardBuilder;

public class CardTest {

    @Test
    public void constructor_noTag_defaultTag() {
        Card expectedCard = new Card(
                new Question(VALID_QUESTION_PHOTOSYNTHESIS),
                new Answer(VALID_ANSWER_PHOTOSYNTHESIS),
                new Tag(TagName.UNTAGGED),
                new Deck(VALID_DECK_SCIENCE));
        Card cardWithoutTag = new Card(new Question(VALID_QUESTION_PHOTOSYNTHESIS),
                new Answer(VALID_ANSWER_PHOTOSYNTHESIS),
                null,
                new Deck(VALID_DECK_SCIENCE));

        assertEquals(expectedCard, cardWithoutTag);
    }

    @Test
    public void constructor_missingDeck_throwsNullPointerException() {

        assertThrows(NullPointerException.class, () -> new Card(
                new Question(VALID_QUESTION_PHOTOSYNTHESIS),
                new Answer(VALID_ANSWER_PHOTOSYNTHESIS),
                new Tag(TagName.MEDIUM),
                null));
    }

    @Test
    public void isSameCard() {
        // same object -> returns true
        assertTrue(LOOP.isSameCard(LOOP));

        // null -> returns false
        assertFalse(LOOP.isSameCard(null));

        // same question and answer and deck, all other attributes different -> returns true
        Card editedLoop = new CardBuilder(LOOP).withTag(VALID_TAG_MEDIUM).build();
        assertTrue(LOOP.isSameCard(editedLoop));

        // different question, all other attributes same -> returns false
        editedLoop = new CardBuilder(LOOP).withQuestion(VALID_QUESTION_PHOTOSYNTHESIS).build();
        assertFalse(LOOP.isSameCard(editedLoop));

        // different answer, all other attributes same -> returns true
        editedLoop = new CardBuilder(LOOP).withAnswer(VALID_ANSWER_PHOTOSYNTHESIS).build();
        assertTrue(LOOP.isSameCard(editedLoop));

        // question differs in case, all other attributes same -> returns false
        Card editedPhotosynthesis = new CardBuilder(PHOTOSYNTHESIS)
                .withQuestion(VALID_QUESTION_PHOTOSYNTHESIS.toLowerCase())
                .build();
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
        editedLoop = new CardBuilder(LOOP).withTag(VALID_TAG_MEDIUM).build();
        assertFalse(LOOP.equals(editedLoop));

        // different flip attribute -> returns false
        editedLoop = new CardBuilder(LOOP).withFlipAttribute(!LOOP.isFlipped()).build();
        assertNotEquals(editedLoop, LOOP);
    }

    @Test
    public void isInDeck() {
        Deck defaultDeck = new Deck(CardBuilder.DEFAULT_DECK);
        Deck otherDeck = new Deck("Other");
        Card defaultCard = new CardBuilder().withDeck(CardBuilder.DEFAULT_DECK).build();

        assertTrue(defaultCard.isInDeck(defaultDeck));
        assertFalse(defaultCard.isInDeck(otherDeck));
    }
}
