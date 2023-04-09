package seedu.modtrek.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modtrek.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CreditTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Credit(null));
    }

    @Test
    public void constructor_invalidCredit_throwsIllegalArgumentException() {
        String invalidCredit = "";
        assertThrows(IllegalArgumentException.class, () -> new Credit(invalidCredit));
    }

    @Test
    public void isValidCredit() {
        // null credit
        assertThrows(NullPointerException.class, () -> Credit.isValidCredit(null));

        // invalid credits
        assertFalse(Credit.isValidCredit("")); // empty string
        assertFalse(Credit.isValidCredit(" ")); // spaces only
        assertFalse(Credit.isValidCredit("four")); // non-numeric
        assertFalse(Credit.isValidCredit("4 MC")); // alphabets within digits
        assertFalse(Credit.isValidCredit("600"));

        // valid credits
        assertTrue(Credit.isValidCredit("0"));
        assertTrue(Credit.isValidCredit("4"));
        assertTrue(Credit.isValidCredit("8"));
        assertTrue(Credit.isValidCredit("12"));
        assertTrue(Credit.isValidCredit("99"));
    }
}
