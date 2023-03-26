package seedu.wife.model.food;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.wife.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValid() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValid(null));

        // invalid name
        assertFalse(Name.isValid("")); // empty string
        assertFalse(Name.isValid(" ")); // spaces only
        assertFalse(Name.isValid("^")); // only non-alphanumeric characters
        assertFalse(Name.isValid("Meiji*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Name.isValid("chocolate milk")); // alphabets only
        assertTrue(Name.isValid("12345")); // numbers only
        assertTrue(Name.isValid("m3iji")); // alphanumeric characters
        assertTrue(Name.isValid("Chocolate Milk")); // with capital letters
        assertTrue(Name.isValid("There is no restriction on length of food name")); // long names
    }
}
