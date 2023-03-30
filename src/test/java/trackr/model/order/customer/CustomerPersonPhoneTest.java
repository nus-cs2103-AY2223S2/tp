package trackr.model.order.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import trackr.model.person.CustomerPhone;

public class CustomerPersonPhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CustomerPhone(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new CustomerPhone(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> CustomerPhone.isValidPersonPhone(null));

        // invalid phone numbers
        assertFalse(CustomerPhone.isValidPersonPhone("")); // empty string
        assertFalse(CustomerPhone.isValidPersonPhone(" ")); // spaces only
        assertFalse(CustomerPhone.isValidPersonPhone("91")); // less than 3 numbers
        assertFalse(CustomerPhone.isValidPersonPhone("phone")); // non-numeric
        assertFalse(CustomerPhone.isValidPersonPhone("9011p041")); // alphabets within digits
        assertFalse(CustomerPhone.isValidPersonPhone("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(CustomerPhone.isValidPersonPhone("93121534"));
        assertTrue(CustomerPhone.isValidPersonPhone("911")); // exactly 3 numbers
        assertTrue(CustomerPhone.isValidPersonPhone("124293842033123")); // long phone numbers
    }
}
