package seedu.careflow.model.patient;

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

        // future date can't be date of birth
        assertFalse(DateOfBirth.isValidBirthDate("13/11/2099"));
        assertFalse(DateOfBirth.isValidBirthDate("13.11.2099"));
        assertFalse(DateOfBirth.isValidBirthDate("13-11-2099"));
        assertFalse(DateOfBirth.isValidBirthDate("13/11/99"));
        assertFalse(DateOfBirth.isValidBirthDate("13.11.99"));
        assertFalse(DateOfBirth.isValidBirthDate("13-11-99"));

        // year before 1900 shouldn't be date of birth
        assertFalse(DateOfBirth.isValidBirthDate("13/11/1800"));
        assertFalse(DateOfBirth.isValidBirthDate("13.11.1800"));
        assertFalse(DateOfBirth.isValidBirthDate("13-11-1800"));

        // invalid date format
        assertFalse(DateOfBirth.isValidBirthDate("no date")); // invalid day
        assertFalse(DateOfBirth.isValidBirthDate("32-01-2022")); // invalid day
        assertFalse(DateOfBirth.isValidBirthDate("29-02-2022")); // invalid day
        assertFalse(DateOfBirth.isValidBirthDate("30-02-2022")); // invalid day
        assertFalse(DateOfBirth.isValidBirthDate("31-06-2022")); // invalid day
        assertFalse(DateOfBirth.isValidBirthDate("13-13-2022")); // invalid month


        // valid date of birth
        assertTrue(DateOfBirth.isValidBirthDate("13/11/2022"));
        assertTrue(DateOfBirth.isValidBirthDate("13.11.2022"));
        assertTrue(DateOfBirth.isValidBirthDate("13-11-2022"));
        assertTrue(DateOfBirth.isValidBirthDate("01-01-1900")); // check for boundary date
        assertTrue(DateOfBirth.isValidBirthDate("28-02-2022")); // check for non leap year
        assertTrue(DateOfBirth.isValidBirthDate("29-02-2020")); // check for leap year
        assertTrue(DateOfBirth.isValidBirthDate("13/11/22"));
        assertTrue(DateOfBirth.isValidBirthDate("13.11.22"));
        assertTrue(DateOfBirth.isValidBirthDate("13-11-22"));
    }

    @Test
    public void equals() {
        DateOfBirth dob = new DateOfBirth("19/03/23");
        // null -> returns false
        assertFalse(dob.equals(null));

        // same object -> returns true
        assertTrue(dob.equals(dob));

        // same values -> returns true
        assertTrue(dob.equals(new DateOfBirth("19/03/23")));

        // different values -> return false
        assertFalse(dob.equals("18/03/23"));
    }
}
