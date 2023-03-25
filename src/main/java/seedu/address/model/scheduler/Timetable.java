package seedu.address.model.scheduler;

import java.util.ArrayList;
import java.util.HashMap;

import org.joda.time.LocalTime;

import seedu.address.model.commitment.Commitment;
import seedu.address.model.scheduler.exceptions.CommitmentClashException;
import seedu.address.model.scheduler.time.Day;
import seedu.address.model.scheduler.time.HourBlock;
import seedu.address.model.scheduler.time.TimePeriod;
import seedu.address.model.scheduler.time.util.TimeUtil;

/**
 * Represents a timetable for a person.
 */
public class Timetable {
    public static final Integer[] START_TIMINGS = new Integer[] {
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
            for (Integer hour : START_TIMINGS) {
                grid.add(new HourBlock(new LocalTime(hour, 0), day));
            }
            schedule.put(day, grid);
        }
    }

    /**
     * Adds a commitment to the schedule.
     */
    public void addLesson(Commitment commitment) {
        TimePeriod timePeriod = commitment.getTimePeriod();
        Day day = timePeriod.getSchoolDay();
        ArrayList<HourBlock> availableSlots = schedule.get(day);
        if (!commitment.canFitIntoDaySchedule(availableSlots)) {
            throw new CommitmentClashException("There is already a prior commitment!");
        } else {
            for (int i = timePeriod.getStartTime().getHourOfDay() - 8;
                 i < timePeriod.getEndTime().getHourOfDay() - 8; i++) {
                availableSlots.get(i).setCommitment(commitment);
            }
        }
    }

    /**
     * Merges another timetable together
     */
    public void mergeTimetable(Timetable other) {
        assert(!TimeUtil.hasConflict(this, other));
        HashMap<Day, ArrayList<HourBlock>> schedule = other.getSchedule();
        for (Day day : Day.values()) {
            ArrayList<HourBlock> dayEvents = schedule.get(day);
            for (int i = 0; i < schedule.size(); i++) {
                if (dayEvents.get(i).getCommitment().isPresent()) {
                    Commitment lesson = dayEvents.get(i).getCommitment().get();
                    this.schedule.get(day).get(i).setCommitment(lesson);
                }
            }
        }
        //TODO: Find a cleaner implementation.
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

