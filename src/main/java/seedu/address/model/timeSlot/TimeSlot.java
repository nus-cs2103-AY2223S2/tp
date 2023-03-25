package seedu.address.model.timeSlot;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class TimeSlot {
    private static final int duration = 1; // 1 hour slots
    private DayOfWeek day;
    private LocalTime startTime;

    public String toString() {
        return day.toString() + startTime.toString();
    }
}
