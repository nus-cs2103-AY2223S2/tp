package arb.model.project;

import static arb.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TitleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Title(null));
    }

    @Test
    public void constructor_invalid_throwsIllegalArgumentException() {
        String invalidTitle = "";
        assertThrows(IllegalArgumentException.class, () -> new Title(invalidTitle));
    }

    @Test
    public void isValidTitle() {
        // null title
        assertThrows(NullPointerException.class, () -> Title.isValidTitle(null));

        // invalid titles
        assertFalse(Title.isValidTitle("")); // empty string
        assertFalse(Title.isValidTitle(" ")); // only whitespace
        assertFalse(Title.isValidTitle("*=")); // only non-alphanumeric characters
        assertFalse(Title.isValidTitle("hehe%")); // contains non-alphanumeric characters

        // valid titles
        assertTrue(Title.isValidTitle("Large Tree")); // alphabets only
        assertTrue(Title.isValidTitle("13")); // numbers only
        assertTrue(Title.isValidTitle("Large Tree num 3")); // alphanumerics
        assertTrue(Title.isValidTitle("I want a Large Tree near a lake on the 5th hill")); // long title
    }

}
