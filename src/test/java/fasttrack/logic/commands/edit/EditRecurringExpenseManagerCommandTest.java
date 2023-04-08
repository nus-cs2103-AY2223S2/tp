package fasttrack.logic.commands.edit;

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

public class EditRecurringExpenseManagerCommandTest {
    private Model model;
    private Category category = new UserDefinedCategory("test", "test");
    private LocalDate currentDate = LocalDate.now();
    private LocalDate endDate = currentDate.plusMonths(1);
    private LocalDate startDate = currentDate.minusMonths(1);

    private RecurringExpenseManager recurringExpense = new RecurringExpenseManager("test", 4.50,
        category, startDate, endDate, RecurringExpenseType.MONTHLY);
    @Test
    public void execute_validNameChange_success() throws CommandException {
        model = new ModelManager();
        model.addCategory(category);
        model.addRecurringGenerator(recurringExpense);
        EditRecurringExpenseManagerCommand editRecurringGeneratorCommand = new EditRecurringExpenseManagerCommand(
                Index.fromOneBased(1), "standardName", null, null,
                null, null);

        editRecurringGeneratorCommand.execute(model);
        assertEquals(recurringExpense.getExpenseName(), "standardName");
    }

    @Test
    public void execute_validAmountChange_success() throws CommandException {
        model = new ModelManager();
        model.addCategory(category);
        model.addRecurringGenerator(recurringExpense);
        EditRecurringExpenseManagerCommand editRecurringGeneratorCommand = new EditRecurringExpenseManagerCommand(
                Index.fromOneBased(1), null, 25.5, null,
                null, null);

        editRecurringGeneratorCommand.execute(model);
        assertEquals(recurringExpense.getAmount(), 25.5);
    }

    @Test
    public void execute_validCategoryChange_success() throws CommandException {
        model = new ModelManager();
        model.addCategory(category);
        Category toBeUsed = new UserDefinedCategory("placeholder category", "placeholder");
        model.addRecurringGenerator(recurringExpense);
        model.addCategory(toBeUsed);
        EditRecurringExpenseManagerCommand editRecurringGeneratorCommand = new EditRecurringExpenseManagerCommand(
                Index.fromOneBased(1), null, null, toBeUsed.getCategoryName(),
                null, null);

        editRecurringGeneratorCommand.execute(model);
        assertEquals(recurringExpense.getExpenseCategory().getCategoryName(), toBeUsed.getCategoryName());

    }

    @Test
    public void execute_validFrequencyChange_success() throws CommandException {
        model = new ModelManager();
        model.addCategory(category);
        model.addRecurringGenerator(recurringExpense);

        EditRecurringExpenseManagerCommand editRecurringGeneratorCommand = new EditRecurringExpenseManagerCommand(
                Index.fromOneBased(1), null, null, null,
                "WEEKLY", null);

        editRecurringGeneratorCommand.execute(model);
        assertEquals(recurringExpense.getRecurringExpenseType(), RecurringExpenseType.WEEKLY);
    }

    @Test
    public void execute_validEndDateChange_success() throws CommandException {
        model = new ModelManager();
        model.addCategory(category);
        model.addRecurringGenerator(recurringExpense);
        EditRecurringExpenseManagerCommand editRecurringGeneratorCommand = new EditRecurringExpenseManagerCommand(
                Index.fromOneBased(1), null, null, null,
                null, LocalDate.parse("2023-12-03"));

        editRecurringGeneratorCommand.execute(model);
        assertEquals(recurringExpense.getExpenseEndDate(), LocalDate.parse("2023-12-03"));
    }

    @Test
    public void execute_invalidIndexInput() throws CommandException {
        model = new ModelManager();
        model.addCategory(category);
        model.addRecurringGenerator(recurringExpense);
        EditRecurringExpenseManagerCommand editRecurringGeneratorCommand = new EditRecurringExpenseManagerCommand(
                Index.fromOneBased(3), null, null, null,
                null, null);
        assertThrows(CommandException.class, () -> editRecurringGeneratorCommand.execute(model));
    }

    @Test
    public void execute_invalidCategoryInput() throws CommandException {
        model = new ModelManager();
        model.addCategory(category);
        model.addRecurringGenerator(recurringExpense);
        EditRecurringExpenseManagerCommand editRecurringGeneratorCommand = new EditRecurringExpenseManagerCommand(
                Index.fromOneBased(1), null, null, "Nonexistent",
                null, null);
        assertThrows(CommandException.class, () -> editRecurringGeneratorCommand.execute(model));
    }

    @Test
    public void execute_invalidAllInput() throws CommandException {
        model = new ModelManager();
        model.addCategory(category);
        model.addRecurringGenerator(recurringExpense);
        EditRecurringExpenseManagerCommand editRecurringGeneratorCommand = new EditRecurringExpenseManagerCommand(
                Index.fromOneBased(1), null, null, null,
                null, null);
        assertThrows(CommandException.class, () -> editRecurringGeneratorCommand.execute(model));
    }
}
