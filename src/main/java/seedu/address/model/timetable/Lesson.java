package seedu.address.model.timetable;

import java.util.ArrayList;

import org.joda.time.LocalTime;

import seedu.address.model.location.Location;
import seedu.address.model.timetable.time.SchoolDay;
import seedu.address.model.timetable.time.TimeSlot;


/**
 * Represents a typical NUS lesson.
 */
public class Lesson {

    private final SchoolDay day;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final Location location;
    private final Module module;

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

    /**
     * Checks if there is a sequence of timeslots to fit lesson.
     */
    public boolean canFitLessonIntoDaySchedule(ArrayList<TimeSlot> slots) {
        boolean canFit = true;
        for (int i = startTime.getHourOfDay() - 8; i < endTime.getHourOfDay() - 8; i++) {
            canFit &= slots.get(i).isFree();
        }
        return canFit;
    }
}
