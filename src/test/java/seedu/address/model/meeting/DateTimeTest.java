package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

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
        assertThrows(IllegalArgumentException.class, () -> new DateTime("some random string"));
        assertThrows(IllegalArgumentException.class, () -> new DateTime("32/13/2022 13:22")); // invalid day
        assertThrows(IllegalArgumentException.class, () -> new DateTime("22/13/2022 13:22")); // invalid month
        assertThrows(IllegalArgumentException.class, () -> new DateTime("22/12/20222 13:22")); // invalid year
        assertThrows(IllegalArgumentException.class, () -> new DateTime("22\\12/2022 13:22")); // invalid delimiter '\'

        // Double date/time (start and end)
        assertThrows(IllegalArgumentException.class, () -> new DateTime("some random string", "some random string"));
        assertThrows(IllegalArgumentException.class, () -> new DateTime(
                "12/13/2023 13:22", "12/12/2023 13:22")); // invalid start
        assertThrows(IllegalArgumentException.class, () -> new DateTime(
                "12/12/2023 13:22", "12/13/2023 13:22")); // invalid end

    }

    @Test
    public void constructor_invalidEndDateTime_throwsInvalidEndDateTimeException() {
        assertThrows(IllegalArgumentException.class, () -> new DateTime(
                "12/12/2023 13:22", "12/11/2023 13:22")); // negative duration
        assertThrows(IllegalArgumentException.class, () -> new DateTime(
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
        assertFalse(DateTime.isValidDateTime("22/05/2023 1:32am"));
        assertFalse(DateTime.isValidDateTime("22/05/2023 1:32pm"));
        assertFalse(DateTime.isValidDateTime("22/05/2023 1:32 AM"));
        assertFalse(DateTime.isValidDateTime("22/05/2023 1:32 PM"));
        assertFalse(DateTime.isValidDateTime("next thursday")); // NLP
        assertFalse(DateTime.isValidDateTime("this coming friday 2:30pm")); // NLP
        assertFalse(DateTime.isValidDateTime("22/05/2023 13:32PM"));

        // valid dates/times
        assertTrue(DateTime.isValidDateTime("22052023 13:22"));
        assertTrue(DateTime.isValidDateTime("22-05-2023 13:22"));
        assertTrue(DateTime.isValidDateTime("22/05/2023"));
        assertTrue(DateTime.isValidDateTime("22-05-2023"));
        assertTrue(DateTime.isValidDateTime("22.05.2023"));
        assertTrue(DateTime.isValidDateTime("22052023"));
        assertTrue(DateTime.isValidDateTime("22/05/23"));
        assertTrue(DateTime.isValidDateTime("22-05-23"));
        assertTrue(DateTime.isValidDateTime("22.05.23"));
        assertTrue(DateTime.isValidDateTime("220523"));
        assertTrue(DateTime.isValidDateTime("2205"));
        assertTrue(DateTime.isValidDateTime("22/05"));
        assertTrue(DateTime.isValidDateTime("22.05"));
        assertTrue(DateTime.isValidDateTime("22-05"));
        assertTrue(DateTime.isValidDateTime("22/05/2023 13:22"));
        assertTrue(DateTime.isValidDateTime("22/05/2023 1:32AM"));
        assertTrue(DateTime.isValidDateTime("22/05/2023 12:32PM"));
    }

    @Test
    public void isValidDuration() {
        // null date/time
        assertThrows(NullPointerException.class, () -> DateTime.isValidDuration(null, null));
        assertThrows(NullPointerException.class, () -> DateTime.isValidDuration(LocalDateTime.MAX, null));
        assertThrows(NullPointerException.class, () -> DateTime.isValidDuration(null, LocalDateTime.MAX));

        // invalid dates/times
        assertFalse(DateTime.isValidDuration(LocalDateTime.MAX, LocalDateTime.MAX)); // zero duration
        assertFalse(DateTime.isValidDuration(LocalDateTime.MAX, LocalDateTime.MIN)); // negative duration

        // valid dates/times
        assertTrue(DateTime.isValidDuration(LocalDateTime.MIN, LocalDateTime.MAX));
    }
}
