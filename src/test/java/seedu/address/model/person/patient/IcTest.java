package seedu.address.model.person.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IcTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Ic(null));
    }

    @Test
    public void constructor_invalidGender_throwsIllegalArgumentException() {
        String invalidIc = "";
        assertThrows(IllegalArgumentException.class, () -> new Ic(invalidIc));
    }

    @Test
    public void isValidDrugAllergy() {
        // null
        assertThrows(NullPointerException.class, () -> Ic.isValidIc(null));

        // blank description
        assertFalse(Ic.isValidIc("")); // empty string
        assertFalse(Ic.isValidIc(" ")); // spaces only

        // invalid ic
        assertFalse(Ic.isValidIc("abc123"));
        assertFalse(Ic.isValidIc("a123x"));

        // valid ic
        assertTrue(Ic.isValidIc("a1234567b"));
        assertTrue(Ic.isValidIc("A1234567B"));
    }
}
