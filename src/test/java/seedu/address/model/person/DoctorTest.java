package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
public class DoctorTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidDoctor_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Doctor(invalidName));
    }

    @Test
    public void isValidDoctor() {
        // null name
        assertThrows(NullPointerException.class, () -> Doctor.isValidDoctor(null));

        // invalid name
        assertFalse(Doctor.isValidDoctor("")); // empty string
        assertFalse(Doctor.isValidDoctor(" ")); // spaces only
        assertFalse(Doctor.isValidDoctor("^")); // only non-alphanumeric characters
        assertFalse(Doctor.isValidDoctor("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Doctor.isValidDoctor("peter jack")); // alphabets only
        assertTrue(Doctor.isValidDoctor("12345")); // numbers only
        assertTrue(Doctor.isValidDoctor("peter the 2nd")); // alphanumeric characters
        assertTrue(Doctor.isValidDoctor("Capital Tan")); // with capital letters
        assertTrue(Doctor.isValidDoctor("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
