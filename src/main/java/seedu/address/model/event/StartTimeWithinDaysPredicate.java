package seedu.address.model.event;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;

/**
 * Tests that a {@code Event}'s start {@code Time} is within the given number of days.
 */
public class StartTimeWithinDaysPredicate implements Predicate<Event> {
    private final LocalDateTime timeNow;
    private final Integer days;

    /**
     * Creates a predicate to test if an event is within the given {@code days} from
     * the given {@code timeNow}.
     */
    public StartTimeWithinDaysPredicate(LocalDateTime timeNow, Integer days) {
        this.timeNow = timeNow;
        this.days = days;
    }

    @Override
    public boolean test(Event event) {
        LocalDateTime eventStartTime = event.getStartTime().time;
        boolean isStartTimeBeforeNow = timeNow.compareTo(eventStartTime) > 0;
        if (isStartTimeBeforeNow) {
            return false;
        }
        long fullDaysBetween = timeNow.until(eventStartTime, ChronoUnit.DAYS);
        return fullDaysBetween <= days;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StartTimeWithinDaysPredicate // instanceof handles nulls
                && days.equals(((StartTimeWithinDaysPredicate) other).days)); // state check
    }
}
