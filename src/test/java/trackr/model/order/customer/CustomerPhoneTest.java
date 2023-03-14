package trackr.model.order.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CustomerPhoneTest {

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
        assertThrows(NullPointerException.class, () -> CustomerPhone.isValidCustomerPhone(null));

        // invalid phone numbers
        assertFalse(CustomerPhone.isValidCustomerPhone("")); // empty string
        assertFalse(CustomerPhone.isValidCustomerPhone(" ")); // spaces only
        assertFalse(CustomerPhone.isValidCustomerPhone("91")); // less than 3 numbers
        assertFalse(CustomerPhone.isValidCustomerPhone("phone")); // non-numeric
        assertFalse(CustomerPhone.isValidCustomerPhone("9011p041")); // alphabets within digits
        assertFalse(CustomerPhone.isValidCustomerPhone("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(CustomerPhone.isValidCustomerPhone("911")); // exactly 3 numbers
        assertTrue(CustomerPhone.isValidCustomerPhone("93121534"));
        assertTrue(CustomerPhone.isValidCustomerPhone("124293842033123")); // long phone numbers
    }
}
