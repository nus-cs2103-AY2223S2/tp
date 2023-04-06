package fasttrack.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import fasttrack.commons.core.index.Index;
import fasttrack.commons.stubs.ModelStub;
import fasttrack.logic.commands.edit.EditExpenseCommand;
import fasttrack.logic.commands.exceptions.CommandException;
import fasttrack.model.category.Category;
import fasttrack.model.category.UserDefinedCategory;
import fasttrack.model.expense.Expense;

public class EditExpenseCommandTest {

    private ModelStub model = new ModelStub();
    private Category standardCategory = new UserDefinedCategory("test", "test");

    private Expense standardExpense = new Expense("NewExpenseName", "4.00", LocalDate.now(),
            standardCategory);

    private Expense toEdit = new Expense("testExpense", "4.00", LocalDate.now(),
            new UserDefinedCategory("toBeReplaced", "randomSummary"));


    @Test
    public void execute_validInput_success() throws CommandException {
        model.addCategory(standardCategory);
        model.addExpense(toEdit);
        EditExpenseCommand editExpenseCommand = new EditExpenseCommand(Index.fromOneBased(1),
                "NewExpenseName", null, null,
                standardCategory.getCategoryName());
        editExpenseCommand.execute(model);
        assertEquals(toEdit, standardExpense);
    }

    @Test
    public void execute_invalidIndexInput() throws CommandException {
        model.addCategory(standardCategory);
        model.addExpense(toEdit);
        EditExpenseCommand editExpenseCommand = new EditExpenseCommand(Index.fromOneBased(2),
                "NewExpenseName", null, null,
                standardCategory.getCategoryName());
        assertThrows(CommandException.class, () -> editExpenseCommand.execute(model));
    }

    @Test
    public void execute_invalidCategoryInput() throws CommandException {
        model.addCategory(standardCategory);
        model.addExpense(toEdit);
        EditExpenseCommand editExpenseCommand = new EditExpenseCommand(Index.fromOneBased(1),
                null, null, null,
                "Nonexistent");
        assertThrows(CommandException.class, () -> editExpenseCommand.execute(model));
    }

    @Test
    public void execute_invalidInputAll() throws CommandException {
        model.addCategory(standardCategory);
        model.addExpense(toEdit);
        EditExpenseCommand editExpenseCommand = new EditExpenseCommand(Index.fromOneBased(1),
                null, null, null,
                null);
        assertThrows(CommandException.class, () -> editExpenseCommand.execute(model));
    }
}
