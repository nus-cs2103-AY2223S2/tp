package seedu.address.storage;

import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalScores.SCORE_1;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.score.Date;
import seedu.address.model.score.Label;
import seedu.address.model.score.ScoreValue;

public class JsonAdaptedScoreTest {
    private static final String INVALID_LABEL = "Midt@erm";
    private static final String INVALID_VALUE = "99.99";
    private static final String INVALID_DATE = "12-12-2019";

    private static final String VALID_LABEL = SCORE_1.getLabel().toString();
    private static final String VALID_VALUE = SCORE_1.getValue().toString();
    private static final String VALID_DATE = SCORE_1.getDate().toString();

    @Test
    public void toModelType_invalidLabel_throwsIllegalValueException() {
        JsonAdaptedScore score =
                new JsonAdaptedScore(INVALID_LABEL, VALID_VALUE, VALID_DATE);
        String expectedMessage = Label.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, score::toModelType);
    }

    @Test
    public void toModelType_nullLabel_throwsIllegalValueException() {
        JsonAdaptedScore score = new JsonAdaptedScore(null, VALID_VALUE, VALID_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Label.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, score::toModelType);
    }

    @Test
    public void toModelType_invalidValue_throwsIllegalValueException() {
        JsonAdaptedScore score =
                new JsonAdaptedScore(VALID_LABEL, INVALID_VALUE, VALID_DATE);
        String expectedMessage = ScoreValue.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, score::toModelType);
    }

    @Test
    public void toModelType_nullValue_throwsIllegalValueException() {
        JsonAdaptedScore score = new JsonAdaptedScore(VALID_LABEL, null, VALID_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ScoreValue.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, score::toModelType);
    }
    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedScore score =
                new JsonAdaptedScore(VALID_LABEL, VALID_VALUE, INVALID_DATE);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, score::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedScore score = new JsonAdaptedScore(VALID_LABEL, VALID_VALUE, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, score::toModelType);
    }
}
