package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedCard.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCards.VARIABLE;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.card.Answer;
import seedu.address.model.card.Question;

public class JsonAdaptedCardTest {
    private static final String INVALID_NAME = " ";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = VARIABLE.getQuestion().toString();
    private static final String VALID_ADDRESS = VARIABLE.getAnswer().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = VARIABLE.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final String VALID_DECK = VARIABLE.getDeck().toString();
    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedCard person = new JsonAdaptedCard(VARIABLE);
        assertEquals(VARIABLE, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedCard person =
                new JsonAdaptedCard(INVALID_NAME, VALID_ADDRESS, VALID_TAGS, VALID_DECK);
        String expectedMessage = Question.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedCard person = new JsonAdaptedCard(null, VALID_ADDRESS, VALID_TAGS, VALID_DECK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Question.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedCard person =
                new JsonAdaptedCard(VALID_NAME, INVALID_ADDRESS, VALID_TAGS, VALID_DECK);
        String expectedMessage = Answer.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedCard person = new JsonAdaptedCard(VALID_NAME, null, VALID_TAGS, VALID_DECK);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Answer.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedCard person =
                new JsonAdaptedCard(VALID_NAME, VALID_ADDRESS, invalidTags, VALID_DECK);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
