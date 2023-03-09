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

    private final String eventName;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Constructor for IsolatedEvent class.
     * @param eventName
     * @param startDate
     * @param endDate
     */
    public IsolatedEvent(String eventName, LocalDateTime startDate, LocalDateTime endDate) {
        super(eventName);
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Constructor for IsolatedEvent Class.
     * @param eventName
     * @param startDate
     * @param endDate
     */
    public IsolatedEvent(String eventName, String startDate, String endDate) {
        super(eventName);
        this.eventName = eventName;
        this.startDate = LocalDateTime.parse(startDate, formatter);
        this.endDate = LocalDateTime.parse(endDate, formatter);
    }

    // TODO: Implement compareTo by comparing the start date/time of events
    @Override
    public int compareTo(IsolatedEvent o) { //TODO: Add implementation for compareTo
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
        if (startDate.isBefore(startPeriod) || endDate.isAfter(endPeriod)) {
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
