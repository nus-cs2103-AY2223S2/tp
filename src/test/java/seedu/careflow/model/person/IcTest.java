package seedu.careflow.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.careflow.testutil.Assert.assertThrows;

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
        assertFalse(Ic.isValidIc("a")); // length of ic is not 9
        assertFalse(Ic.isValidIc("a123x")); // length of ic is not 9
        assertFalse(Ic.isValidIc("a12345678")); // ic is not end with character
        assertFalse(Ic.isValidIc("a123456bb")); // ic format is invalid
        assertFalse(Ic.isValidIc("a123t567b")); // ic format is invalid
        assertFalse(Ic.isValidIc("a123456*b")); // special character show up

        // valid ic
        assertTrue(Ic.isValidIc("a1234567b")); // case insensitive
        assertTrue(Ic.isValidIc("A1234567B")); // case insensitive
        assertTrue(Ic.isValidIc("a1234567z"));
        assertTrue(Ic.isValidIc("A7654321Z"));
    }
}
