package seedu.event.model.event;

import java.time.Clock;
import java.time.LocalDate;
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
    public StartTimeWithinDaysPredicate(Clock clock, Integer days) {
        this.timeNow = LocalDateTime.now(clock);
        this.days = days;
    }

    @Override
    public boolean test(Event event) {
        LocalDateTime eventStartTime = event.getStartTime().time;
        boolean isStartTimeBeforeNow = timeNow.compareTo(eventStartTime) > 0;
        if (isStartTimeBeforeNow) {
            return false;
        }

        LocalDate dateNow = timeNow.toLocalDate();
        LocalDate eventStartDate = eventStartTime.toLocalDate();

        long fullDaysBetween = ChronoUnit.DAYS.between(dateNow, eventStartDate);
        return fullDaysBetween <= days;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StartTimeWithinDaysPredicate // instanceof handles nulls
                && days.equals(((StartTimeWithinDaysPredicate) other).days)); // state check
    }
}
