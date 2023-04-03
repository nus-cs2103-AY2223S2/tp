package seedu.address.model.timetable.time;

import static seedu.address.model.timetable.util.TypicalTime.ONE_PM;
import static seedu.address.model.timetable.util.TypicalTime.TEN_AM;

import org.joda.time.LocalTime;

import seedu.address.model.time.Day;
import seedu.address.model.time.TimeBlock;
import seedu.address.model.time.TimePeriod;

/**
 * Creates Time Periods.
 */
public class TimePeriodBuilder {
    public static final LocalTime DEFAULT_START_TIME = TEN_AM;
    public static final LocalTime DEFAULT_END_TIME = ONE_PM;
    public static final Day DEFAULT_DAY = Day.THURSDAY;

    private LocalTime startTime;
    private LocalTime endTime;
    private Day day;

    /**
     * Creates a TimePeriodBuilder with default values.
     */
    public TimePeriodBuilder() {
        startTime = DEFAULT_START_TIME;
        endTime = DEFAULT_END_TIME;
        day = DEFAULT_DAY;
    }

    /**
     * Creates a TimePeriodBuilder with details from a {@code timePeriod}.
     */
    public TimePeriodBuilder(TimePeriod timePeriod) {
        startTime = timePeriod.getStartTime();
        endTime = timePeriod.getEndTime();
        day = timePeriod.getSchoolDay();
    }

    /**
     * Creates a new TimePeriodBuilder with an updated start time.
     */
    public TimePeriodBuilder withStartTime(LocalTime startTime) {
        this.startTime = startTime;
        return this;
    }

    /**
     * Creates a new TimePeriodBuilder with an updated end time.
     */
    public TimePeriodBuilder withEndTime(LocalTime endTime) {
        this.endTime = endTime;
        return this;
    }

    /**
     * Creates a new TimePeriodBuilder with a duration.
     */
    public TimePeriodBuilder withDuration(int hours) {
        endTime = startTime.plusHours(hours);
        return this;
    }

    /**
     * Creates a new TimePeriodBuilder with an updated day.
     */
    public TimePeriodBuilder withDay(Day day) {
        this.day = day;
        return this;
    }

    /**
     * Builds a new TimePeriod based on the information given.
     */
    public TimePeriod build() {
        if (!startTime.isBefore(endTime)) {
            return new TimeBlock(DEFAULT_START_TIME, DEFAULT_END_TIME, DEFAULT_DAY);
        }

        return new TimeBlock(startTime, endTime, day);
    }
}
