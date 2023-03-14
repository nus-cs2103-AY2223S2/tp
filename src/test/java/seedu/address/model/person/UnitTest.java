package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class UnitTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Unit(null));
    }

    @Test
    public void constructor_invalidUnit_throwsIllegalArgumentException() {
        String invalidUnit = "!@#";
        assertThrows(IllegalArgumentException.class, () -> new Unit(invalidUnit));
    }

    @Test
    public void isValidUnit() {
        // null unit
        assertThrows(NullPointerException.class, () -> Unit.isValidUnit(null));

        // invalid unit
        assertFalse(Unit.isValidUnit("")); // empty string
        assertFalse(Unit.isValidUnit("*&^%$#@!")); // not alphanumeric

        // valid unit
        assertTrue(Unit.isValidUnit("Team bravo 123")); // alphanumeric
        assertTrue(Unit.isValidUnit("N/A")); // slashes are allowed
    }
}
