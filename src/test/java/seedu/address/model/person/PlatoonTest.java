package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PlatoonTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Platoon(null));
    }

    @Test
    public void constructor_invalidPlatoon_throwsIllegalArgumentException() {
        String invalidPlatoon = "!@#";
        assertThrows(IllegalArgumentException.class, () -> new Platoon(invalidPlatoon));
    }

    @Test
    public void isValidPlatoon() {
        // null platoon
        assertThrows(NullPointerException.class, () -> Platoon.isValidPlatoon(null));

        // invalid platoon
        assertFalse(Platoon.isValidPlatoon("")); // empty string
        assertFalse(Platoon.isValidPlatoon("*&^%$#@!")); // not alphanumeric

        // valid platoon
        assertTrue(Platoon.isValidPlatoon("Team bravo 123")); // alphanumeric
        assertTrue(Platoon.isValidPlatoon("N/A")); // slashes are allowed
    }
}
