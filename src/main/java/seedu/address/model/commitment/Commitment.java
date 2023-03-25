package seedu.address.model.commitment;

import org.joda.time.LocalTime;

import seedu.address.model.scheduler.time.Day;

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
    public Commitment() {

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
