package seedu.address.model.event;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import seedu.address.model.event.exceptions.EventConflictException;

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
    public static final String MESSAGE_CONSTRAINTS_CLASH =
            "Events cannot have conflicts with other events";

    private final DayOfWeek dayOfWeek;
    private final LocalTime startTime;
    private final LocalTime endTime;

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
     * Gets the day that the event takes place
     * @return the dayOfWeek
     */
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
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
     * This function cross-check with the isolated event list to check for any conflicts.
     * @param isolatedEventList is the event list to be checked with
     * @throws seedu.address.model.event.exceptions.EventConflictException if there is a conflicted event
     */
    public void listConflictedEventWithIsolated(
            IsolatedEventList isolatedEventList) throws EventConflictException {

        int index = 1;
        for (IsolatedEvent ie : isolatedEventList.getIsolatedEvents()) {
            LocalDateTime startPeriod = ie.getStartDate();
            LocalDateTime endPeriod = ie.getEndDate();

            long count = this.numberOfDaysBetween(startPeriod, endPeriod, this.getDayOfWeek());

            if (count == -1) {
                continue;
            }

            LocalDateTime recurringEventDate = startPeriod.plusDays(count);

            LocalDateTime dummyEventStartDate =
                    LocalDateTime.of(recurringEventDate.toLocalDate(), this.getStartTime());

            LocalDateTime dummyEventEndDate =
                    LocalDateTime.of(recurringEventDate.toLocalDate(), this.getEndTime());

            boolean isEventBefore = false;
            boolean isEventAfter = false;

            if (!dummyEventStartDate.isAfter(startPeriod) && !dummyEventEndDate.isAfter(startPeriod)) {
                isEventBefore = true;
            }

            if (!dummyEventStartDate.isBefore(endPeriod) && !dummyEventEndDate.isBefore(endPeriod)) {
                isEventAfter = true;
            }

            if (!(isEventBefore || isEventAfter)) {
                throw new EventConflictException(MESSAGE_CONSTRAINTS_CLASH
                        + "\nIsolated Event List:\n" + index + " " + ie);
            }

        }
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

        if (!isDayBetweenPeriod && !eventDay.equals(this.dayOfWeek)) {
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

    /**
     * Checks if the start time and the end time of the event is valid for recurring event.
     */
    public void checkPeriod() throws EventConflictException {
        if (this.startTime.isAfter(this.endTime) || this.startTime.equals(this.endTime)) {
            throw new EventConflictException(RecurringEvent.MESSAGE_CONSTRAINTS_PERIOD);
        }
    }

    @Override
    public String toString() {
        return getEventName() + " on " + this.dayOfWeek.name() + " from " + startTime.toString() + " to "
                + endTime.toString();
    }


    /**
     * To check if the day of the week falls between 2 dates
     * @param startPeriod represents the starting date
     * @param endPeriod represents the ending date
     * @param dayOfWeek is the day of the week
     * @return count which indicates how many the number of in which the day of week falls after the start period
     *      and -1 if it does not fall between the 2 dates
     */
    public long numberOfDaysBetween(LocalDateTime startPeriod, LocalDateTime endPeriod, DayOfWeek dayOfWeek) {
        long daysBetween = startPeriod.until(endPeriod, ChronoUnit.DAYS);
        DayOfWeek startPeriodDay = startPeriod.getDayOfWeek();

        DayOfWeek eventDay = startPeriodDay;
        long count = 0;

        for (int i = 0; i < daysBetween; i++) {
            if (dayOfWeek.equals(eventDay)) {
                break;
            }
            eventDay = eventDay.plus(1);
            count++;
        }
        if (eventDay.equals(dayOfWeek)) {
            return count;
        }
        return -1;
    }
}
