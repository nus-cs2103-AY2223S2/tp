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
        assertThrows(NullPointerException.class, () -> Name.isValidFoodName(null));

        // invalid name
        assertFalse(Name.isValidFoodName("")); // empty string
        assertFalse(Name.isValidFoodName(" ")); // spaces only
        assertFalse(Name.isValidFoodName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidFoodName("Meiji*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Name.isValidFoodName("chocolate milk")); // alphabets only
        assertTrue(Name.isValidFoodName("12345")); // numbers only
        assertTrue(Name.isValidFoodName("m3iji")); // alphanumeric characters
        assertTrue(Name.isValidFoodName("Chocolate Milk")); // with capital letters
        assertTrue(Name.isValidFoodName("There is no restriction on length of food name")); // long names
    }
}
