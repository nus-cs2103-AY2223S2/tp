package seedu.address.model.shared;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DatetimeTest {

    @Test
    void testValidDate() {
        String futureDate = LocalDate.now().plusYears(2).format(DateTimeFormatter.ISO_LOCAL_DATE);
        Datetime dt = new Datetime(futureDate);
        Optional<Long> timestamp = dt.getTimestamp();
        assertTrue(timestamp.isPresent());
    }
    @Test
    void testValidDatetime() {
        Datetime dt = new Datetime("2023-03-27T15:30:00");
        Optional<Long> timestamp = dt.getTimestamp();
        assertTrue(timestamp.isPresent());
    }
    @Test
    void testValidDatetimeHumanFormat() {
        Datetime dt = new Datetime("2023-03-27 23:30:00");
        Optional<Long> timestamp = dt.getTimestamp();
        assertTrue(timestamp.isPresent());
    }

    @Test
    void testNullInput() {
        Datetime dt = new Datetime(null);
        Optional<Long> timestamp = dt.getTimestamp();
        assertFalse(timestamp.isPresent());
    }

    @Test
    void testInvalidFormat() {
        Datetime dt = new Datetime("2023-02-27T15:30");
        Optional<Long> timestamp = dt.getTimestamp();
        assertFalse(timestamp.isPresent());
    }

    @Test
    void testPastDate() {
        Datetime dt = new Datetime("2020-01-01");
        Optional<Long> timestamp = dt.getTimestamp();
        assertFalse(timestamp.isPresent());
    }

    @Test
    void testValidDatetimeHumanFormatZone() {
        ZoneId zoneId = ZoneId.systemDefault();
        String futureDateTime = LocalDateTime.now(zoneId).plusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Datetime dt = new Datetime(futureDateTime);
        Optional<Long> timestamp = dt.getTimestamp();
        assertTrue(timestamp.isPresent());
    }
}