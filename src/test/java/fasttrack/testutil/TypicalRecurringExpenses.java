package fasttrack.testutil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fasttrack.model.expense.RecurringExpenseManager;
import fasttrack.model.expense.RecurringExpenseType;


/**
 * A utility class containing a list of {@code RecurringExpenseManager} objects to be used in tests.
 */
public class TypicalRecurringExpenses {

    public static final RecurringExpenseManager GYM_MEMBERSHIP =
            new RecurringExpenseManager("Gym Membership", 50, TypicalCategories.FITNESS,
                    LocalDate.of(2023, 4, 1), null, RecurringExpenseType.MONTHLY);
    public static final RecurringExpenseManager NETFLIX_SUBSCRIPTION =
            new RecurringExpenseManager("Netflix Subscription", 15, TypicalCategories.ENTERTAINMENT,
                    LocalDate.of(2023, 2, 20), null, RecurringExpenseType.MONTHLY);
    public static final RecurringExpenseManager RENT =
            new RecurringExpenseManager("Rent", 800, TypicalCategories.HOUSING,
                    LocalDate.of(2023, 1, 15), null, RecurringExpenseType.MONTHLY);
    public static final RecurringExpenseManager INTERNET =
            new RecurringExpenseManager("Water", 60, TypicalCategories.UTILITIES,
                    LocalDate.of(2023, 4, 1), LocalDate.of(2026, 4, 1), RecurringExpenseType.MONTHLY);

    private TypicalRecurringExpenses() {} // prevents instantiation

    public static List<RecurringExpenseManager> getTypicalRecurringExpenses() {
        return new ArrayList<>(Arrays.asList(GYM_MEMBERSHIP, NETFLIX_SUBSCRIPTION, RENT, INTERNET));
    }
}

