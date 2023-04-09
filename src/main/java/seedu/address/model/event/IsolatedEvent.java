package seedu.address.model.event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.commons.core.Messages;
import seedu.address.model.event.exceptions.EventConflictException;

/**
 * Represents an {@code Event} that happens only once.
 */
public class IsolatedEvent extends Event implements Comparable<IsolatedEvent> {

    public static final String MESSAGE_CONSTRAINTS_EVENTNAME =
            "Event name should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String MESSAGE_CONSTRAINTS_DATE =
            "Date should be in the format: dd/MM/yyyy HH:mm";

    private String eventName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * Constructor for IsolatedEvent class.
     *
     * @param eventName name of the isolated event added.
     * @param startDate in which the isolated event starts.
     * @param endDate   in which the isolated event ends.
     */
    public IsolatedEvent(String eventName, LocalDateTime startDate, LocalDateTime endDate) {
        super(eventName);
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    public int getStartDayValue() {
        return startDate.getDayOfWeek().getValue();
    }

    public int getEndDayValue() {
        return endDate.getDayOfWeek().getValue();
    }

    public String getEventName() {
        return this.eventName;
    }

    @Override
    public int compareTo(IsolatedEvent o) { //TODO: Add implementation for compareTo

        LocalDateTime oStart = o.getStartDate();
        LocalDateTime oEnd = o.getEndDate();

        if (this.startDate.isBefore(oStart) && (this.endDate.isBefore(oStart) || this.endDate.equals(oStart))) {
            return -1;
        } else if (this.startDate.isAfter(oStart) && (this.startDate.isAfter(oEnd) || this.startDate.equals(oEnd))) {
            return 1;
        }
        return 0;
    }

    /**
     * Checks if the {@code IsolatedEvent} has any conflicts with any of the {@code RecurringEvent}s in the given
     * {@code RecurringEventList}.
     * @param recurringEventList The {@code RecurringEventList} to check for conflicts with.
     * @throws EventConflictException if there is a conflict found in the given {@code RecurringEventList}.
     */
    public void checkConflictsRecurringEventList(RecurringEventList recurringEventList) throws EventConflictException {
        LocalDateTime startPeriod = this.getStartDate();
        LocalDateTime endPeriod = this.getEndDate();

        int index = 1;
        for (RecurringEvent re : recurringEventList.getRecurringEvents()) {
            long count = re.numberOfDaysBetween(startPeriod, endPeriod, re.getDayOfWeek());

            if (count == -1) {
                continue;
            }

            LocalDateTime recurringEventDate = startPeriod.plusDays(count);
            LocalDateTime dummyEventStartDate = LocalDateTime.of(recurringEventDate.toLocalDate(), re.getStartTime());
            LocalDateTime dummyEventEndDate = LocalDateTime.of(recurringEventDate.toLocalDate(), re.getEndTime());

            boolean isEventBefore = false;
            boolean isEventAfter = false;

            if (!dummyEventStartDate.isAfter(startPeriod) && !dummyEventEndDate.isAfter(startPeriod)) {
                isEventBefore = true;
            }

            if (!dummyEventStartDate.isBefore(endPeriod) && !dummyEventEndDate.isBefore(endPeriod)) {
                isEventAfter = true;
            }

            if (!(isEventBefore || isEventAfter)) {
                throw new EventConflictException("Recurring Event List:\n" + index + " " + re);
            }
            index++;
        }
    }

    /**
     * Returns a {@code boolean} that indicates if the {@code Event} occurs between the
     * given period.
     *
     * @param startPeriod The start of the given period.
     * @param endPeriod   The end of the given period.
     * @return A boolean indicating if the even occurs during the given period.
     */
    @Override
    public boolean occursBetween(LocalDateTime startPeriod, LocalDateTime endPeriod) {
        if (startDate.isBefore(startPeriod) && endDate.isBefore(endPeriod)) {
            return false;
        }

        if (startDate.isAfter(endPeriod) && endDate.isAfter(endPeriod)) {
            return false;
        }

        return true;
    }

    /**
     * Checks if the start of an {@code IsolatedEvent} is before its end.
     * @throws EventConflictException if the start is after the end
     */
    public void checkValidStartEnd() throws EventConflictException {
        if (this.startDate.isAfter(endDate) || this.startDate.equals(endDate)) {
            throw new EventConflictException(Messages.MESSAGE_EVENT_START_AFTER_END);
        }
    }

    /**
     * Checks if the {@code IsolatedEvent} has already ended.
     * @throws EventConflictException if the {@code IsolatedEvent} has already ended.
     */
    public void checkNotEnded() throws EventConflictException {
        LocalDateTime now = LocalDateTime.now();

        if (this.endDate.isBefore(now)) {
            throw new EventConflictException(Messages.MESSAGE_EVENT_INVALID_DATE);
        }
    }

    /**
     * Checks if the start date and the end date of the event is valid for isolated event.
     * @throws EventConflictException if start time is after the end time or the dates keyed in are before today.
     */
    public void checkDateTime() throws EventConflictException {
        this.checkValidStartEnd();
        this.checkNotEnded();
    }

    @Override
    public String toString() {
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String startDate = this.startDate.format(newFormatter);
        String endDate = this.endDate.format(newFormatter);

        String currEventInfo = this.eventName + " from: " + startDate + " to: " + endDate;
        return currEventInfo;
    }
}
