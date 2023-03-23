package seedu.address.model.expense;

import java.time.LocalDate;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class ExpenseInTimespanPredicate implements Predicate<Expense> {
    private final LocalDate timespan;

    public ExpenseInTimespanPredicate(LocalDate timespan) {
        this.timespan = timespan;
    }

    @Override
    public boolean test(Expense expense) {
        return expense.getDate().isAfter(this.timespan) || expense.getDate().isEqual(this.timespan);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpenseInTimespanPredicate // instanceof handles nulls
                && timespan.isEqual(((ExpenseInTimespanPredicate) other).timespan)); // state check
    }

}
