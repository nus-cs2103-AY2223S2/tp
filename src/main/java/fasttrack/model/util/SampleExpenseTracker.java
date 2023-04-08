package fasttrack.model.util;

import java.time.LocalDate;

import fasttrack.model.Budget;
import fasttrack.model.ExpenseTracker;
import fasttrack.model.ReadOnlyExpenseTracker;
import fasttrack.model.category.Category;
import fasttrack.model.category.UserDefinedCategory;
import fasttrack.model.expense.Expense;

/**
 * Class containing sample data for ExpenseTracker to initialize if needed.
 */
public class SampleExpenseTracker {

    private static final Category food = new UserDefinedCategory("Food", "For food");
    private static final Category entertainment = new UserDefinedCategory("Entertainment", "For entertainment");
    private static final Category transportation = new UserDefinedCategory("Transportation", "For bus, car, train");
    private static final Category shopping = new UserDefinedCategory("Shopping", "");
    private static final Category housing = new UserDefinedCategory("Housing", "");
    /**
     * Sample data for categories
     * @return an array of sample categories.
     */
    public static Category[] getSampleCategories() {
        return new Category[] {
            food, entertainment, transportation, shopping, housing
        };
    }

    /**
     * Sample data for expenses
     * @return an array of sample expenses.
     */
    public static Expense[] getSampleExpenses() {
        return new Expense[] {
            new Expense("Meal at JE", "4.50", LocalDate.now(), food),
            new Expense("Movie ticket", "12.99", LocalDate.of(2023, 3, 15), entertainment),
            new Expense("MRT fare", "45.80", LocalDate.of(2023, 3, 10), transportation),
            new Expense("Shoes", "75.00", LocalDate.of(2023, 3, 20), shopping),
            new Expense("Groceries", "56.30", LocalDate.of(2023, 3, 25), food)
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
