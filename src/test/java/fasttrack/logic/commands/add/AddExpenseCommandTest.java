package fasttrack.logic.commands.add;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import fasttrack.logic.commands.exceptions.CommandException;
import fasttrack.model.Model;
import fasttrack.model.ModelManager;
import fasttrack.model.category.Category;
import fasttrack.model.category.UserDefinedCategory;
import fasttrack.model.expense.Expense;

public class AddExpenseCommandTest {
    private Model model = new ModelManager();
    private Category category = new UserDefinedCategory("test", "test");
    private LocalDate date = LocalDate.now();
    private Expense expense = new Expense("test", "1.0", date, category);
    private Expense expense2 = new Expense("test2", "2.0", date, category);
    private Expense expense3 = new Expense("test3", "3.0", date, category);
    private AddExpenseCommand addExpenseCommand = new AddExpenseCommand(expense);
    private AddExpenseCommand addExpenseCommand2 = new AddExpenseCommand(expense2);
    private AddExpenseCommand addExpenseCommand3 = new AddExpenseCommand(expense3);

    @Test
    public void addExpenseCommandTest() throws CommandException {
        requireNonNull(model);
        addExpenseCommand.execute(model);
        addExpenseCommand2.execute(model);
        addExpenseCommand3.execute(model);
        assertEquals(model.getFilteredExpenseList().get(0), expense);
        assertEquals(model.getFilteredExpenseList().get(1), expense2);
        assertEquals(model.getFilteredExpenseList().get(2), expense3);
    }

    @Test
    public void addExpenseCommandNullTest() {
        assertThrows(NullPointerException.class, () -> new AddExpenseCommand(null));
    }
}
