package seedu.address.model.pet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class OwnerNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new OwnerName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidOwnerName = "";
        assertThrows(IllegalArgumentException.class, () -> new OwnerName(invalidOwnerName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> OwnerName.isValidName(null));

        // invalid name
        assertFalse(OwnerName.isValidName("")); // empty string
        assertFalse(OwnerName.isValidName(" ")); // spaces only
        assertFalse(OwnerName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(OwnerName.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(OwnerName.isValidName("peter jack")); // alphabets only
        assertTrue(OwnerName.isValidName("12345")); // numbers only
        assertTrue(OwnerName.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(OwnerName.isValidName("Capital Tan")); // with capital letters
        assertTrue(OwnerName.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
