package ezschedule.model.event;

import java.util.function.Predicate;

/**
 * Tests that a {@code Event}'s {@code Date} matches the date given.
 */
public class EventMatchesDatePredicate implements Predicate<Event> {

    private final Date date;

    /**
     * Constructs a {@code EventMatchesDatePredicate}.
     *
     * @param date The given date to be matched with.
     */
    public EventMatchesDatePredicate(Date date) {
        this.date = date;
    }

    @Override
    public boolean test(Event event) {
        return date.equals(event.getDate());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EventMatchesDatePredicate // instanceof handles nulls
                && date.equals(((EventMatchesDatePredicate) other).date)); // state check
    }
}
