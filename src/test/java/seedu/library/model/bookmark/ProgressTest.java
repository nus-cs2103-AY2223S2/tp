package seedu.library.model.bookmark;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.library.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ProgressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Progress(null));
    }

    @Test
    public void constructor_invalidProgress_throwsIllegalArgumentException() {
        String[] invalidProgress = {"", "a", "!"};
        assertThrows(IllegalArgumentException.class, () -> new Progress(invalidProgress));
    }

    @Test
    public void isValidProgress() {
        // null progress
        assertThrows(NullPointerException.class, () -> Progress.isValidProgress(null));

        // invalid progress
        assertFalse(Progress.isValidProgress(new String[]{" ", " ", " "})); // empty string
        assertFalse(Progress.isValidProgress(new String[]{"a", "*", "!"})); // non-numeric
        assertFalse(Progress.isValidProgress(new String[]{"~", "~", "~"})); // all ~
        // valid progress
        assertTrue(Progress.isValidProgress(new String[]{"1", "65", "24"})); // all numeric
        assertTrue(Progress.isValidProgress(new String[]{"~", "32", "~"})); // numerics + ~
    }
}
