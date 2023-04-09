package fasttrack.testutil;

import static fasttrack.testutil.TypicalCategories.FOOD;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fasttrack.model.Budget;
import fasttrack.model.ExpenseTracker;
import fasttrack.model.expense.RecurringExpenseManager;
import fasttrack.model.expense.RecurringExpenseType;

/**
 * A utility class containing a list of {@code RecurringExpenseManager} objects to be used in tests.
 */
public class TypicalRecurringExpenseManagers {

    public static final RecurringExpenseManager RECUR_APPLE_DAY =
            new RecurringExpenseManager("Apple", 1.50, FOOD,
                    LocalDate.of(2023, 3, 1), RecurringExpenseType.DAILY);
    public static final RecurringExpenseManager RECUR_APPLE_DAY_WITH_END =
            new RecurringExpenseManager("Apple", 1.50, FOOD,
                    LocalDate.of(2023, 3, 1), LocalDate.of(2024, 3, 2),
                    RecurringExpenseType.DAILY);
    public static final RecurringExpenseManager RECUR_APPLE_WEEK =
            new RecurringExpenseManager("Apple", 1.50, FOOD,
                    LocalDate.of(2023, 3, 1), RecurringExpenseType.WEEKLY);
    public static final RecurringExpenseManager RECUR_APPLE_WEEK_WITH_END =
            new RecurringExpenseManager("Apple", 1.50, FOOD,
                    LocalDate.of(2023, 3, 1), LocalDate.of(2024, 3, 2),
                    RecurringExpenseType.WEEKLY);
    public static final RecurringExpenseManager RECUR_APPLE_MONTH =
            new RecurringExpenseManager("Apple", 1.50, FOOD,
                    LocalDate.of(2023, 3, 1), RecurringExpenseType.MONTHLY);
    public static final RecurringExpenseManager RECUR_APPLE_MONTH_WITH_END =
            new RecurringExpenseManager("Apple", 1.50, FOOD,
                    LocalDate.of(2023, 3, 1), LocalDate.of(2024, 3, 2),
                    RecurringExpenseType.MONTHLY);
    public static final RecurringExpenseManager RECUR_APPLE_YEAR =
            new RecurringExpenseManager("Apple", 1.50, FOOD,
                    LocalDate.of(2023, 3, 1), RecurringExpenseType.YEARLY);
    public static final RecurringExpenseManager RECUR_APPLE_YEAR_WITH_END =
            new RecurringExpenseManager("Apple", 1.50, FOOD,
                    LocalDate.of(2023, 3, 1), LocalDate.of(2024, 3, 2),
                    RecurringExpenseType.YEARLY);

    /**
     * Returns an {@code ExpenseTracker} with all the typical expenses.
     */
    public static ExpenseTracker getTypicalExpenseTracker() {
        ExpenseTracker et = new ExpenseTracker();
        for (RecurringExpenseManager manager : getTypicalManagers()) {
            et.addRecurringGenerator(manager);
        }
        et.setBudget(new Budget(1000));
        return et;
    }

    public static List<RecurringExpenseManager> getTypicalManagers() {
        return new ArrayList<>(Arrays.asList(RECUR_APPLE_YEAR));
    }
}
