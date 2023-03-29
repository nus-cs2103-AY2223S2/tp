package seedu.address.model.opening;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.ultron.model.opening.Date;

public class DateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null, "2023-01-01"));
        assertThrows(NullPointerException.class, () -> new Date("OA", null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidKey = "";
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new Date("OA", invalidDate));
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidKey, "2023-01-01"));
    }

    @Test
    public void isValidDate() {
        // null keydate
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // invalid keydate
        assertFalse(Date.isValidDate("")); // empty string
        assertFalse(Date.isValidDate(" ")); // spaces only
        assertFalse(Date.isValidDate("@2023-01-01")); // missing key
        assertFalse(Date.isValidDate(" @2023-01-01")); // missing key

        assertFalse(Date.isValidDate("OA@")); // missing date
        assertFalse(Date.isValidDate("OA@ ")); // missing date
        assertFalse(Date.isValidDate("OA2023-01-01")); // missing @
        assertFalse(Date.isValidDate("OA 2023-01-01")); // @ replaced by whitespace

        // invalid key
        assertFalse(Date.isValidDate("")); //
        assertFalse(Date.isValidDate(" ")); //
        assertFalse(Date.isValidDate(" ")); //
        assertFalse(Date.isValidDate(" ")); //
        assertFalse(Date.isValidDate(" ")); //

        // invalid date

        assertFalse(Date.isValidDate("&")); // only non-alphanumeric characters
        assertFalse(Date.isValidDate("123")); // numbers only
        assertFalse(Date.isValidDate("Engineer 10")); // with numbers
        assertFalse(Date.isValidDate("Data-analyst 10")); // with non-alphanumeric characters and numbers

        // valid keydate
        assertTrue(Date.isValidDate("analyst")); // alphabets only
        assertTrue(Date.isValidDate("Intern")); // with capital letters
        assertTrue(Date.isValidDate("data-analyst")); // with non-alphanumeric characters, but no numbers
        assertTrue(Date.isValidDate("analyst & engineer")); // non-alphanumeric characters, no numbers
        assertTrue(Date.isValidDate("software engineer for integrated processing")); // long keydates
    }
}
