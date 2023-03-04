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
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new Age(invalidAddress));
    }

    @Test
    public void isValidAge() {
        // null address
        assertThrows(NullPointerException.class, () -> Age.isValidAge(null));

        // invalid addresses
        assertFalse(Age.isValidAge("")); // empty string
        assertFalse(Age.isValidAge(" ")); // spaces only

        // valid addresses
        assertTrue(Age.isValidAge("Blk 456, Den Road, #01-355"));
        assertTrue(Age.isValidAge("-")); // one character
        assertTrue(Age.isValidAge("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }
}
