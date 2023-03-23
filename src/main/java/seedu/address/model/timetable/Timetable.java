package seedu.address.model.timetable;

import java.util.ArrayList;
import java.util.HashMap;

import org.joda.time.LocalTime;

import seedu.address.model.timetable.exceptions.LessonClashException;
import seedu.address.model.timetable.time.SchoolDay;
import seedu.address.model.timetable.time.TimeSlot;

/**
 * Represents a timetable for a person.
 */
public class Timetable {
    private static final Integer[] startTimings = new Integer[] {
        8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21
    };

    private HashMap<SchoolDay, ArrayList<TimeSlot>> schedule;

    /**
     * Constructs a Timetable with no classes.
     */
    public Timetable() {
        this.schedule = new HashMap<>();
        for (SchoolDay day : SchoolDay.values()) {
            ArrayList<TimeSlot> grid = new ArrayList<>();
            for (Integer hour : startTimings) {
                grid.add(new TimeSlot(new LocalTime(hour, 0), day));
            }
            schedule.put(day, grid);
        }
    }

    /**
     * Adds a lesson to the schedule.
     */
    public void addLesson(Lesson lesson) {
        SchoolDay day = lesson.getDay();
        ArrayList<TimeSlot> availableSlots = schedule.get(day);
        if (!lesson.canFitLessonIntoDaySchedule(availableSlots)) {
            throw new LessonClashException("There is lesson clash!");
        } else {
            for (int i = lesson.getStartTime().getHourOfDay() - 8;
                 i < lesson.getEndTime().getHourOfDay() - 8; i++) {
                availableSlots.get(i).setLesson(lesson);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Classes: \n");
        for (SchoolDay day : SchoolDay.values()) {
            sb.append(day).append("\n");
            ArrayList<TimeSlot> dayTime = schedule.get(day);
            for (TimeSlot timeSlot : dayTime) {
                if (!timeSlot.isFree()) {
                    sb.append(timeSlot).append("\n");
                }
            }
        }
        sb.append("\n");
        return sb.toString();
    }

    public HashMap<SchoolDay, ArrayList<TimeSlot>> getSchedule() {
        return schedule;
    }

    public ArrayList<TimeSlot> getMondayClasses() {
        return schedule.get(SchoolDay.MONDAY);
    }

    public ArrayList<TimeSlot> getTuesdayClasses() {
        return schedule.get(SchoolDay.TUESDAY);
    }

    public ArrayList<TimeSlot> getWednesdayClasses() {
        return schedule.get(SchoolDay.WEDNESDAY);
    }

    public ArrayList<TimeSlot> getThursdayClasses() {
        return schedule.get(SchoolDay.THURSDAY);
    }

    public ArrayList<TimeSlot> getFridayClasses() {
        return schedule.get(SchoolDay.FRIDAY);
    }

}
