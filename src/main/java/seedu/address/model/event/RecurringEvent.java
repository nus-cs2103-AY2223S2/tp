package seedu.address.model.event;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;


/**
 * Represents an {@code Event} that occurs on a weekly basis.
 */
public class RecurringEvent extends Event implements Comparable<RecurringEvent> {
    // TODO: Add String representation of RecurringEvent

    private  DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public RecurringEvent(String eventName, String dayOfWeek, String startTime, String endTime) {
        super(eventName);
        this.dayOfWeek = DayOfWeek.valueOf(dayOfWeek);
        this.startTime = LocalTime.parse(startTime);
        this.endTime = LocalTime.parse(endTime);
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getDayValue () {
        return dayOfWeek.getValue();
    }

    /**
     * Compares the day and time between recurringEvent.
     * @param o the object to be compared.
     * @return -1 if the event falls before o, 1 if both event falls after o and 0 if the event
     * conflicts with each other
     */
    @Override
    public int compareTo(RecurringEvent o) { //TODO: Add implementation for compareTo

        int oDay = o.getDayValue();

        if (getDayValue() > oDay) {
            return 1;
        } else if (getDayValue() < oDay) {
            return -1;
        }

        LocalTime oStart = o.getStartTime();
        LocalTime oEnd = o.getEndTime();

        if (this.startTime.isBefore(oStart) && this.endTime.isBefore(oStart)) {
            return -1;
        } else if (this.startTime.isAfter(oStart) && this.startTime.isAfter(oEnd)) {
            return 1;
        }

        return 0;

    }

    /**
     * Returns a {@code boolean} that indicates if the {@code Event} occurs between the
     * given period.
     * @param startPeriod The start of the given period.
     * @param endPeriod The end of the given period.
     * @return A boolean indicating if the even occurs during the given period.
     */
    @Override
    public boolean occursBetween(LocalDateTime startPeriod, LocalDateTime endPeriod) {
        // TODO: Implement this method.
        return false;
    }
}
