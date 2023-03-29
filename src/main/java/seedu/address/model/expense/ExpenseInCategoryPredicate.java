package seedu.address.model.expense;

import java.util.function.Predicate;

import seedu.address.model.category.Category;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class ExpenseInCategoryPredicate implements Predicate<Expense> {
    private final Category category;

    public ExpenseInCategoryPredicate(Category category) {
        this.category = category;
    }

    /**
     * Get Category that this {@code ExpenseInCategoryPredicate}
     * @return
     */
    public Category getCategory() {
        return this.category;
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
