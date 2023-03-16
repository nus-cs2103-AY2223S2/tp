package seedu.address.model.timetable;

import org.joda.time.LocalTime;

import seedu.address.model.location.Location;

/**
 * Represents a typical NUS lesson.
 */
public class Lesson {

    private SchoolDay day;
    private LocalTime startTime;
    private LocalTime endTime;
    private Location location;
    private Module module;

    /**
     * Constructs a lesson.
     */
    public Lesson(Module module, SchoolDay schoolDay, LocalTime startTime, LocalTime endTime, Location location) {
        this.day = schoolDay;
        this.module = module;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }

    public Module getModule() {
        return module;
    }

    public SchoolDay getDay() {
        return day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public Location getLocation() {
        return location;
    }
}
