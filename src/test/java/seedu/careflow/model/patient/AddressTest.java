package seedu.careflow.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.careflow.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.careflow.testutil.StringUtil;

public class AddressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Address(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new Address(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> Address.isValidAddress(null));

        // invalid addresses
        assertFalse(Address.isValidAddress("")); // empty string
        assertFalse(Address.isValidAddress(" ")); // spaces only
        // long address exceed 200 char limit
        assertFalse(Address.isValidAddress(StringUtil.generateRandomString(201)));

        // valid addresses
        assertTrue(Address.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(Address.isValidAddress("-")); // one character
        assertTrue(Address.isValidAddress("Leng Inc;1234 Market St;San Francisco CA 2349879;USA")); //long address
        // long address with exactly 200 char
        assertTrue(Address.isValidAddress("a" + StringUtil.generateRandomString(199)));
    }

    @Test
    public void equals() {
        Address address = new Address("Robert Robertson, 1234 NW Bobcat Lane, St. Robert");
        // null -> returns false
        assertFalse(address.equals(null));

        // same object -> returns true
        assertTrue(address.equals(address));

        // same values -> returns true
        assertTrue(address.equals(new Address("Robert Robertson, 1234 NW Bobcat Lane, St. Robert")));

        // different values -> return false
        assertFalse(address.equals("Robert Robertson, 1234 NW Bobcat Lane"));
    }
}
