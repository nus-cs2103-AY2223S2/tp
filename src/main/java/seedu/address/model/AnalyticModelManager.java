package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.logging.Logger;

import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.category.Category;
import seedu.address.model.expense.Expense;

/**
 * Represents the in-memory model of user analytics from the expense tracker
 */
public class AnalyticModelManager implements AnalyticModel {
    private static final Logger logger = LogsCenter.getLogger(AnalyticModelManager.class);

    private final ExpenseTracker expenseTracker;
    private final FilteredList<Expense> filteredExpenses;
    private final FilteredList<Category> filteredCategories;

    /**
     * Initializes an AnalyticModelManager with the given addressBook and userPrefs.
     */
    public AnalyticModelManager(ReadOnlyExpenseTracker expenseTracker) {
        requireNonNull(expenseTracker);
        logger.fine("Initializing with expense tracker: " + expenseTracker);
        this.expenseTracker = new ExpenseTracker(expenseTracker);
        filteredExpenses = new FilteredList<>(this.expenseTracker.getExpenseList());
        filteredCategories = new FilteredList<>(this.expenseTracker.getCategoryList());
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof AnalyticModelManager)) {
            return false;
        }

        // state check
        AnalyticModelManager other = (AnalyticModelManager) obj;
        return expenseTracker.equals(other.expenseTracker)
                && filteredExpenses.equals(other.filteredExpenses)
                && filteredCategories.equals(other.filteredCategories);
    }




}
