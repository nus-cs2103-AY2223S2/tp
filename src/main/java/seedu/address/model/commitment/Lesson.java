package seedu.address.model.commitment;

import java.util.ArrayList;
import java.util.Objects;

import org.joda.time.LocalTime;

import seedu.address.model.location.Location;
import seedu.address.model.scheduler.time.Day;
import seedu.address.model.scheduler.time.TimeBlock;
import seedu.address.model.scheduler.time.TimePeriod;


/**
 * Represents a typical NUS lesson.
 */
public class Lesson extends Commitment {
    private final String moduleCode;

    /**
     * Constructs a lesson.
     */
    public Lesson(String moduleCode, LocalTime startTime, LocalTime endTime, Day schoolDay, Location location) {
        super(location, new TimeBlock(startTime, endTime, schoolDay));
        this.moduleCode = moduleCode;
    }

    public Lesson(String moduleCode, Location location, TimePeriod timePeriod) {
        super(location, timePeriod);
        this.moduleCode = moduleCode;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Lesson{"
                + getDay()
                + ", " + getStartTime()
                + " to " + getEndTime()
                + " at " + location + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lesson lesson = (Lesson) o;
        return super.equals(o)
                && moduleCode.equals(lesson.getModuleCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), moduleCode);
    }
}
