package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.stubs.ModelStub;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.category.Category;
import seedu.address.model.category.UserDefinedCategory;
import seedu.address.model.expense.Expense;

public class AddExpenseCommandTest {
    private ModelStub model = new ModelStub();
    private Category category = new UserDefinedCategory("test", "test");
    private LocalDate date = LocalDate.now();
    private Expense expense = new Expense("test", "1.0", date, category);
    private Expense expense2 = new Expense("test2", "2.0", date, category);
    private Expense expense3 = new Expense("test3", "3.0", date, category);

    @Test
    public void addExpenseCommandTest() throws CommandException {
        requireNonNull(model);
        model.addExpense(expense);
        model.addExpense(expense2);
        model.addExpense(expense3);
        assertEquals(model.getFilteredExpenseList().get(0), expense);
        assertEquals(model.getFilteredExpenseList().get(1), expense2);
        assertEquals(model.getFilteredExpenseList().get(2), expense3);
    }

    @Test
    public void addSameExpenseCommandTest() throws CommandException {
        requireNonNull(model);
        model.addExpense(expense);
        assertThrows(CommandException.class, () -> model.addExpense(expense));
    }
}
