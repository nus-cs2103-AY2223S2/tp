package seedu.dengue.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dengue.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AgeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Age(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAge = "";
        assertThrows(IllegalArgumentException.class, () -> new Age(invalidAge));
    }

    @Test
    public void isValidAge() {
        // null age
        assertThrows(NullPointerException.class, () -> Age.isValidAge(null));

        // invalid age
        assertFalse(Age.isValidAge("")); // empty string
        assertFalse(Age.isValidAge(" ")); // spaces only
        assertFalse(Age.isValidAge("200")); // > 199
        assertFalse(Age.isValidAge("a")); // non numerical
        assertFalse(Age.isValidAge("2@")); // illegal special characters
        assertFalse(Age.isValidAge("#@")); // special characters

        // valid ages
        assertTrue(Age.isValidAge("0")); // Boundary case 0
        assertTrue(Age.isValidAge("1")); // long address
        assertTrue(Age.isValidAge("100"));
        assertTrue(Age.isValidAge("199")); // Boundary case 199
    }
}
