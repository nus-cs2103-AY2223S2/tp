package seedu.address.model.score;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null));
    }

    @Test
    public void constructor_invalidDate_throwsBadDateException() {
        assertThrows(IllegalArgumentException.class, () -> new Date(""));
    }

    @Test
    public void isValidDate() {
        // null date
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // invalid date
        assertFalse(Date.isValidDate("")); // empty string
        assertFalse(Date.isValidDate(" ")); // spaces only
        assertFalse(Date.isValidDate("22-03-2020")); // wrong format
        assertFalse(Date.isValidDate("2020/03/22")); // wrong delimiter
        assertFalse(Date.isValidDate("2020-03-32")); // invalid day
        assertFalse(Date.isValidDate("2020-13-22")); // invalid month
        assertFalse(Date.isValidDate("2020-03-22 12:00")); // time included
        assertFalse(Date.isValidDate("abcdefg")); // random string

        // valid date
        assertTrue(Date.isValidDate("2020-03-22")); // correct format
    }

    @Test
    public void isFutureDate() {
        // null date
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // Future date
        assertFalse(Date.isFutureDate("2023-09-08")); // future date

        // valid date
        assertTrue(Date.isFutureDate("2020-03-22")); // correct format
    }
}
