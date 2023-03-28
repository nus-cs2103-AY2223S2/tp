package ezschedule.model.event;

import java.util.function.Predicate;

/**
 * Tests that an {@code Event} is one of the upcoming events.
 * Only the first {@code count} number of upcoming events are considered.
 */
public class UpcomingEventPredicate implements Predicate<Event> {

    private static int upcomingCount;
    private int count;

    /**
     * Constructor for UpcomingEventPredicate to reuse the given count.
     */
    public UpcomingEventPredicate() {
        count = upcomingCount;
    }

    /**
     * Constructor for UpcomingEventPredicate with given count.
     */
    public UpcomingEventPredicate(int count) {
        upcomingCount = count;
        this.count = count;
    }

    // Events should be in chronological order
    @Override
    public boolean test(Event event) {
        if (event.isCompleted()) {
            return false;
        }

        return count-- > 0;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UpcomingEventPredicate // instanceof handles nulls
                && count == ((UpcomingEventPredicate) other).count); // state check
    }
}
