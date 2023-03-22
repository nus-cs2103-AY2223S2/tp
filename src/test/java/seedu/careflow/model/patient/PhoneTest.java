package seedu.careflow.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.careflow.testutil.Assert.assertThrows;

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
        assertFalse(Phone.isValidPhone("phone")); // non-numeric
        assertFalse(Phone.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(Phone.isValidPhone("123456789-12345678912")); // long phone numbers exceed 20 char limit

        // valid phone numbers
        assertTrue(Phone.isValidPhone("911")); // exactly 3 numbers
        assertTrue(Phone.isValidPhone("93121534"));
        assertTrue(Phone.isValidPhone("+65-93121534")); // with special character
        assertTrue(Phone.isValidPhone("9312 1534")); // with space
        assertTrue(Phone.isValidPhone("+(65)-9312 1534")); // with special character and space
        assertTrue(Phone.isValidPhone("12429384203312312345")); // phone numbers with 20 digit
        assertTrue(Phone.isValidPhone("+1 4-29382033(23123)")); // phone numbers with 20 digit and special char
    }

    @Test
    public void equals() {
        Phone phone = new Phone("+(65) 1234-5678");
        // null -> returns false
        assertFalse(phone.equals(null));

        // same object -> returns true
        assertTrue(phone.equals(phone));

        // same values -> returns true
        assertTrue(phone.equals(new Phone("+(65) 1234-5678")));

        // different values -> return false
        assertFalse(phone.equals("1234-5678"));
    }
}
