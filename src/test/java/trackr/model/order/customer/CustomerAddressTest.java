package trackr.model.order.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CustomerAddressTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CustomerAddress(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new CustomerAddress(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> CustomerAddress.isValidCustomerAddress(null));

        // invalid addresses
        assertFalse(CustomerAddress.isValidCustomerAddress("")); // empty string
        assertFalse(CustomerAddress.isValidCustomerAddress(" ")); // spaces only

        // valid addresses
        assertTrue(CustomerAddress.isValidCustomerAddress("Blk 456, Den Road, #01-355"));
        assertTrue(CustomerAddress.isValidCustomerAddress("-")); // one character
        assertTrue(CustomerAddress.isValidCustomerAddress("Leng Inc; San Francisco CA 2349879; USA")); // long address
    }
}
