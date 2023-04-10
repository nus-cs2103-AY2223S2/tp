package seedu.address.model.timetable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.joda.time.LocalTime;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.recommender.timing.exceptions.CommitmentClashException;
import seedu.address.model.commitment.Commitment;
import seedu.address.model.time.Day;
import seedu.address.model.time.HourBlock;
import seedu.address.model.time.TimePeriod;
import seedu.address.model.time.util.TimeUtil;

/**
 * Represents a timetable for a person.
 */
public class Timetable {

    public static final Integer[] START_TIMINGS = new Integer[] {
        8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22
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
            for (Integer hour : START_TIMINGS) {
                grid.add(new HourBlock(new LocalTime(hour, 0), day));
            }
            schedule.put(day, grid);
        }
    }

    /**
     * Construcs a Timetable with some commitments.
     * @param commitments The commitments to add in the timetable.
     */
    public Timetable(Collection<? extends Commitment> commitments) {
        this();

        List<TimePeriod> timePeriods = commitments.stream()
                .map(Commitment::getTimePeriod)
                .collect(Collectors.toList());

        assert !TimeUtil.hasAnyClash(timePeriods);

        commitments.forEach(this::addCommitment);
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
     * Removes a commitment from the schedule.
     */
    public <T extends Commitment> void removeCommitment(T commitment) {
        Day day = commitment.getDay();
        ArrayList<HourBlock> availableSlots = schedule.get(day);

        for (int i = commitment.getStartTime().getHourOfDay() - 8;
             i < commitment.getEndTime().getHourOfDay() - 8; i++) {
            availableSlots.get(i).removeCommitment();
        }
    }

    /**
     * Returns whether the timetable can fit the commitment.
     */
    public boolean canFitCommitment(Commitment commitment) {
        return commitment.canFitIntoDaySchedule(schedule.get(commitment.getDay()));
    }

    /**
     * Returns the set of commitments that clash with the one given.
     */
    public Set<Commitment> getClashingCommitments(Commitment commitment) {
        return commitment.getClashingCommitments(schedule.get(commitment.getDay()));
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

