package seedu.address.model.score;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCORE_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCORE_LABEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCORE_VALUE;

import org.junit.jupiter.api.Test;

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
}
