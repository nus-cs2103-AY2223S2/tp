package seedu.address.model.event.fields;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

class DateTimeTest {
    private static final String BAD_FORMAT_STRING = "2022/01/01 01:01";
    private static final String GOOD_FORMAT_STRING = "2022-01-01 0101";
    private static final LocalDateTime TARGET_DATE_TIME = LocalDateTime
            .of(2022, 1, 1, 1, 1);
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    @Test
    public void isValidDateTime_nullString_returnsFalse() {
        assertFalse(DateTime.isValidDateTime(null));
    }

    @Test
    public void isValidDateTime_emptyString_returnsFalse() {
        assertFalse(DateTime.isValidDateTime(""));
        assertFalse(DateTime.isValidDateTime(" "));
    }

    @Test
    public void isValidDateTime_badFormat_returnsFalse() {
        assertFalse(DateTime.isValidDateTime(BAD_FORMAT_STRING));
    }

    @Test
    public void isValidDateTime_correctFormat_returnsTrue() {
        assertTrue(DateTime.isValidDateTime(GOOD_FORMAT_STRING));
        /* Impossible dates are rounded down by LocalDateTime#parse,
         * hence they are still regarded as valid.
         */
        assertTrue(DateTime.isValidDateTime("2023-02-31 0000"));
        assertTrue(DateTime.isValidDateTime("2023-06-31 0000"));
    }

    @Test
    public void createInvalidDateTime_throwsException() {
        assertThrows(NullPointerException.class, () -> new DateTime((String) null));
        assertThrows(DateTimeParseException.class, () -> new DateTime(""));
        assertThrows(DateTimeParseException.class, () -> new DateTime(BAD_FORMAT_STRING));
    }

    @Test
    public void createValidDateTime() {
        assertEquals(TARGET_DATE_TIME, new DateTime(GOOD_FORMAT_STRING).getDateTime());
    }

    @Test
    public void isValidInterval_valid_returnsTrue() {
        DateTime before = new DateTime("2023-01-01 0000");
        DateTime after = new DateTime("2023-01-01 0001");
        assertTrue(DateTime.isValidInterval(before, after));
    }

    @Test
    public void isValidInterval_invalid_returnsFalse() {
        DateTime before = new DateTime("2023-01-01 0000");
        DateTime after = new DateTime("2023-01-01 0001");
        assertFalse(DateTime.isValidInterval(after, before));
        assertFalse(DateTime.isValidInterval(before, before));
    }

    @Test
    public void getIntervalDuration() {
        DateTime before = new DateTime("2023-01-01 0000");
        DateTime after = new DateTime("2023-01-01 0001");
        assertEquals(0, DateTime.getIntervalDuration(before, before, ChronoUnit.MINUTES));
        assertEquals(1, DateTime.getIntervalDuration(before, after, ChronoUnit.MINUTES));
        assertEquals(-1, DateTime.getIntervalDuration(after, before, ChronoUnit.MINUTES));
    }

    @Test
    public void plus_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTime(GOOD_FORMAT_STRING).plus(null));
    }

    @Test
    public void plus_valid() {
        LocalDateTime localDateTime = LocalDateTime.parse(GOOD_FORMAT_STRING, FORMATTER);
        DateTime dateTime = new DateTime(GOOD_FORMAT_STRING);
        dateTime.plus(ChronoUnit.DAYS);
        assertEquals(localDateTime.plusDays(1), dateTime.getDateTime());
    }

    @Test
    public void toString_customFormatter() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(GOOD_FORMAT_STRING, FORMATTER);
        DateTime dateTime = new DateTime(GOOD_FORMAT_STRING);
        assertEquals(localDateTime.format(formatter), dateTime.toString(formatter));
    }

    @Test
    public void toString_correctFormat() {
        assertEquals(GOOD_FORMAT_STRING, new DateTime(GOOD_FORMAT_STRING).toString());
    }

    @Test
    public void equals_allEquivalencePartitions() {
        DateTime targetDateTime = new DateTime(GOOD_FORMAT_STRING);
        assertEquals(targetDateTime, targetDateTime);
        assertEquals(targetDateTime, new DateTime(GOOD_FORMAT_STRING));
        assertNotEquals(targetDateTime, null);
        assertNotEquals(targetDateTime, new Object());
        assertNotEquals(targetDateTime, new DateTime("2023-02-02 0202"));
    }
}
