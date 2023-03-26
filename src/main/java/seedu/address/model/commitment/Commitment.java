package seedu.address.model.commitment;

import java.util.ArrayList;

import org.joda.time.LocalTime;

import seedu.address.model.time.Day;
import seedu.address.model.time.HourBlock;

/**
 * Represents a commitment.
 */
public class Commitment {

    private LocalTime startTime;
    private LocalTime endTime;
    private Day day;

    /**
     * Constructs a Commitment object
     */
    public Commitment(LocalTime startTime, LocalTime endTime, Day day) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
    }

    /**
     * Checks if there is a sequence of timeslots to fit a commitment.
     */
    public boolean canFitIntoDaySchedule(ArrayList<HourBlock> slots) {
        boolean canFit = true;
        for (int i = startTime.getHourOfDay() - 8; i < endTime.getHourOfDay() - 8; i++) {
            System.out.println(String.format("%s, %s", i, slots.get(i)));
            canFit &= slots.get(i).isFree();
        }
        return canFit;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public Day getDay() {
        return day;
    }
}
