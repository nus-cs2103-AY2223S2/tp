package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Phone(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new Phone(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> Phone.isValidPhone(null));

        // invalid phone numbers
        assertFalse(Phone.isValidPhone("")); // empty string
        assertFalse(Phone.isValidPhone(" ")); // spaces only
        assertFalse(Phone.isValidPhone("91")); // less than 3 numbers
        assertFalse(Phone.isValidPhone("9199")); // less than 5 numbers
        assertFalse(Phone.isValidPhone("99999999 99999999 99999999 99999999 9")); // More than 32
        assertFalse(Phone.isValidPhone("hello!")); // Special characters other than _
        assertFalse(Phone.isValidPhone("test username")); // white space
        assertFalse(Phone.isValidPhone("@@@@@")); // Special characters other than _ only
        assertTrue(Phone.isValidPhone("9011p041")); // Telegram Handle allows alphanumeric and underscore
        assertFalse(Phone.isValidPhone("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(Phone.isValidPhone("91551")); // exactly 5 numbers
        assertTrue(Phone.isValidPhone("915516")); // exactly 6 numbers
        assertTrue(Phone.isValidPhone("11111111111111111111111111111111")); // exactly 32 numbers
        assertTrue(Phone.isValidPhone("1111111111111111111111111111111")); // exactly 31 numbers
        assertTrue(Phone.isValidPhone("93121534"));
        assertTrue(Phone.isValidPhone("124293842033123")); // long phone numbers
    }
}
