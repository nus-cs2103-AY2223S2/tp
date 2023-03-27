package ezschedule.model.event;

import java.util.function.Predicate;

/**
 * Tests that an {@code Event} is one of the upcoming events.
 * Only the first {@code count} number of upcoming events are considered.
 */
public class UpcomingEventPredicate implements Predicate<Event> {

    private int upcomingCount;

    public UpcomingEventPredicate(int count) {
        upcomingCount = count;
    }

    // Events should be in chronological order
    @Override
    public boolean test(Event event) {
        if (event.isCompleted()) {
            return false;
        }

        return upcomingCount-- > 0;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UpcomingEventPredicate // instanceof handles nulls
                && upcomingCount == ((UpcomingEventPredicate) other).upcomingCount); // state check
    }
}
