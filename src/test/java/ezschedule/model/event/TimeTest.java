package ezschedule.model.event;

import static ezschedule.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class TimeTest {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    private final Time time = new Time(LocalTime.now().format(formatter));

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

        // valid addresses
        assertTrue(Time.isValidTime("18:00"));
    }

    @Test
    public void isPastTime() {
        // time has passed
        assertTrue(time.isPastTime());
    }
}
