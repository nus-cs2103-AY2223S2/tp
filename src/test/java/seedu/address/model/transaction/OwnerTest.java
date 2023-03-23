package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class OwnerTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Owner(null));
    }

    @Test
    public void constructor_invalidOwner_throwsIllegalArgumentException() {
        String invalidOwner = "";
        assertThrows(IllegalArgumentException.class, () -> new Owner(invalidOwner));
    }

    @Test
    public void isValidOwner() {
        // null owner
        assertThrows(NullPointerException.class, () -> Owner.isValidOwner(null));

        // invalid owner
        assertFalse(Owner.isValidOwner("")); // empty string
        assertFalse(Owner.isValidOwner(" ")); // spaces only
        assertFalse(Owner.isValidOwner("^")); // only non-alphanumeric characters
        assertFalse(Owner.isValidOwner("peter*")); // contains non-alphanumeric characters

        // valid owner
        assertTrue(Owner.isValidOwner("peter jack")); // alphabets only
        assertTrue(Owner.isValidOwner("12345")); // numbers only
        assertTrue(Owner.isValidOwner("peter the 2nd")); // alphanumeric characters
        assertTrue(Owner.isValidOwner("Capital Tan")); // with capital letters
        assertTrue(Owner.isValidOwner("David Roger Jackson Ray Jr 2nd")); // long owner names
    }
}
