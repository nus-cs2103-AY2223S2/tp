package fasttrack.logic.commands.list;

import static fasttrack.logic.commands.CommandTestUtil.assertCommandSuccess;
import static fasttrack.logic.commands.list.ListRecurringExpensesCommand.MESSAGE_SUCCESS;
import static fasttrack.model.Model.PREDICATE_SHOW_ALL_EXPENSES;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import fasttrack.model.expense.Expense;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fasttrack.model.Model;
import fasttrack.model.ModelManager;
import fasttrack.model.UserPrefs;
import fasttrack.testutil.TypicalExpenses;

public class ListRecurringExpensesCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(TypicalExpenses.getTypicalExpenseTracker(), new UserPrefs());
        expectedModel = new ModelManager(model.getExpenseTracker(), new UserPrefs());
    }

    @Test
    public void execute_expenseListUpdated_success() {
        String expectedMessage = String.format(MESSAGE_SUCCESS);
        ListRecurringExpensesCommand command = new ListRecurringExpensesCommand();

        expectedModel.updateFilteredExpensesList(PREDICATE_SHOW_ALL_EXPENSES);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        List<Expense> expected = TypicalExpenses.getTypicalExpenses();
        List<Expense> actual = model.getFilteredExpenseList();
        for (List<Expense> expenses : Arrays.asList(expected, actual)) {
            Collections.sort(expenses);
        }
        assertEquals(
                expected,
                actual);
    }
}