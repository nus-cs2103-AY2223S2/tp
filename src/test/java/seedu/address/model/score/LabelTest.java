package seedu.address.model.score;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LabelTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Label(null));
    }

    @Test
    public void constructor_invalidTitle_throwsIllegalArgumentException() {
        String invalidTitle = "";
        assertThrows(IllegalArgumentException.class, () -> new Label(invalidTitle));
    }

    @Test
    public void isValidTitle() {
        // null title
        assertThrows(NullPointerException.class, () -> Label.isValidLabel(null));

        // invalid title
        assertFalse(Label.isValidLabel("")); // empty string
        assertFalse(Label.isValidLabel(" ")); // spaces only
        assertFalse(Label.isValidLabel("^")); // only non-alphanumeric characters
        assertFalse(Label.isValidLabel("SA2*")); // contains non-alphanumeric characters

        // valid title
        assertTrue(Label.isValidLabel("final assessment")); // alphabets only
        assertTrue(Label.isValidLabel("12345")); // numbers only
        assertTrue(Label.isValidLabel("SA2")); // alphanumeric characters
        assertTrue(Label.isValidLabel("Final Assessment")); // with capital letters
        assertTrue(Label.isValidLabel("Final Assessment For CS2103")); // long names
    }

}
