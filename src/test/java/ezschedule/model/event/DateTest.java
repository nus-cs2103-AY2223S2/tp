package ezschedule.model.event;

import static ezschedule.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        // null phone number
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // invalid phone numbers
        assertFalse(Date.isValidDate("")); // empty string
        assertFalse(Date.isValidDate(" ")); // spaces only
        assertFalse(Date.isValidDate("2023-06")); // less than 3 numbers
        assertFalse(Date.isValidDate("twenty-twenty-three")); // non-numeric
        assertFalse(Date.isValidDate("t2023-06-02")); // alphabets within digits
        assertFalse(Date.isValidDate("2023 06 02")); // no dashes

        // valid date
        assertTrue(Date.isValidDate("2023-06-02")); // exactly 3 numbers
    }
}
