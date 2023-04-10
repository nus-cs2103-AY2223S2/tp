package fasttrack.model;

import fasttrack.model.category.Category;
import fasttrack.model.expense.Expense;
import fasttrack.model.expense.RecurringExpenseManager;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;

/**
 * Unmodifiable view of an expense tracker
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
