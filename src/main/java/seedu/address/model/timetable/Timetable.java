package seedu.address.model.timetable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import org.joda.time.LocalTime;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.commitment.Commitment;
import seedu.address.model.time.Day;
import seedu.address.model.time.HourBlock;
import seedu.address.model.time.util.TimeUtil;
import seedu.address.model.timingrecommender.exceptions.CommitmentClashException;

/**
 * Represents a timetable for a person.
 */
public class Timetable {
    private static final Integer[] startTimings = new Integer[] {
        8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21
    };
    private static final Logger logger = LogsCenter.getLogger(Timetable.class);
    private final HashMap<Day, ArrayList<HourBlock>> schedule;

    /**
     * Constructs a Timetable with no classes.
     */
    public Timetable() {
        logger.info("=============================[ Creating Timetable ]===========================");
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
     * Adds a commitment to the schedule.
     */
    public <T extends Commitment> void addCommitment(T commitment) {
        Day day = commitment.getDay();
        ArrayList<HourBlock> availableSlots = schedule.get(day);
        if (!commitment.<T>canFitIntoDaySchedule(availableSlots)) {
            throw new CommitmentClashException("There is lesson clash!");
        } else {
            for (int i = commitment.getStartTime().getHourOfDay() - 8;
                 i < commitment.getEndTime().getHourOfDay() - 8; i++) {
                availableSlots.get(i).setCommitment(commitment);
            }
        }
    }

    /**
     * Merges another timetable together
     */
    public void mergeTimetable(Timetable other) {
        logger.info("=============================[ Timetable: Check conflict ]===========================");
        assert(!TimeUtil.hasConflict(this, other));
        logger.info("=============================[ Timetable: No Conflict ]===========================");
        HashMap<Day, ArrayList<HourBlock>> otherSchedule = other.getSchedule();
        for (Day day : Day.values()) {
            ArrayList<HourBlock> dayEvents = otherSchedule.get(day);
            for (int i = 0; i < dayEvents.size(); i++) {
                if (!dayEvents.get(i).isFree()) {
                    Commitment lesson = dayEvents.get(i).getCommitment().orElseGet(() -> null);
                    logger.info(String.format("=============================[ Lesson Added: %s]===="
                        + "=======================", lesson));
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

