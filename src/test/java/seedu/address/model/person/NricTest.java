package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NricTest {

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
        // null nric
        assertThrows(NullPointerException.class, () -> Nric.isValidNric(null));

        // invalid nric
        assertFalse(Nric.isValidNric("")); // empty string
        assertFalse(Nric.isValidNric(" ")); // spaces only
        assertFalse(Nric.isValidNric("^")); // only non-alphanumeric characters
        assertFalse(Nric.isValidNric("S1234*67L")); // contains non-alphanumeric characters
        assertFalse(Nric.isValidNric("Q1234567L")); // first character is not eligible

        // valid nric
        assertTrue(Nric.isValidNric("S1234567P")); // valid NRIC
        assertTrue(Nric.isValidNric("T1234567Q")); // valid NRIC
        assertTrue(Nric.isValidNric("F1234567W")); // valid NRIC
        assertTrue(Nric.isValidNric("G1234567E")); // valid NRIC
        assertTrue(Nric.isValidNric("M1234567M")); // valid NRIC
    }
}
