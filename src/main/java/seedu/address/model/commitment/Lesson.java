package seedu.address.model.commitment;

import java.util.ArrayList;

import org.joda.time.LocalTime;

import seedu.address.model.location.Location;
import seedu.address.model.scheduler.Module;
import seedu.address.model.scheduler.time.Day;
import seedu.address.model.scheduler.time.HourBlock;



/**
 * Represents a typical NUS lesson.
 */
public class Lesson {

    private final Day day;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final Location location;
    private final Module module;

    /**
     * Constructs a lesson.
     */
    public Lesson(Module module, LocalTime startTime, LocalTime endTime, Day schoolDay, Location location) {
        this.day = schoolDay;
        this.module = module;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }

    public Module getModule() {
        return module;
    }

    public Day getDay() {
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
                + day
                + ", " + startTime
                + " to " + endTime
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
        return day == lesson.day
                && startTime.isEqual(lesson.getStartTime())
                && endTime.isEqual(lesson.getEndTime())
                && location.equals(lesson.getLocation())
                && module.equals(lesson.getModule());
    }

    /**
     * Checks if there is a sequence of timeslots to fit lesson.
     */
    public boolean canFitLessonIntoDaySchedule(ArrayList<HourBlock> slots) {
        boolean canFit = true;
        for (int i = startTime.getHourOfDay() - 8; i < endTime.getHourOfDay() - 8; i++) {
            canFit &= slots.get(i).isFree();
        }
        return canFit;
    }

    @Override
    public int hashCode() {
        String hash = day.toString() + startTime.toString() + endTime.toString();
        return hash.hashCode();
    }
}
