package fasttrack.logic.commands.list;


import static fasttrack.logic.commands.CommandTestUtil.assertCommandSuccess;
import static fasttrack.logic.commands.list.ListCategoryCommand.MESSAGE_SUCCESS;
import static fasttrack.model.Model.PREDICATE_SHOW_ALL_EXPENSES;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fasttrack.model.Model;
import fasttrack.model.ModelManager;
import fasttrack.model.UserPrefs;
import fasttrack.testutil.TypicalExpenses;

public class ListCategoriesCommandTest {

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
        ListCategoryCommand command = new ListCategoryCommand();

        expectedModel.updateFilteredExpensesList(PREDICATE_SHOW_ALL_EXPENSES);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        assertEquals(
                Arrays.asList(TypicalExpenses.getTypicalExpenses()),
                model.getFilteredExpenseList());
    }
}
