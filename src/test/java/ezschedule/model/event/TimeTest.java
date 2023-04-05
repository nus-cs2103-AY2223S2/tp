package ezschedule.model.event;

import static ezschedule.logic.commands.CommandTestUtil.VALID_START_TIME_A;
import static ezschedule.logic.commands.CommandTestUtil.VALID_START_TIME_B;
import static ezschedule.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class TimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Time(null));
    }

    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        String invalidTime = "";
        assertThrows(IllegalArgumentException.class, () -> new Time(invalidTime));
    }

    @Test
    public void isValidTime() {
        // null address
        assertThrows(NullPointerException.class, () -> Time.isValidTime(null));

        // invalid addresses
        assertFalse(Time.isValidTime("")); // empty string
        assertFalse(Time.isValidTime(" ")); // spaces only
        assertFalse(Time.isValidTime("18.00")); // not using : as separator
        assertFalse(Time.isValidTime("20:000")); // more than 2 digits
        assertFalse(Time.isValidTime("-00:00")); // negative
        assertFalse(Time.isValidTime("24:00")); // time is only allowed for 00:00 to 23:59

        // valid addresses
        assertTrue(Time.isValidTime("18:00")); // valid time
        assertTrue(Time.isValidTime("00:00")); // earliest possible valid time
        assertTrue(Time.isValidTime("23:58")); // latest possible valid time
    }

    @Test
    public void isPastTime() {
        // current time has passed
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        Time time = new Time(LocalTime.now().format(formatter));
        assertTrue(time.isPastTime());

        // latest possible valid time will not be passed
        time = new Time("23:59");
        assertFalse(time.isPastTime());
    }

    @Test
    public void isBefore() {
        Time time = new Time("12:00");

        // time is before -> returns true
        Time otherTime = new Time("12:01");
        assertTrue(time.isBefore(otherTime));

        // time is the same -> returns false
        otherTime = new Time("12:00");
        assertFalse(time.isBefore(otherTime));

        // time is before -> returns true
        otherTime = new Time("11:59");
        assertFalse(time.isBefore(otherTime));
    }

    @Test
    public void isAfter() {
        Time time = new Time("12:00");

        // time is after -> returns true
        Time otherTime = new Time("11:59");
        assertTrue(time.isAfter(otherTime));

        // time is the same -> returns false
        otherTime = new Time("12:00");
        assertFalse(time.isAfter(otherTime));

        // time is before -> returns true
        otherTime = new Time("12:01");
        assertFalse(time.isAfter(otherTime));
    }

    @Test
    public void equals() {
        // same object -> returns equal
        Time time = new Time(VALID_START_TIME_A);
        assertTrue(time.equals(time));

        // same values -> returns equal
        Time timeCopy = new Time(VALID_START_TIME_A);
        assertTrue(time.equals(timeCopy));

        // null -> returns not equal
        assertFalse(time.equals(null));

        // different type -> returns not equal
        assertFalse(time.equals(5));

        // different date -> returns not equal
        Time differentTime = new Time(VALID_START_TIME_B);
        assertFalse(time.equals(differentTime));
    }
}
