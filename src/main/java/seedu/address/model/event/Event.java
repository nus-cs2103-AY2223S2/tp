package seedu.address.model.event;

import java.time.LocalDateTime;

/**
 * Represents an event that a {@code Person} is involved in.
 */
public abstract class Event {
    private String eventName;

    public Event(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
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
