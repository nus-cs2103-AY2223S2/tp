package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AddressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StudentId(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new StudentId(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> StudentId.isValidAddress(null));

        // invalid addresses
        assertFalse(StudentId.isValidAddress("")); // empty string
        assertFalse(StudentId.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(StudentId.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(StudentId.isValidAddress("-")); // one character
        assertTrue(StudentId.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }
}
