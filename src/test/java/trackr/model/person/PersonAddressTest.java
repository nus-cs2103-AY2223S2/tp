package trackr.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PersonAddressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PersonAddress(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new PersonAddress(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> PersonAddress.isValidPersonAddress(null));

        // invalid addresses
        assertFalse(PersonAddress.isValidPersonAddress("")); // empty string
        assertFalse(PersonAddress.isValidPersonAddress(" ")); // spaces only

        // valid addresses
        assertTrue(PersonAddress.isValidPersonAddress("Blk 456, Den Road, #01-355"));
        assertTrue(PersonAddress.isValidPersonAddress("-")); // one character
        // long address
        assertTrue(PersonAddress.isValidPersonAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA"));
    }

    @Test
    public void equals() {
        PersonAddress personAddress = new PersonAddress("Blk 456, Den Road, #01-355");
        PersonAddress differentPersonAddress =
                new PersonAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA");

        assertTrue(personAddress.equals(personAddress)); //same object
        assertTrue(personAddress.equals(new PersonAddress("Blk 456, Den Road, #01-355"))); //same address

        assertFalse(personAddress.equals(null)); //null
        assertFalse(personAddress.equals(differentPersonAddress)); //different address
        assertFalse(personAddress.equals(1)); //different type
    }

    @Test
    public void hashCode_success() {
        String address = "Test Address";
        assertEquals(address.hashCode(), new PersonAddress(address).hashCode());
    }

}
