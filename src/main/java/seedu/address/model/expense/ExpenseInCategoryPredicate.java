package seedu.address.model.expense;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.category.Category;
import seedu.address.model.person.Person;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class ExpenseInCategoryPredicate implements Predicate<Expense> {
    private final Category category;

    public ExpenseInCategoryPredicate(Category category) {
        this.category = category;
    }

    @Override
    public boolean test(Expense expense) {
        return expense.getCategory().isSameCategory(this.category);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ExpenseInCategoryPredicate // instanceof handles nulls
                && category.isSameCategory(((ExpenseInCategoryPredicate) other).category)); // state check
    }

}
