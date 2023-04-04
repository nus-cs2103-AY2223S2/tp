package seedu.techtrack.model.role;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.techtrack.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Contact(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new Contact(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> Contact.isValidContact(null));

        // invalid phone numbers
        assertFalse(Contact.isValidContact("")); // empty string
        assertFalse(Contact.isValidContact(" ")); // spaces only
        assertFalse(Contact.isValidContact("91")); // less than 3 numbers
        assertFalse(Contact.isValidContact("phone")); // non-numeric
        assertFalse(Contact.isValidContact("9011p041")); // alphabets within digits
        assertFalse(Contact.isValidContact("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(Contact.isValidContact("911")); // exactly 3 numbers
        assertTrue(Contact.isValidContact("93121534"));
        assertTrue(Contact.isValidContact("124293842033123")); // long phone numbers
    }
}
