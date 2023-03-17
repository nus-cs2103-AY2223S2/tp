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

    /**
     * Checks if current time period is right after the other.
     */
    public boolean isStraightAfter(TimePeriod otherTimePeriod) {
        return this.getStartTime().isEqual(otherTimePeriod.getEndTime())
                && isSameDay(otherTimePeriod);
    }

    /**
     * Checks if current time period is right before the other.
     */
    public boolean isStraightBefore(TimePeriod otherTimePeriod) {
        return this.getEndTime().isEqual(otherTimePeriod.getStartTime())
                && isSameDay(otherTimePeriod);
    }

    /**
     * Checks if the time period is a consecutive period.
     */
    public boolean isConsecutiveWith(TimePeriod otherTimePeriod) {
        return isStraightAfter(otherTimePeriod) || isStraightBefore(otherTimePeriod);
    }

    public boolean isSameDay(TimePeriod otherTimePeriod) {
        return this.getSchoolDay().equals(otherTimePeriod.getSchoolDay());
    }

    public abstract TimePeriod merge(TimePeriod timePeriod);

    public abstract Hours getHoursBetween();

    public SchoolDay getSchoolDay() {
        return schoolDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TimePeriod that = (TimePeriod) o;
        return getStartTime().isEqual(that.getStartTime())
                && getEndTime().isEqual(that.getEndTime())
                && getSchoolDay().equals(that.getSchoolDay());
    }
}
