package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.DateTimeException;

import org.junit.jupiter.api.Test;

import seedu.address.model.meeting.exceptions.InvalidEndDateTimeException;

public class DateTimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTime(null));
    }

    @Test
    public void constructor_invalidDateTime_throwsIllegalArgumentException() {
        String invalidDateTime = "";
        assertThrows(IllegalArgumentException.class, () -> new DateTime(invalidDateTime));
    }

    @Test
    public void constructor_invalidDateTime_throwsDateTimeException() {
        // Single date/time
        assertThrows(DateTimeException.class, () -> new DateTime("some random string"));
        assertThrows(DateTimeException.class, () -> new DateTime("32/13/2022 13:22")); // invalid day
        assertThrows(DateTimeException.class, () -> new DateTime("22/13/2022 13:22")); // invalid month
        assertThrows(DateTimeException.class, () -> new DateTime("22/12/20222 13:22")); // invalid year
        assertThrows(DateTimeException.class, () -> new DateTime("22\\12/2022 13:22")); // invalid delimiter '\'

        // Double date/time (start and end)
        assertThrows(DateTimeException.class, () -> new DateTime("some random string", "some random string"));
        assertThrows(DateTimeException.class, () -> new DateTime(
                "12/13/2023 13:22", "12/12/2023 13:22")); // invalid start
        assertThrows(DateTimeException.class, () -> new DateTime(
                "12/12/2023 13:22", "12/13/2023 13:22")); // invalid end

    }

    @Test
    public void constructor_invalidEndDateTime_throwsInvalidEndDateTimeException() {
        assertThrows(InvalidEndDateTimeException.class, () -> new DateTime(
                "12/12/2023 13:22", "12/11/2023 13:22")); // negative duration
        assertThrows(InvalidEndDateTimeException.class, () -> new DateTime(
                "12/12/2023 13:22", "12/12/2023 13:22")); // zero duration
    }

    @Test
    public void isValidDateTime() {
        // null date/time
        assertThrows(NullPointerException.class, () -> DateTime.isValidDateTime(null));

        // invalid dates/times
        assertFalse(DateTime.isValidDateTime("")); // empty string
        assertFalse(DateTime.isValidDateTime(" ")); // spaces only
        assertFalse(DateTime.isValidDateTime(" 22/05/2023")); // starts with a space

        // valid dates/times
        assertTrue(DateTime.isValidDateTime("22/05/2023"));
        assertTrue(DateTime.isValidDateTime("22-05-2023"));
        assertTrue(DateTime.isValidDateTime("22052023"));
        assertTrue(DateTime.isValidDateTime("22/05/2023 13:22"));
        assertTrue(DateTime.isValidDateTime("22-05-2023 13:22"));
        assertTrue(DateTime.isValidDateTime("22052023 13:22"));
        assertTrue(DateTime.isValidDateTime("22/05/2023 1:32am"));
        assertTrue(DateTime.isValidDateTime("22/05/2023 1:32pm"));
        assertTrue(DateTime.isValidDateTime("next thursday")); // NLP
        assertTrue(DateTime.isValidDateTime("this coming friday 2:30pm")); // NLP
    }
}
