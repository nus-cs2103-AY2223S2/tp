package trackr.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PersonPhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PersonPhone(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new PersonPhone(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> PersonPhone.isValidPersonPhone(null));

        // invalid phone numbers
        assertFalse(PersonPhone.isValidPersonPhone("")); // empty string
        assertFalse(PersonPhone.isValidPersonPhone(" ")); // spaces only
        assertFalse(PersonPhone.isValidPersonPhone("91")); // less than 3 numbers
        assertFalse(PersonPhone.isValidPersonPhone("phone")); // non-numeric
        assertFalse(PersonPhone.isValidPersonPhone("9011p041")); // alphabets within digits
        assertFalse(PersonPhone.isValidPersonPhone("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(PersonPhone.isValidPersonPhone("911")); // exactly 3 numbers
        assertTrue(PersonPhone.isValidPersonPhone("93121534"));
        assertTrue(PersonPhone.isValidPersonPhone("124293842033123")); // long phone numbers
    }

    @Test
    public void equals() {
        PersonPhone personPhone = new PersonPhone("911");
        PersonPhone differentPersonPhone = new PersonPhone("12345");

        assertTrue(personPhone.equals(personPhone)); //same object
        assertTrue(personPhone.equals(new PersonPhone("911"))); //same phone

        assertFalse(personPhone.equals(null)); //null
        assertFalse(personPhone.equals(differentPersonPhone)); //different phone
        assertFalse(personPhone.equals(1)); //different type
    }

    @Test
    public void hashCode_success() {
        String phone = "123";
        assertEquals(phone.hashCode(), new PersonPhone(phone).hashCode());
    }

}
