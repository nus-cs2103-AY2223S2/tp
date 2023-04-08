package ezschedule.model.event;

import static ezschedule.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class RecurFactorTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidRecurFactor_throwsIllegalArgumentException() {
        String invalidRecurFactor = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidRecurFactor));
    }

    @Test
    public void isValidRecurFactor() {
        // null recur factor
        assertThrows(NullPointerException.class, () -> RecurFactor.isValidRecurFactor(null));

        // invalid recur factor
        assertFalse(RecurFactor.isValidRecurFactor("")); // empty string
        assertFalse(RecurFactor.isValidRecurFactor(" ")); // spaces only
        assertFalse(RecurFactor.isValidRecurFactor("^")); // only non-alphanumeric characters
        assertFalse(RecurFactor.isValidRecurFactor("DAY")); // capitalised
        assertFalse(RecurFactor.isValidRecurFactor("day*")); // contains non-alphanumeric characters

        // valid recur factor
        assertTrue(RecurFactor.isValidRecurFactor("day"));
        assertTrue(RecurFactor.isValidRecurFactor("week"));
        assertTrue(RecurFactor.isValidRecurFactor("month"));
    }

    @Test
    public void equals() {
        RecurFactor recurFactorA = new RecurFactor("day");
        RecurFactor recurFactorB = new RecurFactor("month");

        // same object -> returns true
        assertTrue(recurFactorA.equals(recurFactorA));

        // same object -> returns true
        RecurFactor recurFactorCopy = new RecurFactor("day");
        assertTrue(recurFactorA.equals(recurFactorCopy));

        // null -> returns false
        assertFalse(recurFactorA.equals(null));

        // different type -> returns false
        assertFalse(recurFactorA.equals(5));

        // different name -> returns false
        assertFalse(recurFactorA.equals(recurFactorB));
    }
}
