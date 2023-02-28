package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PostalTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Postal(null));
    }

    @Test
    public void constructor_invalidPostal_throwsIllegalArgumentException() {
        String invalidPostal = "";
        assertThrows(IllegalArgumentException.class, () -> new Postal(invalidPostal));
    }

    @Test
    public void isValidPostal() {
        // null postal number
        assertThrows(NullPointerException.class, () -> Postal.isValidPostal(null));

        // invalid postal numbers
        assertFalse(Postal.isValidPostal("")); // empty string
        assertFalse(Postal.isValidPostal(" ")); // spaces only
        assertFalse(Postal.isValidPostal("91")); // less than 3 numbers
        assertFalse(Postal.isValidPostal("postal")); // non-numeric
        assertFalse(Postal.isValidPostal("9011p041")); // alphabets within digits
        assertFalse(Postal.isValidPostal("9312 1534")); // spaces within digits

        // valid postal numbers
        assertTrue(Postal.isValidPostal("911")); // exactly 3 numbers
        assertTrue(Postal.isValidPostal("93121534"));
        assertTrue(Postal.isValidPostal("124293842033123")); // long postal numbers
    }
}
