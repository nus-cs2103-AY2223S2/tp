package seedu.address.model.timeslot;

import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * Class to generate the time slots of the week.
 */
public class TimeSlot {
    private static final int duration = 1; // 1 hour slots
    private DayOfWeek day;
    private LocalTime startTime;

    public String toString() {
        return day.toString() + startTime.toString();
    }
}
