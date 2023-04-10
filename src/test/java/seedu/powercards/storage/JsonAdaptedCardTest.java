package seedu.powercards.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.powercards.storage.JsonAdaptedCard.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.powercards.testutil.Assert.assertThrows;
import static seedu.powercards.testutil.TypicalCards.VARIABLE;

import org.junit.jupiter.api.Test;

import seedu.powercards.commons.exceptions.IllegalValueException;
import seedu.powercards.model.card.Answer;
import seedu.powercards.model.card.Question;

public class JsonAdaptedCardTest {
    private static final String INVALID_QUESTION = " ";
    private static final String INVALID_ANSWER = " ";
    private static final String INVALID_TAG = "#Hard";

    private static final String VALID_QUESTION = VARIABLE.getQuestion().toString();
    private static final String VALID_ANSWER = VARIABLE.getAnswer().toString();
    private static final String VALID_TAG = VARIABLE.getTagName();
    private static final String VALID_DECK = VARIABLE.getDeck().toString();
    @Test
    public void toModelType_validCardDetails_returnsCard() throws Exception {
        JsonAdaptedCard card = new JsonAdaptedCard(VARIABLE);
        assertEquals(VARIABLE, card.toModelType());
    }

    @Test
    public void toModelType_invalidQuestion_throwsIllegalValueException() {
        JsonAdaptedCard card =
                new JsonAdaptedCard(INVALID_QUESTION, VALID_ANSWER, VALID_TAG, VALID_DECK);
        String expectedMessage = Question.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, card::toModelType);
    }

    @Test
    public void toModelType_nullQuestion_throwsIllegalValueException() {
        JsonAdaptedCard card = new JsonAdaptedCard(null, VALID_ANSWER, VALID_TAG, VALID_DECK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Question.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, card::toModelType);
    }

    @Test
    public void toModelType_invalidAnswer_throwsIllegalValueException() {
        JsonAdaptedCard card =
                new JsonAdaptedCard(VALID_QUESTION, INVALID_ANSWER, VALID_TAG, VALID_DECK);
        String expectedMessage = Answer.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, card::toModelType);
    }

    @Test
    public void toModelType_nullAnswer_throwsIllegalValueException() {
        JsonAdaptedCard card = new JsonAdaptedCard(VALID_QUESTION, null, VALID_TAG, VALID_DECK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Answer.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, card::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        JsonAdaptedCard card =
                new JsonAdaptedCard(VALID_QUESTION, VALID_ANSWER, INVALID_TAG, VALID_DECK);
        assertThrows(IllegalValueException.class, card::toModelType);
    }

}
