package seedu.address.model.event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an {@code Event} that happens only once.
 */
public class IsolatedEvent extends Event implements Comparable<IsolatedEvent> {

    public static final String VALIDATION_REGEX_EVENTNAME = "[\\p{Alnum}][\\p{Alnum} ]*";
    public static final String MESSAGE_CONSTRAINTS_EVENTNAME =
            "Event name should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String MESSAGE_CONSTRAINTS_DATE =
            "Date should be in the format: dd/MM/yyyy HH:mm";

    private String eventName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * Constructor for IsolatedEvent class.
     * @param eventName name of the isolated event added.
     * @param startDate in which the isolated event starts.
     * @param endDate in which the isolated event ends.
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
     * Returns a {@code boolean} that indicates if the {@code Event} occurs between the
     * given period.
     * @param startPeriod The start of the given period.
     * @param endPeriod The end of the given period.
     * @return A boolean indicating if the even occurs during the given period.
     */
    @Override
    public boolean occursBetween(LocalDateTime startPeriod, LocalDateTime endPeriod) {
        if (startDate.isBefore(startPeriod)) {
            return false;
        }

        if (endDate.isAfter(endPeriod)) {
            return false;
        }

        return true;
    }

    public static boolean isValidEventName(String eventName) {
        return eventName.matches(VALIDATION_REGEX_EVENTNAME);
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
