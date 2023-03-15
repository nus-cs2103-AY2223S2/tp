package seedu.wife.commons.core.food;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.wife.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.wife.model.food.Unit;

public class UnitTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Unit(null));
    }

    @Test
    public void constructor_invalidUnit_throwsIllegalArgumentException() {
        String invalidUnit = "";
        assertThrows(IllegalArgumentException.class, () -> new Unit(invalidUnit));
    }

    @Test
    public void isValid() {
        // null Unit number
        assertThrows(NullPointerException.class, () -> Unit.isValid(null));

        // invalid Unit numbers
        assertFalse(Unit.isValid("")); // empty string
        assertFalse(Unit.isValid(" ")); // spaces only
        assertFalse(Unit.isValid("u2312nit")); // digits within alphabets

        // valid Unit numbers
        assertTrue(Unit.isValid("Unit"));
        assertTrue(Unit.isValid("unit"));
    }
}
