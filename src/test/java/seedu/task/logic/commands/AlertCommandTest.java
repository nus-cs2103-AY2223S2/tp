package seedu.task.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.task.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.task.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.task.testutil.TypicalComplicatedTasks.getTypicalComplicatedTasks;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import seedu.task.commons.core.Messages;
import seedu.task.model.Model;
import seedu.task.model.ModelManager;
import seedu.task.model.UserPrefs;
import seedu.task.model.task.TaskWithinTimelinePredicate;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code AlertCommand}.
 */
public class AlertCommandTest {
    private Model model = new ModelManager(getTypicalComplicatedTasks(), new UserPrefs());

    @Test
    public void execute_validIndexAlertList_success() {
        AlertCommand alertCommand = new AlertCommand(48);
        String expectedMessage = AlertCommand.ALERT_COMMAND_SUCCESS;
        ModelManager expectedModel = new ModelManager(model.getTaskBook(), new UserPrefs());
        expectedModel.updateAlertTaskList(new TaskWithinTimelinePredicate(Duration.ofHours(48)));
        assertCommandSuccess(alertCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexAlertList_throwsCommandException() {
        AlertCommand alertCommand = new AlertCommand(0);
        assertCommandFailure(alertCommand, model, Messages.MESSAGE_INVALID_DURATION);
    }

    @Test
    public void test_equals() {
        AlertCommand alertCommand = new AlertCommand(5);
        AlertCommand alertCommand2 = new AlertCommand(5);
        AlertCommand alertCommand3 = new AlertCommand(3);
        assertTrue(alertCommand2.equals(alertCommand));
        assertFalse(alertCommand2.equals(alertCommand3));

    }
}
