package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.stubs.ModelStub;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.category.Category;
import seedu.address.model.category.UserDefinedCategory;
import seedu.address.model.expense.RecurringExpenseManager;
import seedu.address.model.expense.RecurringExpenseType;

public class EditRecurringExpenseManagerCommandTest {
    private ModelStub model = new ModelStub();
    private Category standardCategory = new UserDefinedCategory("test", "test");

    private RecurringExpenseManager standardGenerator = new RecurringExpenseManager("standardName", 4.50,
            standardCategory, LocalDate.now(), RecurringExpenseType.MONTHLY);

    @Test
    public void execute_validNameChange_success() throws CommandException {
        model.addCategory(standardCategory);
        RecurringExpenseManager generatorToEdit = new RecurringExpenseManager("nameToChange", 4.50,
                standardCategory, LocalDate.now(), RecurringExpenseType.MONTHLY);
        model.addRecurringGenerator(generatorToEdit);
        EditRecurringExpenseManagerCommand editRecurringGeneratorCommand = new EditRecurringExpenseManagerCommand(
                Index.fromOneBased(1), "standardName", null, null,
                null, null);

        editRecurringGeneratorCommand.execute(model);
        assertEquals(generatorToEdit, standardGenerator);
    }

    @Test
    public void execute_validAmountChange_success() throws CommandException {
        model.addCategory(standardCategory);
        RecurringExpenseManager generatorToEdit = new RecurringExpenseManager("standardName", 300,
                standardCategory, LocalDate.now(), RecurringExpenseType.MONTHLY);
        model.addRecurringGenerator(generatorToEdit);
        EditRecurringExpenseManagerCommand editRecurringGeneratorCommand = new EditRecurringExpenseManagerCommand(
                Index.fromOneBased(1), null, 4.50, null,
                null, null);

        editRecurringGeneratorCommand.execute(model);
        assertEquals(generatorToEdit, standardGenerator);
    }

    @Test
    public void execute_validCategoryChange_success() throws CommandException {
        model.addCategory(standardCategory);
        Category toBeUsed = new UserDefinedCategory("placeholder category", "placeholder");
        RecurringExpenseManager generatorToEdit = new RecurringExpenseManager("standardName", 4.50,
                toBeUsed, LocalDate.now(), RecurringExpenseType.MONTHLY);
        model.addRecurringGenerator(generatorToEdit);
        EditRecurringExpenseManagerCommand editRecurringGeneratorCommand = new EditRecurringExpenseManagerCommand(
                Index.fromOneBased(1), null, null, standardCategory.getCategoryName(),
                null, null);

        editRecurringGeneratorCommand.execute(model);
        assertEquals(generatorToEdit, standardGenerator);
    }

    @Test
    public void execute_validFrequencyChange_success() throws CommandException {
        model.addCategory(standardCategory);
        RecurringExpenseManager generatorToEdit = new RecurringExpenseManager("nameToChange", 4.50,
                standardCategory, LocalDate.now(), RecurringExpenseType.YEARLY);
        model.addRecurringGenerator(generatorToEdit);
        EditRecurringExpenseManagerCommand editRecurringGeneratorCommand = new EditRecurringExpenseManagerCommand(
                Index.fromOneBased(1), null, null, null,
                "MONTHLY", null);

        editRecurringGeneratorCommand.execute(model);
        assertEquals(generatorToEdit, standardGenerator);
    }

    @Test
    public void execute_validEndDateChange_success() throws CommandException {
        model.addCategory(standardCategory);
        RecurringExpenseManager generatorToEdit = new RecurringExpenseManager("nameToChange", 4.50,
                standardCategory, LocalDate.parse("2021-12-03"), RecurringExpenseType.MONTHLY);
        model.addRecurringGenerator(generatorToEdit);
        EditRecurringExpenseManagerCommand editRecurringGeneratorCommand = new EditRecurringExpenseManagerCommand(
                Index.fromOneBased(1), null, null, null,
                null, standardGenerator.getExpenseEndDate());

        editRecurringGeneratorCommand.execute(model);
        assertEquals(generatorToEdit, standardGenerator);
    }

    @Test
    public void execute_invalidIndexInput() throws CommandException {
        model.addCategory(standardCategory);
        RecurringExpenseManager generatorToEdit = new RecurringExpenseManager("nameToChange", 4.50,
                standardCategory, LocalDate.parse("2021-12-03"), RecurringExpenseType.MONTHLY);
        model.addRecurringGenerator(generatorToEdit);
        EditRecurringExpenseManagerCommand editRecurringGeneratorCommand = new EditRecurringExpenseManagerCommand(
                Index.fromOneBased(3), null, null, null,
                null, null);
        assertThrows(CommandException.class, () -> editRecurringGeneratorCommand.execute(model));
    }

    @Test
    public void execute_invalidCategoryInput() throws CommandException {
        model.addCategory(standardCategory);
        RecurringExpenseManager generatorToEdit = new RecurringExpenseManager("nameToChange", 4.50,
                standardCategory, LocalDate.parse("2021-12-03"), RecurringExpenseType.MONTHLY);
        model.addRecurringGenerator(generatorToEdit);
        EditRecurringExpenseManagerCommand editRecurringGeneratorCommand = new EditRecurringExpenseManagerCommand(
                Index.fromOneBased(1), null, null, "Nonexistent",
                null, null);
        assertThrows(CommandException.class, () -> editRecurringGeneratorCommand.execute(model));
    }

    @Test
    public void execute_invalidAllInput() throws CommandException {
        model.addCategory(standardCategory);
        RecurringExpenseManager generatorToEdit = new RecurringExpenseManager("nameToChange", 4.50,
                standardCategory, LocalDate.parse("2021-12-03"), RecurringExpenseType.MONTHLY);
        model.addRecurringGenerator(generatorToEdit);
        EditRecurringExpenseManagerCommand editRecurringGeneratorCommand = new EditRecurringExpenseManagerCommand(
                Index.fromOneBased(1), null, null, null,
                null, null);
        assertThrows(CommandException.class, () -> editRecurringGeneratorCommand.execute(model));
    }
}
