package seedu.address.model.event.fields;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

class DateTimeTest {
    private static final String BAD_FORMAT_STRING = "2022/01/01 01:01";
    private static final String GOOD_FORMAT_STRING = "2022-01-01 0101";
    private static final LocalDateTime TARGET_DATE_TIME = LocalDateTime
            .of(2022, 1, 1, 1, 1);

    @Test
    public void isValidDateTime_nullString_returnsFalse() {
        assertFalse(DateTime.isValidDateTime(null));
    }

    @Test
    public void isValidDateTime_emptyString_returnsFalse() {
        assertFalse(DateTime.isValidDateTime(""));
    }

    @Test
    public void isValidDateTime_badFormat_returnsFalse() {
        assertFalse(DateTime.isValidDateTime(BAD_FORMAT_STRING));
    }

    @Test
    public void isValidDateTime_correctFormat_returnsTrue() {
        assertTrue(DateTime.isValidDateTime(GOOD_FORMAT_STRING));
    }

    @Test
    public void createInvalidDateTime_throwsException() {
        assertThrows(NullPointerException.class, () -> new DateTime(null));
        assertThrows(DateTimeParseException.class, () -> new DateTime(""));
        assertThrows(DateTimeParseException.class, () -> new DateTime(BAD_FORMAT_STRING));
    }

    @Test
    public void createValidDateTime() {
        assertEquals(TARGET_DATE_TIME, new DateTime(GOOD_FORMAT_STRING).getDateTime());
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
