package seedu.address.model.time;

import org.joda.time.Hours;
import org.joda.time.LocalTime;

import seedu.address.model.time.exceptions.WrongTimeException;

/**
 * Represents a time period of a variable amount.
 */
public class TimeBlock extends TimePeriod {

    /**
     * Constructs a period in time.
     */
    public TimeBlock(LocalTime startTime, LocalTime endTime, Day schoolDay) {
        super(startTime, endTime, schoolDay);
    }

    public TimeBlock(TimePeriod timePeriod) {
        super(timePeriod.getStartTime(), timePeriod.getEndTime(), timePeriod.getSchoolDay());
    }

    public LocalTime getStartTime() {
        return super.getStartTime();
    }

    public LocalTime getEndTime() {
        return super.getEndTime();
    }

    @Override
    public TimeBlock merge(TimePeriod period) {
        if (this.isConsecutiveWith(period) && this.getSchoolDay().equals(period.getSchoolDay())) {
            if (this.getStartTime().isBefore(period.getStartTime())) {
                return new TimeBlock(this.getStartTime(), period.getEndTime(), getSchoolDay());
            } else {
                return new TimeBlock(period.getStartTime(), this.getEndTime(), getSchoolDay());
            }
        }
        throw new WrongTimeException("Must be consecutive timeblocks together!");
    }

    @Override
    public Hours getHoursBetween() {
        return Hours.hoursBetween(getStartTime(), getEndTime());
    }
}
