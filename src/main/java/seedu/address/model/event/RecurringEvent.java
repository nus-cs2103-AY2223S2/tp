package seedu.address.model.event;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * Represents an {@code Event} that occurs on a weekly basis.
 */
public class RecurringEvent extends Event implements Comparable<RecurringEvent> {

    public static final String MESSAGE_CONSTRAINTS_TIME =
            "Time should be in the format: HH:mm";
    public static final String MESSAGE_CONSTRAINTS_PERIOD =
            "The end time should not be earlier than the start time";
    public static final String MESSAGE_CONSTRAINTS_DAYOFWEEK =
            "The day of the week should be either MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY or SUNDAY";

    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Constructor for Recurring events
     * @param eventName is the name of event
     * @param dayOfWeek is the day when the event occurs
     * @param startTime is the starting time of the event
     * @param endTime is the ending time of the event
     */
    public RecurringEvent(String eventName, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        super(eventName);

        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Gets the starting time of the event
     * @return the startTime
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Gets the ending time of the event
     * @return the endTime
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Get the value for the day of the week
     * 0, 1, 2, 3, 4, 5, 6 stands for Monday, Tuesday, Wednesday, Thursday, Friday,
     * Saturday and Sunday respectively
     * @return the value of day
     */
    public int getDayValue() {
        return dayOfWeek.getValue();
    }

    /**
     * Compares the day and time between recurringEvent.
     * @param recurringEvent2 the object to be compared.
     * @return -1 if the event falls before o, 1 if both event falls after o and 0 if the event
     *     conflicts with each other
     */
    @Override
    public int compareTo(RecurringEvent recurringEvent2) {

        int oDay = recurringEvent2.getDayValue();

        if (getDayValue() > oDay) {
            return 1;
        } else if (getDayValue() < oDay) {
            return -1;
        }

        LocalTime oStart = recurringEvent2.getStartTime();
        LocalTime oEnd = recurringEvent2.getEndTime();

        if (this.startTime.isBefore(oStart) && (this.endTime.isBefore(oStart) || this.endTime.equals(oStart))) {
            return -1;
        } else if (this.startTime.isAfter(oStart) && (this.startTime.isAfter(oEnd) || this.startTime.equals(oEnd))) {
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

        DayOfWeek startPeriodDay = startPeriod.getDayOfWeek();

        boolean isDayBetweenPeriod = false;

        long daysBetween = startPeriod.until(endPeriod, ChronoUnit.DAYS);

        // if there are more than 7 days between the start and end period then it is guaranteed that falls
        // within the week
        if (daysBetween > 7) {
            return true;
        }

        long count = 0;
        DayOfWeek eventDay = startPeriodDay;
        for (int i = 0; i < daysBetween; i++) {
            if (this.dayOfWeek.equals(eventDay)) {
                isDayBetweenPeriod = true;
                break;
            }
            eventDay = eventDay.plus(1);
            count++;
        }

        if (!isDayBetweenPeriod && count != 0) {
            //day does not fall between start and end day. E.g. FRIDAY does not fall between MONDAY and Thursday
            return false;
        }

        LocalDateTime recurringEventDate = startPeriod.plusDays(count);
        LocalDateTime dummyEventStartDate = LocalDateTime.of(recurringEventDate.toLocalDate(), this.startTime);
        LocalDateTime dummyEventEndDate = LocalDateTime.of(recurringEventDate.toLocalDate(), this.endTime);

        if (dummyEventStartDate.isBefore(startPeriod) || dummyEventEndDate.isAfter(endPeriod)) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return getEventName() + " on " + this.dayOfWeek.name() + " from " + startTime.toString() + " to "
                + endTime.toString();
    }
}
