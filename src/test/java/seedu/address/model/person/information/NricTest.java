package seedu.address.model.person.information;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class NricTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Nric(null));
    }

    @Test
    public void constructor_invalidNric_throwsIllegalArgumentException() {
        String invalidNric = "";
        assertThrows(IllegalArgumentException.class, () -> new Nric(invalidNric));
    }

    @Test
    public void isValidNric() {
        // null address
        assertThrows(NullPointerException.class, () -> Nric.isValidNric(null));

        // invalid NRIC
        assertFalse(Nric.isValidNric("")); // empty string
        assertFalse(Nric.isValidNric(" ")); // spaces only
        assertFalse(Nric.isValidNric("hello")); // too little characters
        assertFalse(Nric.isValidNric("hellokitty123")); // too much characters
        // invalid NRIC, wrong formats
        assertFalse(Nric.isValidNric("X2233233M")); // wrong prefix
        assertFalse(Nric.isValidNric("TAAAAAAAB")); // middle 7 not numbers
        assertFalse(Nric.isValidNric("S1234567@"));

        // valid NRIC, one each for the allowed prefix
        assertTrue(Nric.isValidNric("T1234567C"));
        assertTrue(Nric.isValidNric("S2345678B")); // 9 characters
        assertTrue(Nric.isValidNric("F1234567A"));
        assertTrue(Nric.isValidNric("G1234567Z"));
        assertTrue(Nric.isValidNric("M1234567B"));

    }

}
