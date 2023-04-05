package seedu.address.model.event.fields;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Test;

import seedu.address.model.event.enums.Interval;

class RecurrenceTest {
    private final Recurrence recurrenceNone = new Recurrence(Interval.NONE);
    private final Recurrence recurrenceDaily = new Recurrence(Interval.DAILY);
    private final Recurrence recurrenceWeekly = new Recurrence(Interval.WEEKLY);
    private final Recurrence recurrenceMonthly = new Recurrence(Interval.MONTHLY);
    private final Recurrence recurrenceYearly = new Recurrence(Interval.YEARLY);

    @Test
    void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Recurrence((String) null));
    }

    @Test
    void constructor_validString() {
        assertEquals(recurrenceNone, new Recurrence("none"));
        assertEquals(recurrenceDaily, new Recurrence("daily"));
        assertEquals(recurrenceWeekly, new Recurrence("weekly"));
        assertEquals(recurrenceMonthly, new Recurrence("monthly"));
        assertEquals(recurrenceYearly, new Recurrence("yearly"));
    }

    @Test
    void constructor_invalidString_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Recurrence(""));
        assertThrows(IllegalArgumentException.class, () -> new Recurrence("Hello"));
    }

    @Test
    void getIncrementUnit() {
        assertEquals(ChronoUnit.MINUTES, recurrenceNone.getIncrementUnit());
        assertEquals(ChronoUnit.DAYS, recurrenceDaily.getIncrementUnit());
        assertEquals(ChronoUnit.WEEKS, recurrenceWeekly.getIncrementUnit());
        assertEquals(ChronoUnit.MONTHS, recurrenceMonthly.getIncrementUnit());
        assertEquals(ChronoUnit.YEARS, recurrenceYearly.getIncrementUnit());
    }

    @Test
    void isValidRecurrence_validString_returnsTrue() {
        assertTrue(Recurrence.isValidRecurrence("none"));
        assertTrue(Recurrence.isValidRecurrence("daily"));
        assertTrue(Recurrence.isValidRecurrence("weekly"));
        assertTrue(Recurrence.isValidRecurrence("monthly"));
        assertTrue(Recurrence.isValidRecurrence("yearly"));
        assertTrue(Recurrence.isValidRecurrence("yEaRlY"));
        assertTrue(Recurrence.isValidRecurrence("YEARLY"));
    }

    @Test
    void isValidRecurrence_invalidString_returnsFalse() {
        assertFalse(Recurrence.isValidRecurrence(""));
        assertFalse(Recurrence.isValidRecurrence("day"));
        assertFalse(Recurrence.isValidRecurrence("d a i l y"));
        assertThrows(NullPointerException.class, () -> Recurrence.isValidRecurrence(null));
    }

    @Test
    void isRecurring() {
        assertTrue(recurrenceDaily.isRecurring());
        assertTrue(recurrenceWeekly.isRecurring());
        assertTrue(recurrenceMonthly.isRecurring());
        assertTrue(recurrenceYearly.isRecurring());
        assertFalse(recurrenceNone.isRecurring());
    }

    @Test
    void testEquals_valid() {
        assertEquals(recurrenceNone, new Recurrence(Recurrence.NONE_CASE));
        assertEquals(recurrenceDaily, new Recurrence(Recurrence.DAILY_CASE));
        assertEquals(recurrenceWeekly, new Recurrence(Recurrence.WEEKLY_CASE));
        assertEquals(recurrenceMonthly, new Recurrence(Recurrence.MONTHLY_CASE));
        assertEquals(recurrenceYearly, new Recurrence(Recurrence.YEARLY_CASE));
    }

    @Test
    void testEquals_invalid() {
        assertNotEquals(recurrenceNone, recurrenceDaily);
        assertNotEquals(recurrenceWeekly, null);
    }
}
