package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.stubs.ModelStub;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.category.Category;
import seedu.address.model.category.UserDefinedCategory;
import seedu.address.model.expense.Expense;

public class AddExpenseCommandTest {
    ModelStub model = new ModelStub();
    Category category = new UserDefinedCategory("test", "test");
    LocalDate date = LocalDate.now();
    Expense expense = new Expense("test", 1.0, date, category);
    Expense expense2 = new Expense("test2", 2.0, date, category);
    Expense expense3 = new Expense("test3", 3.0, date, category);

    @Test
    public void addExpenseCommandTest() throws CommandException {
        requireNonNull(model);
        model.addExpense(expense);
        model.addExpense(expense2);
        model.addExpense(expense3);
    }

    @Test
    public void addSameExpenseCommandTest() throws CommandException {
        requireNonNull(model);
        model.addExpense(expense);
        assertThrows(CommandException.class, () -> model.addExpense(expense));
    }
}
