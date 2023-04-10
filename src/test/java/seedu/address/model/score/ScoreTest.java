package seedu.address.model.score;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCORE_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCORE_LABEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCORE_VALUE;
import static seedu.address.testutil.TypicalScores.SCORE_2;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ScoreBuilder;

public class ScoreTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Label validLabel = new Label(VALID_SCORE_LABEL);
        ScoreValue validScoreValue = new ScoreValue(VALID_SCORE_VALUE);
        Date validDate = new Date(VALID_SCORE_DATE);

        assertThrows(NullPointerException.class, () -> new Score(null, null, null));
        assertThrows(NullPointerException.class, () -> new Score(validLabel, null, null));
        assertThrows(NullPointerException.class, () -> new Score(null, validScoreValue, null));
        assertThrows(NullPointerException.class, () -> new Score(null, null, validDate));
    }

    @Test
    public void isSameScore() {
        // same object -> return true
        assertTrue(SCORE_2.isSameScore(SCORE_2));

        // null -> return false
        assertFalse(SCORE_2.isSameScore(null));

        //same label, all other attributes different -> return false
        Score editedScore = new ScoreBuilder(SCORE_2).withDate(VALID_SCORE_DATE).withValue(VALID_SCORE_VALUE).build();
        assertFalse(SCORE_2.isSameScore(editedScore));

        //same date, all other attributes different -> return true
        editedScore = new ScoreBuilder(SCORE_2).withValue(VALID_SCORE_VALUE).withLabel(VALID_SCORE_LABEL).build();
        assertTrue(SCORE_2.isSameScore(editedScore));

        //same value, all other attributes different -> return false
        editedScore = new ScoreBuilder(SCORE_2).withValue(VALID_SCORE_VALUE).withDate(VALID_SCORE_DATE).build();
        assertFalse(SCORE_2.isSameScore(editedScore));
    }
}
