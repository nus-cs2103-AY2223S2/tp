package seedu.address.model.person.information;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class NRICTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NRIC(null));
    }

    @Test
    public void constructor_invalidAge_throwsIllegalArgumentException() {
        String invalidNRIC = "";
        assertThrows(IllegalArgumentException.class, () -> new NRIC(invalidNRIC));
    }

    @Test
    public void isValidNRIC() {
        // null address
        assertThrows(NullPointerException.class, () -> NRIC.isValidNRIC(null));

        // invalid NRIC
        assertFalse(NRIC.isValidNRIC("")); // empty string
        assertFalse(NRIC.isValidNRIC(" ")); // spaces only
        assertFalse(NRIC.isValidNRIC("hello")); // too little characters
        assertFalse(NRIC.isValidNRIC("hellokitty123")); // too much characters

        // valid NRIC
        assertTrue(NRIC.isValidNRIC("T1234567C"));
        assertTrue(NRIC.isValidNRIC("S2345678B")); // 9 characters
    }

}
