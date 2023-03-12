package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

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
        // invalid addresses
        assertFalse(Address.isValidAddress("")); // empty string
        assertFalse(Address.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(Address.isValidAddress("Punggol"));
        assertTrue(Address.isValidAddress("Pasir Ris"));
        assertTrue(Address.isValidAddress("Sixth Avenue"));

        // wrong case but still works
        assertTrue(Address.isValidAddress("punGGol"));
        assertTrue(Address.isValidAddress("pasir ris"));
        assertTrue(Address.isValidAddress("SIXTH avenue"));

        // untrimmed still works
        assertTrue(Address.isValidAddress("   Punggol"));
        assertTrue(Address.isValidAddress("Pasir Ris   "));

        // wrong spacing
        assertFalse(Address.isValidAddress("Pasir  Ris"));
        assertFalse(Address.isValidAddress("   Sixth     Avenue  "));

        // looks valid, but is wrong
        assertFalse(Address.isValidAddress("Pungol"));
        assertFalse(Address.isValidAddress("Paris Ris"));
        assertFalse(Address.isValidAddress("Fifth Avenue"));
    }
}
