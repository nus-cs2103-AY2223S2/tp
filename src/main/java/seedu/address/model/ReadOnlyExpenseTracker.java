package seedu.address.model;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import seedu.address.model.category.Category;
import seedu.address.model.expense.Expense;
import seedu.address.model.expense.RecurringExpenseManager;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyExpenseTracker {

    /**
     * Returns an unmodifiable view of the category list.
     * This list will not contain any duplicate categories.
     */
    ObservableList<Category> getCategoryList();

    /**
     * Returns an unmodifiable view of the expense list.
     */
    ObservableList<Expense> getExpenseList();

    Budget getBudget();

    ObjectProperty<Budget> getBudgetForStats();

    ObservableList<RecurringExpenseManager> getRecurringExpenseGenerators();

}
