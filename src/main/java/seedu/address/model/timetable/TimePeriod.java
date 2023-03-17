package seedu.address.model.timetable;

import org.joda.time.Hours;
import org.joda.time.LocalTime;

import seedu.address.model.timetable.exceptions.WrongTimeException;

/**
 * Represents a period in time.
 */
public abstract class TimePeriod {

    private final LocalTime startTime;
    private final LocalTime endTime;
    private final SchoolDay schoolDay;

    /**
     * Constructs a period in time.
     */
    public TimePeriod(LocalTime startTime, LocalTime endTime, SchoolDay schoolDay) {
        if (startTime.isAfter(endTime)) {
            throw new WrongTimeException("Start Time Cannot be after End Time!");
        }
        this.startTime = startTime;
        this.endTime = endTime;
        this.schoolDay = schoolDay;
    }


    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public boolean isStraightAfter(TimePeriod otherTimePeriod) {
        return this.getEndTime().isEqual(otherTimePeriod.getStartTime());
    }

    public boolean isStraightBefore(TimePeriod otherTimePeriod) {
        return this.getEndTime().equals(otherTimePeriod.getStartTime());
    }

    public boolean isConsecutiveWith(TimePeriod otherTimePeriod) {
        return isStraightAfter(otherTimePeriod) || isStraightBefore(otherTimePeriod);
    }

    public abstract TimePeriod merge(TimePeriod timePeriod);

    public abstract Hours getHoursBetween();

    public SchoolDay getSchoolDay() {
        return schoolDay;
    }
}
