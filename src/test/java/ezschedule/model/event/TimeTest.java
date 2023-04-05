package ezschedule.model.event;

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
}
