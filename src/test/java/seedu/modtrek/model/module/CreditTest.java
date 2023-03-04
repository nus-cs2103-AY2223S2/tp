package seedu.modtrek.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.modtrek.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.modtrek.model.module.Credit;

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
        // null phone number
        assertThrows(NullPointerException.class, () -> Credit.isValidCredit(null));

        // invalid phone numbers
        assertFalse(Credit.isValidCredit("")); // empty string
        assertFalse(Credit.isValidCredit(" ")); // spaces only
        assertFalse(Credit.isValidCredit("91")); // less than 3 numbers
        assertFalse(Credit.isValidCredit("phone")); // non-numeric
        assertFalse(Credit.isValidCredit("9011p041")); // alphabets within digits
        assertFalse(Credit.isValidCredit("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(Credit.isValidCredit("911")); // exactly 3 numbers
        assertTrue(Credit.isValidCredit("93121534"));
        assertTrue(Credit.isValidCredit("124293842033123")); // long phone numbers
    }
}
