package seedu.address.model.scheduler;

import java.util.ArrayList;
import java.util.HashMap;

import org.joda.time.LocalTime;

import seedu.address.model.commitment.Lesson;
import seedu.address.model.scheduler.exceptions.CommitmentClashException;
import seedu.address.model.time.Day;
import seedu.address.model.time.HourBlock;

/**
 * Represents a timetable for a person.
 */
public class Timetable {
    private static final Integer[] startTimings = new Integer[] {
        8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21
    };

    private HashMap<Day, ArrayList<HourBlock>> schedule;

    /**
     * Constructs a Timetable with no classes.
     */
    public Timetable() {
        this.schedule = new HashMap<>();
        for (Day day : Day.values()) {
            ArrayList<HourBlock> grid = new ArrayList<>();
            for (Integer hour : startTimings) {
                grid.add(new HourBlock(new LocalTime(hour, 0), day));
            }
            schedule.put(day, grid);
        }
    }

    /**
     * Adds a lesson to the schedule.
     */
    public void addLesson(Lesson lesson) {
        Day day = lesson.getDay();
        ArrayList<HourBlock> availableSlots = schedule.get(day);
        if (!lesson.canFitLessonIntoDaySchedule(availableSlots)) {
            throw new CommitmentClashException("There is lesson clash!");
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
        for (Day day : Day.values()) {
            sb.append(day).append("\n");
            ArrayList<HourBlock> dayTime = schedule.get(day);
            for (HourBlock hourBlock : dayTime) {
                if (!hourBlock.isFree()) {
                    sb.append(hourBlock).append("\n");
                }
            }
        }
        sb.append("\n");
        return sb.toString();
    }

    public HashMap<Day, ArrayList<HourBlock>> getSchedule() {
        return schedule;
    }

    public ArrayList<HourBlock> getMondayClasses() {
        return schedule.get(Day.MONDAY);
    }

    public ArrayList<HourBlock> getTuesdayClasses() {
        return schedule.get(Day.TUESDAY);
    }

    public ArrayList<HourBlock> getWednesdayClasses() {
        return schedule.get(Day.WEDNESDAY);
    }

    public ArrayList<HourBlock> getThursdayClasses() {
        return schedule.get(Day.THURSDAY);
    }

    public ArrayList<HourBlock> getFridayClasses() {
        return schedule.get(Day.FRIDAY);
    }

}
