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
        assertFalse(Nric.isValidNric("*1234567A")); // first letter contains non-alphanumeric characters
        assertFalse(Nric.isValidNric("T1234567*")); // last letter contains non-alphanumeric characters
        assertFalse(Nric.isValidNric("A1234567A")); // first letter not S, T, F, G, M
        assertFalse(Nric.isValidNric("01234567A")); // first letter is a number
        assertFalse(Nric.isValidNric("A12345678")); // last letter is a number
        assertFalse(Nric.isValidNric("t1234567A")); // with lowercase letter as first letter
        assertFalse(Nric.isValidNric("T1234567a")); // with lowercase letter as second letter

        // valid name
        assertTrue(Nric.isValidNric("S1234567A")); // First letter S
        assertTrue(Nric.isValidNric("T1234567A")); // First letter T
        assertTrue(Nric.isValidNric("F1234567A")); // First letter F
        assertTrue(Nric.isValidNric("G1234567A")); // First letter G
        assertTrue(Nric.isValidNric("M1234567A")); // First letter M
        assertTrue(Nric.isValidNric("T1234567A")); // with capital letters
    }
}
