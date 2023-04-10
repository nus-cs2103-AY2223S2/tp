package fasttrack.logic.commands.add;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import fasttrack.logic.commands.exceptions.CommandException;
import fasttrack.model.Model;
import fasttrack.model.ModelManager;
import fasttrack.model.category.Category;
import fasttrack.model.category.UserDefinedCategory;
import fasttrack.model.expense.Expense;
import fasttrack.model.expense.RecurringExpenseManager;
import fasttrack.model.expense.RecurringExpenseType;

public class AddRecurringExpenseCommandTest {
    private Model model = new ModelManager();
    private Category category = new UserDefinedCategory("test", "test");
    private LocalDate currentDate = LocalDate.now();
    private LocalDate endDate = currentDate.plusMonths(1);
    private LocalDate startDate = currentDate.minusMonths(1);
    private Expense expense = new Expense("test", "4.5", currentDate, category);
    private RecurringExpenseManager recurringExpense = new RecurringExpenseManager("test", 4.5,
        category, startDate, endDate, RecurringExpenseType.MONTHLY);

    private AddRecurringExpenseCommand addRecurringExpenseCommand = new AddRecurringExpenseCommand(recurringExpense);

    @Test
    public void addRecurringExpenseCommandTest() throws CommandException {
        requireNonNull(model);
        addRecurringExpenseCommand.execute(model);
        assertEquals(model.getFilteredExpenseList().get(0), expense);
        assertEquals(model.getFilteredExpenseList().size(), 2);
        assertEquals(model.getFilteredExpenseList().get(0).getAmount(), 4.5);
        assertEquals(model.getFilteredExpenseList().get(1).getDate(), startDate);
        assertEquals(model.getFilteredExpenseList().get(0).getDate(), currentDate);
    }
}
