package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.SampleDateTimeUtil.FOUR_O_CLOCK_VALID;
import static seedu.address.testutil.SampleDateTimeUtil.MONDAY_SIX_O_CLOCK_VALID;
import static seedu.address.testutil.SampleDateTimeUtil.THREE_O_CLOCK_VALID;
import static seedu.address.testutil.SampleDateTimeUtil.THURSDAY_TWELVE_O_CLOCK_VALID;
import static seedu.address.testutil.SampleDateTimeUtil.TWELVE_O_CLOCK_VALID;
import static seedu.address.testutil.SampleDateTimeUtil.TWO_O_CLOCK_VALID;

import java.time.DayOfWeek;

import org.junit.jupiter.api.Test;

public class RecurringEventTest {

    @Test
    void occurBetween_test() {
        RecurringEvent event = new RecurringEvent("Biking", DayOfWeek.MONDAY,
                TWELVE_O_CLOCK_VALID.toLocalTime(), THREE_O_CLOCK_VALID.toLocalTime());

        assertTrue(event.occursBetween(MONDAY_SIX_O_CLOCK_VALID, THURSDAY_TWELVE_O_CLOCK_VALID));
    }

    @Test
    void notOccurBetween_test() {
        RecurringEvent event = new RecurringEvent("Skiing", DayOfWeek.SATURDAY,
                TWO_O_CLOCK_VALID.toLocalTime(), FOUR_O_CLOCK_VALID.toLocalTime());

        assertFalse(event.occursBetween(MONDAY_SIX_O_CLOCK_VALID, THURSDAY_TWELVE_O_CLOCK_VALID));
    }

    @Test
    void toString_test() {
        RecurringEvent event1 = new RecurringEvent("Biking", DayOfWeek.MONDAY,
                TWELVE_O_CLOCK_VALID.toLocalTime(), THREE_O_CLOCK_VALID.toLocalTime());

        RecurringEvent event2 = new RecurringEvent("Skiing", DayOfWeek.SATURDAY,
                TWO_O_CLOCK_VALID.toLocalTime(), FOUR_O_CLOCK_VALID.toLocalTime());

        String expected1 = "Biking on MONDAY from 12:00 to 15:00";
        String expected2 = "Skiing on SATURDAY from 14:00 to 16:00";
        assertEquals(expected1, event1.toString());
        assertEquals(expected2, event2.toString());
    }
}
