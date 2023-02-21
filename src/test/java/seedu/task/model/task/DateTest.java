package seedu.task.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.task.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "invalid date";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDate));
    }

    @Test
    public void isValidDate() {
        // null Date
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // blank Date
        assertFalse(Date.isValidDate("")); // empty string
        assertFalse(Date.isValidDate(" ")); // spaces only

        // invalid Date format
        assertFalse(Date.isValidDate("2023-01-01")); // missing time field
        assertFalse(Date.isValidDate("2023-01-01 ")); // missing time field
        assertFalse(Date.isValidDate("2023-01-01 0")); // invalid time field
        assertFalse(Date.isValidDate("2023-01-01 00")); // invalid time field
        assertFalse(Date.isValidDate("2023-01-01 000")); // invalid time field
        assertFalse(Date.isValidDate("2023-01-01 0 0")); // invalid time field
        assertFalse(Date.isValidDate("2023-01-01 0 0 0")); // invalid time field
        assertFalse(Date.isValidDate("2023-01-01 000")); // invalid time field
        assertFalse(Date.isValidDate("2023-01-01  000")); // invalid time field
        assertFalse(Date.isValidDate("2023-02-0 0000")); // missing date field
        assertFalse(Date.isValidDate("2023-0-00 0000")); // missing date field
        assertFalse(Date.isValidDate("202-02-00 0000")); // missing date field
        assertFalse(Date.isValidDate("2023-02-31_0000")); // underscore in local part
        assertFalse(Date.isValidDate("2023-02-0+0000")); // plus in local part

        // invalid Dates
        assertFalse(Date.isValidDate("2023-02-31 0000")); // 31st Feb does not exist

        // valid Date
        assertTrue(Date.isValidDate(" 2023-01-01 1800")); // input is trimmed.
        assertTrue(Date.isValidDate("2023-01-01 1800")); // time must be at least 4 characters and max of 6
        assertTrue(Date.isValidDate("2023-02-01 18000")); // time must be at least 4 characters and max of 6
        assertTrue(Date.isValidDate("2023-02-01 180000")); // time must be at least 4 characters and max of 6
        assertTrue(Date.isValidDate("2023-02-01 0000 2023-02-01 1800")); // only the first valid date is parsed
    }
}
