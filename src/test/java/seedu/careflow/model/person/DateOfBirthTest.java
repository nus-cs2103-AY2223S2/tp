package seedu.careflow.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.careflow.testutil.Assert.assertThrows;

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

        // empty string date
        assertFalse(DateOfBirth.isValidBirthDate("")); // empty string
        assertFalse(DateOfBirth.isValidBirthDate(" ")); // spaces only

        // future date can't be birth date
        assertFalse(DateOfBirth.isValidBirthDate("13/11/2023"));
        assertFalse(DateOfBirth.isValidBirthDate("13.11.2023"));
        assertFalse(DateOfBirth.isValidBirthDate("13-11-2023"));
        assertFalse(DateOfBirth.isValidBirthDate("13/11/23"));
        assertFalse(DateOfBirth.isValidBirthDate("13.11.23"));
        assertFalse(DateOfBirth.isValidBirthDate("13-11-23"));

        // year before 1900 shouldn't be birth date
        assertFalse(DateOfBirth.isValidBirthDate("13/11/1800"));
        assertFalse(DateOfBirth.isValidBirthDate("13.11.1800"));
        assertFalse(DateOfBirth.isValidBirthDate("13-11-1800"));

        // invalid date format
        assertFalse(DateOfBirth.isValidBirthDate("32-01-2022")); // invalid day
        assertFalse(DateOfBirth.isValidBirthDate("13-13-2022")); // invalid month

        // valid date of birth
        assertTrue(DateOfBirth.isValidBirthDate("13/11/2022"));
        assertTrue(DateOfBirth.isValidBirthDate("13.11.2022"));
        assertTrue(DateOfBirth.isValidBirthDate("13-11-2022"));
        assertTrue(DateOfBirth.isValidBirthDate("13/11/22"));
        assertTrue(DateOfBirth.isValidBirthDate("13.11.22"));
        assertTrue(DateOfBirth.isValidBirthDate("13-11-22"));
    }
}
