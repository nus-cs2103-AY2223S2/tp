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
        assertThrows(NullPointerException.class, () -> Label.isValidTitle(null));

        // invalid title
        assertFalse(Label.isValidTitle("")); // empty string
        assertFalse(Label.isValidTitle(" ")); // spaces only
        assertFalse(Label.isValidTitle("^")); // only non-alphanumeric characters
        assertFalse(Label.isValidTitle("SA2*")); // contains non-alphanumeric characters

        // valid title
        assertTrue(Label.isValidTitle("final assessment")); // alphabets only
        assertTrue(Label.isValidTitle("12345")); // numbers only
        assertTrue(Label.isValidTitle("SA2")); // alphanumeric characters
        assertTrue(Label.isValidTitle("Final Assessment")); // with capital letters
        assertTrue(Label.isValidTitle("Final Assessment For CS2103")); // long names
    }

}
