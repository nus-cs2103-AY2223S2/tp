package seedu.address.model.util;

import java.time.LocalDate;

import seedu.address.model.Budget;
import seedu.address.model.ExpenseTracker;
import seedu.address.model.ReadOnlyExpenseTracker;
import seedu.address.model.category.Category;
import seedu.address.model.category.UserDefinedCategory;
import seedu.address.model.expense.Expense;

/**
 * Class containing sample data for ExpenseTracker to initialize if needed.
 */
public class SampleExpenseTracker {
    /**
     * Sample data for categories
     * @return an array of sample categories.
     */
    public static Category[] getSampleCategories() {
        return new Category[] {
            new UserDefinedCategory("Food", "For food"),
            new UserDefinedCategory("Entertainment", "For entertainment")
        };
    }

    /**
     * Sample data for expenses
     * @return an array of sample expenses.
     */
    public static Expense[] getSampleExpenses() {
        return new Expense[] {
            new Expense("Meal at JE", 4.50, LocalDate.now(), new UserDefinedCategory("Food", "For food"))
        };
    }

    /**
     * Sets all required sample data for categories and expenses.
     * @return ReadOnlyExpenseTracker to be read from.
     */
    public static ReadOnlyExpenseTracker getSampleExpenseTracker() {
        ExpenseTracker sampleExpenseTracker = new ExpenseTracker();
        for (Category sampleCategory : getSampleCategories()) {
            sampleExpenseTracker.addCategory(sampleCategory);
        }

        for (Expense sampleExpense : getSampleExpenses()) {
            if (sampleExpenseTracker.getCategoryInstance(sampleExpense.getCategory()) != null) {
                sampleExpense.setCategory(sampleExpenseTracker.getCategoryInstance(sampleExpense.getCategory()));
            }
            sampleExpenseTracker.addExpense(sampleExpense);
        }

        sampleExpenseTracker.setBudget(new Budget(0));
        return sampleExpenseTracker;
    }
}
