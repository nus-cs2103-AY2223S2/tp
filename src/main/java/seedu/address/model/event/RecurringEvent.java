package seedu.address.model.event;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
     * Getter for startTime
     * @return the startTime
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Getter for endTime
     * @return the endTime
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Get the value for the day of the week
     * @return 0, 1, 2, 3, 4, 5, 6 for Monday, Tuesday, Wednesday, Thursday, Friday, Saturday and Sunday respectively
     */
    public int getDayValue() {
        return dayOfWeek.getValue();
    }

    /**
     * Compares the day and time between recurringEvent.
     * @param o the object to be compared.
     * @return -1 if the event falls before o, 1 if both event falls after o and 0 if the event
     *     conflicts with each other
     */
    @Override
    public int compareTo(RecurringEvent o) {

        int oDay = o.getDayValue();

        if (getDayValue() > oDay) {
            return 1;
        } else if (getDayValue() < oDay) {
            return -1;
        }

        LocalTime oStart = o.getStartTime();
        LocalTime oEnd = o.getEndTime();

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
        DayOfWeek endPeriodDay = endPeriod.getDayOfWeek();

        LocalTime startPeriodTime = LocalTime.parse(startPeriod.format(DateTimeFormatter.ofPattern("HH:mm")));
        LocalTime endPeriodTime = LocalTime.parse(startPeriod.format(DateTimeFormatter.ofPattern("HH:mm")));

        boolean isDayBetweenPeriod = false;
        boolean isTimeBetweenPeriod;

        long daysBetween = startPeriod.until(endPeriod, ChronoUnit.DAYS);

        if (daysBetween > 7) {
            return true;
        }

        DayOfWeek eventDay = startPeriodDay;
        for (int i = 0; i < daysBetween; i++) {
            if (this.dayOfWeek.equals(eventDay)) {
                isDayBetweenPeriod = true;
                break;
            }
            eventDay = eventDay.plus(1);
        }

        if (eventDay.equals(startPeriodDay) && eventDay.equals(endPeriodDay)) {
            isTimeBetweenPeriod = (this.startTime.isAfter(startPeriodTime) || this.startTime.equals(startPeriodDay))
                    && (this.endTime.isBefore(endPeriodTime) || this.endTime.equals(endPeriodTime));
        } else if (eventDay.equals(startPeriodDay)) {
            isTimeBetweenPeriod = this.startTime.isAfter(startPeriodTime) || this.startTime.equals(startPeriodDay);
        } else if (eventDay.equals(endPeriodDay)) {
            isTimeBetweenPeriod = this.endTime.isBefore(endPeriodTime) || this.endTime.equals(endPeriodTime);
        } else {
            isTimeBetweenPeriod = true;
        }

        return isDayBetweenPeriod && isTimeBetweenPeriod;
    }

    @Override
    public String toString() {
        return getEventName() + " on " + this.dayOfWeek.name() + " from " + startTime.toString() + " to "
                + endTime.toString();
    }
}
