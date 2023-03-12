package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

class RecurringEventListTest {

    private final RecurringEventList recurringEventList = new RecurringEventList();

    private class RecurringEventStub extends RecurringEvent {

        public RecurringEventStub(String eventName, String day, String startTime, String endTime) {
            super(eventName, DayOfWeek.valueOf(day), LocalTime.parse(startTime), LocalTime.parse(endTime));
        }

        @Override
        public String toString() {
            return this.getEventName();
        }

        public int compareTo(RecurringEvent recurringEvent2) {
            return super.compareTo(recurringEvent2);
        }

    }

    @Test
    void insert_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> recurringEventList.insert(null));
    }

    @Test
    void testToString() {
        recurringEventList.insert(new RecurringEventStub("Biking", "MONDAY", "14:00", "15:00"));
        recurringEventList.insert(new RecurringEventStub("Skiing", "SATURDAY", "09:00", "12:00"));
        recurringEventList.insert(new RecurringEventStub("Canoeing", "WEDNESDAY", "12:00", "17:00"));
        recurringEventList.insert(new RecurringEventStub("Jogging", "MONDAY", "07:00", "08:00"));
        recurringEventList.insert(new RecurringEventStub("Invalid", "MONDAY", "06:00", "08:00"));
        assertEquals("Jogging\n" + "Biking\n" + "Canoeing\n" + "Skiing\n", recurringEventList.toString());
    }

    @Test
    void testListBetweenOcurrence() {
        recurringEventList.insert(new RecurringEventStub("Biking", "MONDAY", "14:00", "15:00"));
        recurringEventList.insert(new RecurringEventStub("Skiing", "SATURDAY", "09:00", "12:00"));
        recurringEventList.insert(new RecurringEventStub("Canoeing", "WEDNESDAY", "12:00", "17:00"));
        recurringEventList.insert(new RecurringEventStub("Jogging", "MONDAY", "07:00", "08:00"));
        recurringEventList.insert(new RecurringEventStub("Invalid", "MONDAY", "06:00", "08:00"));

        RecurringEvent x = new RecurringEventStub("Invalid", "TUESDAY", "06:00", "08:00");

        LocalDateTime startPeriod = LocalDateTime.of(LocalDate.parse("2023-03-06"), LocalTime.parse("06:00"));
        LocalDateTime endPeriod = LocalDateTime.of(LocalDate.parse("2023-03-09"), LocalTime.parse("12:00"));

        assertEquals("Jogging\n" + "Biking\n" + "Canoeing\n",
                recurringEventList.listBetweenOccurence(startPeriod, endPeriod));
    }

}
