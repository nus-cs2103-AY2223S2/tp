package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.doctor.Specialty;

public class SpecialtyTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Specialty(null));
    }

    @Test
    public void constructor_invalidSpecialty_throwsIllegalArgumentException() {
        String invalidSpecialty = "";
        assertThrows(IllegalArgumentException.class, () -> new Specialty(invalidSpecialty));
    }

    @Test
    public void isValidSpecialty() {
        // null name
        assertThrows(NullPointerException.class, () -> Specialty.isValidSpecialty(null));

        // invalid name
        assertFalse(Specialty.isValidSpecialty("")); // empty string
        assertFalse(Specialty.isValidSpecialty(" ")); // spaces only
        assertFalse(Specialty.isValidSpecialty("^")); // only non-alphanumeric characters
        assertFalse(Specialty.isValidSpecialty("medicine*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Specialty.isValidSpecialty("general medicine")); // alphabets only
        assertTrue(Specialty.isValidSpecialty("12345")); // numbers only
        assertTrue(Specialty.isValidSpecialty("general medicine 2nd year")); // alphanumeric characters
        assertTrue(Specialty.isValidSpecialty("General Medicine")); // with capital letters
        assertTrue(Specialty.isValidSpecialty("general medicine and family medicine")); // long names
    }
}
