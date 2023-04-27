package seedu.wife.model.food;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.wife.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

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
    public void isUnitNotLengthy() {
        assertFalse(Unit.isUnitNotLengthy("abcdefghijklm"));
        assertTrue(Unit.isUnitNotLengthy("abcd"));
    }

    @Test
    public void isValid() {
        // null Unit number
        assertThrows(NullPointerException.class, () -> Unit.isValidUnit(null));

        // invalid Unit numbers
        assertFalse(Unit.isValidUnit("")); // empty string
        assertFalse(Unit.isValidUnit(" ")); // spaces only
        assertFalse(Unit.isValidUnit("u2312nit")); // digits within alphabets

        // valid Unit numbers
        assertTrue(Unit.isValidUnit("Unit"));
        assertTrue(Unit.isValidUnit("unit"));
    }
}
