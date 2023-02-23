package seedu.address.model.person.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateOfBirthTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateOfBirth(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new DateOfBirth(invalidDate));
    }

    @Test
    public void isValidBirthDate() {
        // null address
        assertThrows(NullPointerException.class, () -> DateOfBirth.isValidBirthDate(null));

        // invalid date of birth
        assertFalse(DateOfBirth.isValidBirthDate("")); // empty string
        assertFalse(DateOfBirth.isValidBirthDate(" ")); // spaces only
        assertFalse(DateOfBirth.isValidBirthDate("13/11/2023")); // Future date can't be birth date
        assertFalse(DateOfBirth.isValidBirthDate("13.11.2023"));
        assertFalse(DateOfBirth.isValidBirthDate("13-11-2023"));
        assertFalse(DateOfBirth.isValidBirthDate("13/11/23"));
        assertFalse(DateOfBirth.isValidBirthDate("13.11.23"));
        assertFalse(DateOfBirth.isValidBirthDate("13-11-23"));

        // valid date of birth
        assertTrue(DateOfBirth.isValidBirthDate("13/11/2022"));
        assertTrue(DateOfBirth.isValidBirthDate("13.11.2022"));
        assertTrue(DateOfBirth.isValidBirthDate("13-11-2022"));
        assertTrue(DateOfBirth.isValidBirthDate("13/11/22"));
        assertTrue(DateOfBirth.isValidBirthDate("13.11.22"));
        assertTrue(DateOfBirth.isValidBirthDate("13-11-22"));
    }
}
