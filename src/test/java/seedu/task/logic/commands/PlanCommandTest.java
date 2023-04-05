package seedu.task.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.task.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.task.logic.commands.PlanCommand.PLAN_SUCCESS_MESSAGE;

import org.junit.jupiter.api.Test;

import seedu.task.model.Model;
import seedu.task.model.ModelManager;
import seedu.task.model.UserPrefs;
import seedu.task.model.task.Effort;

public class PlanCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_planEmptyTaskBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new PlanCommand(new Effort(15)), model, PLAN_SUCCESS_MESSAGE, expectedModel);
    }

    @Test
    public void execute_validEffort_success() {
        PlanCommand planCommand = new PlanCommand(new Effort(10));

        String expectedMessage = PlanCommand.PLAN_SUCCESS_MESSAGE;
        ModelManager expectedModel = new ModelManager(model.getTaskBook(), new UserPrefs(), model.getPlanner());

        expectedModel.plan(10);

        assertCommandSuccess(planCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        PlanCommand pcA = new PlanCommand(new Effort(10));
        PlanCommand pcB = new PlanCommand(new Effort(10));
        PlanCommand pcC = new PlanCommand(new Effort(15));

        // same object -> return true
        assertTrue(pcA.equals(pcA));

        // different object, same values -> return true
        assertTrue(pcA.equals(pcB));

        // different values -> return false
        assertFalse(pcA.equals(pcC));
    }
}
