package seedu.address.model.event;

import java.time.LocalDateTime;

/**
 * Represents an event that a {@code Person} is involved in.
 */
public abstract class Event {

    public static final String VALIDATION_REGEX_EVENTNAME = "[\\p{Alnum}][\\p{Alnum} ]*";
    public static final String MESSAGE_CONSTRAINTS_EVENTNAME =
            "Event name should only contain alphanumeric characters and spaces, and it should not be blank";
    private String eventName;

    public Event(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

    public static boolean isValidEventName(String eventName) {
        return eventName.matches(VALIDATION_REGEX_EVENTNAME);
    }


    /**
     * Returns a {@code boolean} that indicates if the {@code Event} occurs between the
     * given period.
     * @param startPeriod The start of the given period.
     * @param endPeriod The end of the given period.
     * @return A boolean indicating if the even occurs during the given period.
     */
    public abstract boolean occursBetween(LocalDateTime startPeriod, LocalDateTime endPeriod);
}
