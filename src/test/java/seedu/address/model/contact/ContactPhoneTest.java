package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;


public class ContactPhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ContactPhone(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new ContactPhone(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> ContactPhone.isValidPhone(null));

        // invalid phone numbers
        assertFalse(ContactPhone.isValidPhone("")); // empty string
        assertFalse(ContactPhone.isValidPhone(" ")); // spaces only
        assertFalse(ContactPhone.isValidPhone("91")); // less than 3 numbers
        assertFalse(ContactPhone.isValidPhone("phone")); // non-numeric
        assertFalse(ContactPhone.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(ContactPhone.isValidPhone("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(ContactPhone.isValidPhone("911")); // exactly 3 numbers
        assertTrue(ContactPhone.isValidPhone("93121534"));
        assertTrue(ContactPhone.isValidPhone("124293842033123")); // long phone numbers
    }
}
