package trackr.model.order.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import trackr.model.person.CustomerAddress;

public class CustomerPersonAddressTest {
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
        assertThrows(NullPointerException.class, () -> CustomerAddress.isValidPersonAddress(null));

        // invalid addresses
        assertFalse(CustomerAddress.isValidPersonAddress("")); // empty string
        assertFalse(CustomerAddress.isValidPersonAddress(" ")); // spaces only

        // valid addresses
        assertTrue(CustomerAddress.isValidPersonAddress("Blk 456, Den Road, #01-355"));
        assertTrue(CustomerAddress.isValidPersonAddress("-")); // one character
        assertTrue(CustomerAddress.isValidPersonAddress("Leng Inc; San Francisco CA 2349879; USA")); // long address
    }
}
