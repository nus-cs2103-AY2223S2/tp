package fasttrack.logic.commands.delete;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import fasttrack.commons.core.index.Index;
import fasttrack.logic.commands.exceptions.CommandException;
import fasttrack.model.Model;
import fasttrack.model.ModelManager;
import fasttrack.model.category.Category;
import fasttrack.model.category.UserDefinedCategory;
import fasttrack.model.expense.RecurringExpenseManager;
import fasttrack.model.expense.RecurringExpenseType;

public class DeleteRecurringExpenseCommandTest {

    private Category category = new UserDefinedCategory("test", "test");
    private LocalDate currentDate = LocalDate.now();
    private LocalDate endDate = currentDate.plusMonths(1);
    private LocalDate startDate = currentDate.minusMonths(1);

    private RecurringExpenseManager recurringExpense = new RecurringExpenseManager("test", 4.5,
        category, startDate, endDate, RecurringExpenseType.MONTHLY);
    private Index firstExpenseIdx = Index.fromOneBased(1);

    @Test
    public void execute_validIndexUnfilteredList_success() throws CommandException {
        Model model = new ModelManager();
        model.addRecurringGenerator(recurringExpense);
        DeleteRecurringExpenseCommand deleteRecurringExpenseCommand = new
            DeleteRecurringExpenseCommand(firstExpenseIdx);
        deleteRecurringExpenseCommand.execute(model);
        Model expectedModel = new ModelManager();
        assertEquals(expectedModel.getRecurringExpenseGenerators(), model.getRecurringExpenseGenerators());
    }

    @Test
    public void execute_invalidIndexUnfilteredList_failure() throws CommandException {
        Model model = new ModelManager();
        model.addRecurringGenerator(recurringExpense);
        DeleteRecurringExpenseCommand deleteRecurringExpenseCommand = new DeleteRecurringExpenseCommand(
            Index.fromOneBased(3));
        // command should throw an exception
        assertThrows(CommandException.class, () -> deleteRecurringExpenseCommand.execute(model));
    }
}
