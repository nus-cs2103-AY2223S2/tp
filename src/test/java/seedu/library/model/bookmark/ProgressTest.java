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
        String invalidProgress = "";
        assertThrows(IllegalArgumentException.class, () -> new Progress(invalidProgress));
    }

    @Test
    public void isValidProgress() {
        // null progress
        assertThrows(NullPointerException.class, () -> Progress.isValidProgress(null));

        // invalid progress
        // currently only accepts alphanumeric values
        assertFalse(Progress.isValidProgress("")); // empty string
        assertFalse(Progress.isValidProgress(" ")); // spaces only
        assertFalse(Progress.isValidProgress("Finish*")); // "*" is not allowed
        // valid progress
        assertTrue(Progress.isValidProgress("Finish")); // single word
        assertTrue(Progress.isValidProgress("In Progress")); // letters and spaces
        assertTrue(Progress.isValidProgress("12345")); // numbers
    }
}
