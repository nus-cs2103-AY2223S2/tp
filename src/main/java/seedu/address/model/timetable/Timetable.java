package seedu.address.model.timetable;

import java.util.ArrayList;
import java.util.HashMap;

import org.joda.time.LocalTime;
/**
 * Represents a timetable for a person.
 */
public class Timetable {
    private static final String[] startTimings = new String[] {
        "0800", "0900", "1000", "1100", "1200", "1300", "1400",
        "1500", "1600", "1700", "1800", "1900", "2000", "2100"
    };

    private HashMap<SchoolDay, ArrayList<TimeSlot>> schedule;

    /**
     * Constructs a Timetable with no classes.
     */
    public Timetable() {
        this.schedule = new HashMap<>();
        for (SchoolDay day : SchoolDay.values()) {
            ArrayList<TimeSlot> grid = new ArrayList<>();
            for (String timings : startTimings) {
                grid.add(new TimeSlot(new LocalTime(timings), day));
            }
            schedule.put(day, grid);
        }
    }

    /**
     * Adds a lesson to the schedule.
     */
    public void addLesson(Lesson lesson) {
        SchoolDay day = lesson.getDay();
        schedule.get(day).stream()
                .filter(timeSlot -> timeSlot.canFitLesson(lesson))
                .forEachOrdered(timeSlot -> timeSlot.setLesson(lesson));
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
