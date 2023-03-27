package ezschedule.model.event;

import java.util.function.Predicate;

/**
 * Tests that a {@code Event}'s {@code Date} matches the date given.
 */
public class EventMatchesDatePredicate implements Predicate<Event> {

    private final Date date;

    public EventMatchesDatePredicate(Date date) {
        this.date = date;
    }

    @Override
    public boolean test(Event event) {
        return date.equals(event.getDate());
    }
}
