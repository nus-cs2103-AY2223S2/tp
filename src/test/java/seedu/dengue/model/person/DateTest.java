package seedu.dengue.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.dengue.testutil.Assert.assertThrows;

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

        // blank date
        assertFalse(Date.isValidDate("")); // empty string
        assertFalse(Date.isValidDate(" ")); // spaces only

        // incorrect format
        assertFalse(Date.isValidDate("2000/01/01")); // incorrect separators
        assertFalse(Date.isValidDate("$2000-01-01")); // special characters
        assertFalse(Date.isValidDate("01-01-2000")); // incorrect order
        assertFalse(Date.isValidDate("2000-1-01")); // single-digit month
        assertFalse(Date.isValidDate("2000-01-1")); // single-digit day
        assertFalse(Date.isValidDate("23-01-01")); // two-digit year

        // missing parts
        assertFalse(Date.isValidDate("1999-12")); // missing day
        assertFalse(Date.isValidDate("1999--31")); // missing month
        assertFalse(Date.isValidDate("-12-12")); // missing year

        // invalid parts
        assertFalse(Date.isValidDate("2000-11-32")); // invalid day
        assertFalse(Date.isValidDate("2023-02-29")); // invalid day given month
        assertFalse(Date.isValidDate("2000-13-13")); // invalid month
        assertFalse(Date.isValidDate("-2000-11-32")); // invalid year

        // valid date
        assertTrue(Date.isValidDate("1999-04-26"));

    }
}
