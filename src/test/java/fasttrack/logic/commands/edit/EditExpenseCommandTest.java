package fasttrack.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import fasttrack.commons.core.index.Index;
import fasttrack.logic.commands.exceptions.CommandException;
import fasttrack.model.Model;
import fasttrack.model.ModelManager;
import fasttrack.model.category.Category;
import fasttrack.model.category.UserDefinedCategory;
import fasttrack.model.expense.Expense;

public class EditExpenseCommandTest {

    private Model model;
    private Category category = new UserDefinedCategory("test", "test");
    private Category newCategory = new UserDefinedCategory("newCat", "test2");
    private LocalDate date = LocalDate.now();
    private Expense expense = new Expense("expense", "4.00", date,
            category);


    @Test
    public void execute_validInput_success() throws CommandException {
        model = new ModelManager();
        model.addCategory(category);
        model.addExpense(expense);
        EditExpenseCommand editExpenseCommand = new EditExpenseCommand(Index.fromOneBased(1),
                "NewExpenseName", null, null,
                category.getCategoryName());
        editExpenseCommand.execute(model);
        assertEquals("NewExpenseName", model.getFilteredExpenseList().get(0).getName());
    }

    @Test
    public void execute_invalidIndexInput() throws CommandException {
        model = new ModelManager();
        model.addCategory(category);
        model.addExpense(expense);
        EditExpenseCommand editExpenseCommand = new EditExpenseCommand(Index.fromOneBased(2),
                "NewExpenseName", null, null,
                category.getCategoryName());
        assertThrows(CommandException.class, () -> editExpenseCommand.execute(model));
    }

    @Test
    public void execute_invalidCategoryInput() throws CommandException {
        model = new ModelManager();
        model.addCategory(category);
        model.addExpense(expense);
        EditExpenseCommand editExpenseCommand = new EditExpenseCommand(Index.fromOneBased(1),
                null, null, null,
                "Nonexistent");
        assertThrows(CommandException.class, () -> editExpenseCommand.execute(model));
    }

    @Test
    public void execute_invalidInputAll() throws CommandException {
        model = new ModelManager();
        model.addCategory(category);
        model.addExpense(expense);
        EditExpenseCommand editExpenseCommand = new EditExpenseCommand(Index.fromOneBased(1),
                null, null, null,
                null);
        assertThrows(CommandException.class, () -> editExpenseCommand.execute(model));
    }

    @Test
    public void execute_testEquals() {
        model = new ModelManager();
        model.addCategory(category);
        model.addExpense(expense);
        EditExpenseCommand editExpenseCommand = new EditExpenseCommand(Index.fromOneBased(1),
                "NewExpenseName", null, null,
                category.getCategoryName());
        EditExpenseCommand sameExpenseCommand = new EditExpenseCommand(Index.fromOneBased(1),
                "NewExpenseName", null, null,
                category.getCategoryName());
        assertEquals(editExpenseCommand, sameExpenseCommand);
    }

    @Test
    public void execute_testNotEquals() {
        model = new ModelManager();
        model.addCategory(category);
        model.addExpense(expense);
        EditExpenseCommand editExpenseCommand = new EditExpenseCommand(Index.fromOneBased(1),
                "NewExpenseName", null, null,
                category.getCategoryName());
        EditExpenseCommand sameExpenseCommand = new EditExpenseCommand(Index.fromOneBased(1),
                "NewExpenseName", 200.0, null,
                category.getCategoryName());
        assertNotEquals(editExpenseCommand, sameExpenseCommand);
    }
}
