package seedu.task.logic.commands;

import static seedu.task.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.task.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.task.testutil.TypicalDailyPlans.getTypicalPlanner;
import static seedu.task.testutil.TypicalTasks.getTypicalTaskBook;

import org.junit.jupiter.api.Test;

import seedu.task.commons.core.Messages;
import seedu.task.model.Model;
import seedu.task.model.ModelManager;
import seedu.task.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code planCommand}.
 */
public class PlanCommandTest {
    private Model model = new ModelManager(getTypicalTaskBook(), new UserPrefs(), getTypicalPlanner());

    @Test
    public void execute_validEffort_success() {
        PlanCommand planCommand = new PlanCommand(5);

        String expectedMessage = PlanCommand.PLAN_SUCCESS_MESSAGE;
        ModelManager expectedModel = new ModelManager(model.getTaskBook(), new UserPrefs(), model.getPlanner());

        expectedModel.plan(5);

        assertCommandSuccess(planCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidEffort_throwsCommandException() {
        PlanCommand planCommand = new PlanCommand(-5);
        assertCommandFailure(planCommand, model, Messages.MESSAGE_INVALID_EFFORT);
    }

    @Test
    public void execute_planEmptyTaskBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new PlanCommand(5), model, PlanCommand.PLAN_SUCCESS_MESSAGE, expectedModel);
    }
}
