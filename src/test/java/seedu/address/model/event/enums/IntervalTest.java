package seedu.address.model.event.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.event.fields.Recurrence;

class IntervalTest {

    @Test
    void getValue() {
        assertEquals(Recurrence.NONE_CASE, Interval.NONE.getValue());
        assertEquals(Recurrence.DAILY_CASE, Interval.DAILY.getValue());
        assertEquals(Recurrence.WEEKLY_CASE, Interval.WEEKLY.getValue());
        assertEquals(Recurrence.MONTHLY_CASE, Interval.MONTHLY.getValue());
        assertEquals(Recurrence.YEARLY_CASE, Interval.YEARLY.getValue());
    }

    @Test
    void testToString() {
        assertEquals("one-time", Interval.NONE.toString());
        assertEquals(Recurrence.DAILY_CASE, Interval.DAILY.toString());
        assertEquals(Recurrence.WEEKLY_CASE, Interval.WEEKLY.toString());
        assertEquals(Recurrence.MONTHLY_CASE, Interval.MONTHLY.toString());
        assertEquals(Recurrence.YEARLY_CASE, Interval.YEARLY.toString());
    }
}
