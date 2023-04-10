package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.SampleDateTimeUtil.FIVE_O_CLOCK_VALID;
import static seedu.address.testutil.SampleDateTimeUtil.FOUR_O_CLOCK_VALID;
import static seedu.address.testutil.SampleDateTimeUtil.MONDAY_SIX_O_CLOCK_VALID;
import static seedu.address.testutil.SampleDateTimeUtil.NINE_O_CLOCK_VALID;
import static seedu.address.testutil.SampleDateTimeUtil.ONE_O_CLOCK_VALID;
import static seedu.address.testutil.SampleDateTimeUtil.THREE_O_CLOCK_VALID;
import static seedu.address.testutil.SampleDateTimeUtil.THURSDAY_TWELVE_O_CLOCK_VALID;
import static seedu.address.testutil.SampleDateTimeUtil.TWELVE_O_CLOCK_VALID;
import static seedu.address.testutil.SampleDateTimeUtil.TWO_O_CLOCK_VALID;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
class RecurringEventListTest {

    private final RecurringEventList recurringEventList = new RecurringEventList();

    private class RecurringEventStub extends RecurringEvent {

        public RecurringEventStub(String eventName, DayOfWeek day, LocalTime startTime, LocalTime endTime) {
            super(eventName, day, startTime, endTime);
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
    void contain() {
        recurringEventList.insert(new RecurringEventListTest.RecurringEventStub("Biking", DayOfWeek.MONDAY,
                TWO_O_CLOCK_VALID.toLocalTime(), THREE_O_CLOCK_VALID.toLocalTime()));
        recurringEventList.insert(new RecurringEventListTest.RecurringEventStub("Skiing", DayOfWeek.MONDAY,
                TWO_O_CLOCK_VALID.toLocalTime(), THREE_O_CLOCK_VALID.toLocalTime()));
        recurringEventList.insert(new RecurringEventListTest.RecurringEventStub("Canoeing", DayOfWeek.MONDAY,
                TWO_O_CLOCK_VALID.toLocalTime(), THREE_O_CLOCK_VALID.toLocalTime()));

        assertFalse(recurringEventList.contain(new RecurringEventListTest.RecurringEventStub("Biking",
                DayOfWeek.WEDNESDAY, TWO_O_CLOCK_VALID.toLocalTime(), THREE_O_CLOCK_VALID.toLocalTime())));

        assertTrue(recurringEventList.contain(new RecurringEventListTest.RecurringEventStub("Canoeing",
                DayOfWeek.MONDAY, TWO_O_CLOCK_VALID.toLocalTime(), THREE_O_CLOCK_VALID.toLocalTime())));
    }

    @Test
    void testToString() {
        recurringEventList.insert(new RecurringEventStub("Biking", DayOfWeek.MONDAY,
                TWO_O_CLOCK_VALID.toLocalTime(), THREE_O_CLOCK_VALID.toLocalTime()));

        recurringEventList.insert(new RecurringEventStub("Skiing", DayOfWeek.SATURDAY,
                NINE_O_CLOCK_VALID.toLocalTime(), TWELVE_O_CLOCK_VALID.toLocalTime()));

        recurringEventList.insert(new RecurringEventStub("Canoeing", DayOfWeek.WEDNESDAY,
                TWELVE_O_CLOCK_VALID.toLocalTime(), FIVE_O_CLOCK_VALID.toLocalTime()));

        recurringEventList.insert(new RecurringEventStub("Invalid", DayOfWeek.MONDAY,
                TWELVE_O_CLOCK_VALID.toLocalTime(), THREE_O_CLOCK_VALID.toLocalTime()));

        assertEquals("Recurring Events\n1. Biking\n" + "2. Canoeing\n" + "3. Skiing\n", recurringEventList.toString());
    }

    @Test
    void testListBetweenOcurrence() {
        recurringEventList.insert(new RecurringEventStub("Biking", DayOfWeek.MONDAY,
                TWO_O_CLOCK_VALID.toLocalTime(), THREE_O_CLOCK_VALID.toLocalTime()));

        recurringEventList.insert(new RecurringEventStub("Skiing", DayOfWeek.SATURDAY,
                NINE_O_CLOCK_VALID.toLocalTime(), TWELVE_O_CLOCK_VALID.toLocalTime()));

        recurringEventList.insert(new RecurringEventStub("Canoeing", DayOfWeek.WEDNESDAY,
                TWELVE_O_CLOCK_VALID.toLocalTime(), FIVE_O_CLOCK_VALID.toLocalTime()));

        recurringEventList.insert(new RecurringEventStub("Invalid", DayOfWeek.MONDAY,
                TWELVE_O_CLOCK_VALID.toLocalTime(), THREE_O_CLOCK_VALID.toLocalTime()));

        LocalDateTime startPeriod = MONDAY_SIX_O_CLOCK_VALID;
        LocalDateTime endPeriod = THURSDAY_TWELVE_O_CLOCK_VALID;

        assertEquals("Biking\n" + "Canoeing\n",
                recurringEventList.listBetweenOccurrence(startPeriod, endPeriod));
    }

    @Test
    void testInsert() {
        recurringEventList.insert(new RecurringEventStub("Biking", DayOfWeek.MONDAY,
                TWO_O_CLOCK_VALID.toLocalTime(), THREE_O_CLOCK_VALID.toLocalTime()));

        recurringEventList.insert(new RecurringEventStub("Skiing", DayOfWeek.SATURDAY,
                NINE_O_CLOCK_VALID.toLocalTime(), TWELVE_O_CLOCK_VALID.toLocalTime()));

    }

    @Test
    void testcheckClashingRecurringEvent() {
        recurringEventList.insert(new RecurringEventStub("Biking", DayOfWeek.MONDAY,
                TWO_O_CLOCK_VALID.toLocalTime(), THREE_O_CLOCK_VALID.toLocalTime()));

        String expected = recurringEventList.checkClashingRecurringEvent(new RecurringEventStub("Swimming",
                DayOfWeek.MONDAY, ONE_O_CLOCK_VALID.toLocalTime(), FOUR_O_CLOCK_VALID.toLocalTime()));

        assertEquals("Event Conflict in Recurring Event List\n" + "1. Biking", expected);

        expected = recurringEventList.checkClashingRecurringEvent(new RecurringEventStub("Swimming",
                DayOfWeek.MONDAY, THREE_O_CLOCK_VALID.toLocalTime(), FOUR_O_CLOCK_VALID.toLocalTime()));

        assertEquals(null, expected);

    }

}
