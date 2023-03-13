package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDate));
    }

    @Test
    public void isValidDate() {
        // null date
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // invalid dates
        assertFalse(Date.isValidDate("")); // empty string
        assertFalse(Date.isValidDate(" ")); // spaces only
        assertFalse(Date.isValidDate("1st March 2023")); //invalid date format
        assertFalse(Date.isValidDate("March 1st 2023")); //invalid date format
        assertFalse(Date.isValidDate("2023-0-02")); //invalid date format
        assertFalse(Date.isValidDate("202-01-02")); //invalid date format
        assertFalse(Date.isValidDate("2023-03-1")); //invalid date format
        assertFalse(Date.isValidDate("2023-00-00")); //invalid date
        assertFalse(Date.isValidDate("2023-04-31")); //invalid date

        // valid dates
        assertTrue(Date.isValidDate("2023-03-01"));
        assertTrue(Date.isValidDate("2023-02-05"));
        assertTrue(Date.isValidDate("2023-12-31"));
        assertTrue(Date.isValidDate("2023-02-28"));
        assertTrue(Date.isValidDate("2020-02-29")); //leap year
    }
}
