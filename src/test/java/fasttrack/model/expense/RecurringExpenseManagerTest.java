package fasttrack.model.expense;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import fasttrack.model.category.MiscellaneousCategory;

public class RecurringExpenseManagerTest {
    private LocalDate startDate = LocalDate.of(2023, 1, 1);
    private LocalDate endDate = LocalDate.of(2023, 1, 1);
    private MiscellaneousCategory miscellaneousCategory = new MiscellaneousCategory();

    private final RecurringExpenseManager recurringExpenseManager = new RecurringExpenseManager("test", 1.0,
            miscellaneousCategory, startDate, endDate, RecurringExpenseType.MONTHLY);

    private final ArrayList<Expense> expenseList = recurringExpenseManager.getExpenses();

    @Test
    public void getExpenses() {
        assertEquals(expenseList.get(0).getName(), "test");
        assertEquals(expenseList.get(0).getAmount(), 1.0);
        assertEquals(expenseList.get(0).getCategory(), miscellaneousCategory);
        assertEquals(expenseList.get(0).getDate(), startDate);
    }

    @Test
    public void getExpenseListSize() {
        assertEquals(recurringExpenseManager.getNumberOfExpenses(), expenseList.size());
    }

    @Test
    public void getExpenseListTotal() {
        assertEquals(recurringExpenseManager.getTotalAmount(),
            expenseList.get(0).getAmount() * expenseList.size());
    }
}
