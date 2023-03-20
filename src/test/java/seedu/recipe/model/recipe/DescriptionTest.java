package seedu.recipe.model.recipe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.recipe.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidDesc = "";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidDesc));
    }

    @Test
    public void isValidDesc() {
        // null address
        assertThrows(NullPointerException.class, () -> Description.isValidDesc(null));

        // invalid name
        assertFalse(Description.isValidDesc("")); // empty string
        assertFalse(Description.isValidDesc(" ")); // spaces only
        assertFalse(Description.isValidDesc("^")); // only non-alphanumeric characters
        assertFalse(Description.isValidDesc("corndog*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Description.isValidDesc("corndogs")); // alphabets only
        assertTrue(Description.isValidDesc("12345")); // numbers only
        assertTrue(Description.isValidDesc("corndog 2nd recipe")); // alphanumeric characters
        assertTrue(Description.isValidDesc("Capital Corndogs")); // with capital letters
        assertTrue(Description.isValidDesc("Corndogs coated with butter")); // long names
    }
}
