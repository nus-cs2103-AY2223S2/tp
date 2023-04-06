package ezschedule.model.event;

import org.junit.jupiter.api.Test;

import static ezschedule.logic.commands.CommandTestUtil.VALID_NAME_A;
import static ezschedule.logic.commands.CommandTestUtil.VALID_NAME_B;
import static ezschedule.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        RecurFactor recurFactorA = new RecurFactor(VALID);
        Name nameB = new Name(VALID_NAME_B);

        // same object -> returns true
        assertTrue(nameA.equals(nameA));

        // same object -> returns true
        Name nameCopy = new Name(VALID_NAME_A);
        assertTrue(nameA.equals(nameCopy));

        // null -> returns false
        assertFalse(nameA.equals(null));

        // different type -> returns false
        assertFalse(nameA.equals(5));

        // different name -> returns false
        assertFalse(nameA.equals(nameB));
    }
}
