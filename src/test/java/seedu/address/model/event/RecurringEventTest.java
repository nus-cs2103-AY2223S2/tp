package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class RecurringEventTest {
    private class RecurringEventStub extends RecurringEvent {

        public RecurringEventStub(String eventName, String day, String startTime, String endTime) {
            super(eventName, DayOfWeek.valueOf(day), LocalTime.parse(startTime), LocalTime.parse(endTime));
        }

    }

    private RecurringEvent event1 = new RecurringEventStub("Biking", "MONDAY", "14:00", "15:00");
    private RecurringEvent event2 = new RecurringEventStub("Skiing", "SATURDAY", "09:00", "12:00");
    private LocalDateTime startPeriod = LocalDateTime.of(LocalDate.parse("2023-03-06"), LocalTime.parse("06:00"));
    private LocalDateTime endPeriod = LocalDateTime.of(LocalDate.parse("2023-03-09"), LocalTime.parse("12:00"));

    @Test
    void occurBetween_test() {
        assertTrue(event1.occursBetween(startPeriod, endPeriod));
    }

    @Test
    void notOccurBetween_test() {
        assertFalse(event2.occursBetween(startPeriod, endPeriod));
    }

    @Test
    void toString_test() {
        String expected1 = "Biking on MONDAY from 14:00 to 15:00";
        String expected2 = "Skiing on SATURDAY from 09:00 to 12:00";
        assertEquals(expected1, event1.toString());
        assertEquals(expected2, event2.toString());
    }
}
