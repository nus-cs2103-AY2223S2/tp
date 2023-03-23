package seedu.address.model.util;

import seedu.address.model.ExpenseTracker;
import seedu.address.model.ReadOnlyExpenseTracker;
import seedu.address.model.category.Category;
import seedu.address.model.category.UserDefinedCategory;
import seedu.address.model.expense.Expense;

import java.time.LocalDate;

public class SampleExpenseTracker {
    public static Category[] getSampleCategories() {
        return new Category[] {
                new UserDefinedCategory("Food", "For food"),
                new UserDefinedCategory("Entertainment", "For entertainment")
        };
    }

    public static Expense[] getSampleExpenses() {
        return new Expense[] {
                new Expense("Meal at JE", 4.50, LocalDate.now(), new UserDefinedCategory("Food", "For food"))
        };
    }

    public static ReadOnlyExpenseTracker getSampleExpenseTracker() {
        ExpenseTracker sampleExpenseTracker = new ExpenseTracker();
        for (Category sampleCategory : getSampleCategories()) {
            sampleExpenseTracker.addCategory(sampleCategory);
        }

        for (Expense sampleExpense : getSampleExpenses()) {
            if (sampleExpenseTracker.getCategoryInstance(sampleExpense.getCategory().getCategoryName()) != null)
                sampleExpense.setCategory(sampleExpenseTracker.getCategoryInstance(sampleExpense.getCategory()
                        .getCategoryName()));
            sampleExpenseTracker.addExpense(sampleExpense);
        }
        return sampleExpenseTracker;
    }
}
