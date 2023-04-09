package seedu.address.model.commitment;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import org.joda.time.LocalTime;

import seedu.address.model.location.Location;
import seedu.address.model.time.Day;
import seedu.address.model.time.TimeBlock;
import seedu.address.model.time.TimePeriod;

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
        requireNonNull(moduleCode);
        this.moduleCode = moduleCode;
    }

    /**
     * Constructs a lesson.
     */
    public Lesson(String moduleCode, Location location, TimePeriod timePeriod) {
        super(location, timePeriod);
        requireNonNull(moduleCode);
        this.moduleCode = moduleCode;
    }

    /**
     * Constructs a lesson.
     */
    public Lesson(TimePeriod timePeriod) {
        super(Location.NUS, timePeriod);
        this.moduleCode = "";
    }

    /**
     * Creates a new {@code Lesson} with updated module code.
     * Used for handling empty module code strings.
     */
    public Lesson updateModuleCode(String moduleCode) {
        requireNonNull(moduleCode);
        return new Lesson(moduleCode, location, timePeriod);
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", moduleCode, timePeriod.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Lesson)) {
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
