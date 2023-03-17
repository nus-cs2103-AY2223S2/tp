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
    public Lesson(Module module, LocalTime startTime, LocalTime endTime, SchoolDay schoolDay, Location location) {
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

    @Override
    public String toString() {
        return "Lesson{"
                + "day=" + day
                + "\nstartTime=" + startTime
                + "\nendTime=" + endTime
                + "\nlocation=" + location
                + "\nmodule=" + module + '}';
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
        return day == lesson.day
                && startTime.isEqual(lesson.getStartTime())
                && endTime.isEqual(lesson.getEndTime())
                && location.equals(lesson.getLocation())
                && module.equals(lesson.getModule());
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
