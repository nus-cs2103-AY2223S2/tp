package fasttrack.logic.commands.delete;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import fasttrack.commons.core.index.Index;
import fasttrack.logic.commands.exceptions.CommandException;
import fasttrack.model.Model;
import fasttrack.model.ModelManager;
import fasttrack.model.category.MiscellaneousCategory;
import fasttrack.model.expense.Expense;

public class DeleteExpenseCommandTest {

    private Expense expenseToDelete = new Expense("test", "1.0",
                                    LocalDate.now(), new MiscellaneousCategory());
    private Expense expenseToDelete2 = new Expense("test2", "2.0",
                                    LocalDate.now(), new MiscellaneousCategory());
    private Index firstExpenseIdx = Index.fromOneBased(1);

    @Test
    public void execute_validIndexUnfilteredList_success() throws CommandException {
        Model model = new ModelManager();
        model.addExpense(expenseToDelete);
        model.addExpense(expenseToDelete2);
        DeleteExpenseCommand deleteExpenseCommand = new DeleteExpenseCommand(firstExpenseIdx);
        deleteExpenseCommand.execute(model);
        Model expectedModel = new ModelManager();
        expectedModel.addExpense(expenseToDelete2);
        assertEquals(expectedModel.getFilteredExpenseList(), model.getFilteredExpenseList());
    }

    @Test
    public void execute_invalidIndexUnfilteredList_failure() throws CommandException {
        Model model = new ModelManager();
        model.addExpense(expenseToDelete);
        model.addExpense(expenseToDelete2);
        DeleteExpenseCommand deleteExpenseCommand = new DeleteExpenseCommand(
            Index.fromOneBased(3));
        // command should throw an exception
        assertThrows(CommandException.class, () -> deleteExpenseCommand.execute(model));
    }

}
