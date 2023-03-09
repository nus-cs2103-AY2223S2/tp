package seedu.address.model.score;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ScoreValueTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ScoreValue(null));
    }

    @Test
    public void constructor_invalidScoreValue_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new ScoreValue(""));
    }

    @Test
    public void isValidScore() {
        // null score value
        assertThrows(NullPointerException.class, () -> ScoreValue.isValidScore(null));

        // invalid score value
        assertFalse(ScoreValue.isValidScore("")); // empty string
        assertFalse(ScoreValue.isValidScore(" ")); // spaces only
        assertFalse(ScoreValue.isValidScore("abcd")); // non-numeric
        assertFalse(ScoreValue.isValidScore("1234abcd")); // alphanumeric
        assertFalse(ScoreValue.isValidScore("1234 5678")); // spaces within digits
        assertFalse(ScoreValue.isValidScore("-1")); // negative number
        assertFalse(ScoreValue.isValidScore(".1")); // number with only decimal point
        assertFalse(ScoreValue.isValidScore("0.00")); // number with more than 1 decimal point
        assertFalse(ScoreValue.isValidScore("09")); // number with leading zero
        assertFalse(ScoreValue.isValidScore("101")); // number greater than maximum number
        assertFalse(ScoreValue.isValidScore("100.1")); //number greater than maximum number by decimal

        // valid score value
        assertTrue(ScoreValue.isValidScore("0")); // minimum number
        assertTrue(ScoreValue.isValidScore("0.0")); // minimum number with decimal point
        assertTrue(ScoreValue.isValidScore("20")); // number between 1 to 100
        assertTrue(ScoreValue.isValidScore("20.7")); // number between 1 to 100 with decimal point
        assertTrue(ScoreValue.isValidScore("100")); // maximum number
        assertTrue(ScoreValue.isValidScore("100.0")); //maximum number with decimal point


    }
}
