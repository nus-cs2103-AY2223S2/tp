package fasttrack.logic.commands.list;

import static fasttrack.logic.commands.CommandTestUtil.assertCommandSuccess;
import static fasttrack.testutil.TypicalExpenses.APPLE;

import org.junit.jupiter.api.Test;

import fasttrack.logic.commands.CommandResult;
import fasttrack.model.Model;
import fasttrack.model.ModelManager;
import fasttrack.model.UserPrefs;
import fasttrack.testutil.TypicalRecurringExpenseManagers;
import fasttrack.ui.ScreenType;

public class ListRecurringExpensesCommandTest {

    private Model model = new ModelManager(TypicalRecurringExpenseManagers.getTypicalExpenseTracker(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalRecurringExpenseManagers.getTypicalExpenseTracker(),
            new UserPrefs());

    @Test
    public void execute() {
        ListRecurringExpensesCommand command = new ListRecurringExpensesCommand();
        CommandResult result = new CommandResult(ListRecurringExpensesCommand.MESSAGE_SUCCESS,
                ScreenType.RECURRING_EXPENSE_SCREEN);
        expectedModel.addExpense(APPLE);
        assertCommandSuccess(command, model, result, expectedModel);
    }
}
