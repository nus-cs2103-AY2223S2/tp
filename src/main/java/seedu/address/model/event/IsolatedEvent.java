package seedu.address.model.event;

import java.time.LocalDateTime;

/**
 * Represents an {@code Event} that happens only once.
 */
public class IsolatedEvent extends Event implements Comparable<IsolatedEvent> {

    // TODO: Add String representation of IsolatedEvent
    public IsolatedEvent(String eventName) {
        super(eventName);
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
        return false;
    }
}
