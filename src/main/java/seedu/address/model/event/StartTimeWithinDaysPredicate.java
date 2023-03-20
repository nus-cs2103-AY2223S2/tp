package seedu.address.model.event;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;

/**
 * Tests that a {@code Event}'s starrt {@code Time} is within the given number of days.
 */
public class StartTimeWithinDaysPredicate implements Predicate<Event> {
    private final Integer days;

    public StartTimeWithinDaysPredicate(Integer days) {
        this.days = days;
    }

    @Override
    public boolean test(Event event) {
        LocalDateTime eventStartTime = event.getStartTime().time;
        boolean isStartTimeBeforeNow = LocalDateTime.now().compareTo(eventStartTime) > 0;
        if (isStartTimeBeforeNow) {
            return false;
        }
        long fullDaysBetween = LocalDateTime.now().until(eventStartTime, ChronoUnit.DAYS);
        return fullDaysBetween <= days;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StartTimeWithinDaysPredicate // instanceof handles nulls
                && days.equals(((StartTimeWithinDaysPredicate) other).days)); // state check
    }
}
