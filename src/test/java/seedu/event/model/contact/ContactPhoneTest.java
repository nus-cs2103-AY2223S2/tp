package seedu.event.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.event.testutil.Assert.assertThrows;

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
        assertFalse(ContactPhone.isValidPhone("91")); // less than 8 numbers
        assertFalse(ContactPhone.isValidPhone("phone")); // non-numeric
        assertFalse(ContactPhone.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(ContactPhone.isValidPhone("1234567")); // 7 numbers
        assertFalse(ContactPhone.isValidPhone("1234567 ")); // 7 numbers with space
        assertFalse(ContactPhone.isValidPhone("9312 1534")); // spaces within digits
        assertFalse(ContactPhone.isValidPhone("1234567890123456")); // 16 numbers

        // valid phone numbers
        assertTrue(ContactPhone.isValidPhone("93121534"));
        assertTrue(ContactPhone.isValidPhone("12345678"));
        assertTrue(ContactPhone.isValidPhone("123456789")); // 9 numbers
        assertTrue(ContactPhone.isValidPhone("124293842033123")); // 15 numbers
    }
}
