package seedu.address.model.person.predicates;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code AvailableDate} contains the given date range.
 */
public class AvailableDatesWithinRangePredicate<T extends Person> implements Predicate<T> {
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Constructs an {@code AvailableDatesWithinRangePredicate} with the given start and end dates.
     *
     * @param startDate Starting date.
     * @param endDate Ending date.
     */
    public AvailableDatesWithinRangePredicate(LocalDate startDate, LocalDate endDate) {
        requireAllNonNull(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean test(T object) {
        return object.getAvailableDates().stream().anyMatch(
                range -> !range.getStartDate().isAfter(startDate) && !range.getEndDate().isBefore(endDate));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AvailableDatesWithinRangePredicate // instanceof handles nulls
                && endDate.equals(((AvailableDatesWithinRangePredicate<?>) other).endDate)
                && startDate.equals(((AvailableDatesWithinRangePredicate<?>) other).startDate));
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }
}
