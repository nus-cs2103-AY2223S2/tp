package trackr.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.logic.commands.CommandTestUtil.assertCommandFailure;
import static trackr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackr.logic.commands.CommandTestUtil.showTaskAtIndex;
import static trackr.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
import static trackr.testutil.TypicalIndexes.INDEX_SECOND_OBJECT;
import static trackr.testutil.TypicalSuppliers.getTypicalSupplierList;
import static trackr.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.Test;

import trackr.commons.core.Messages;
import trackr.commons.core.index.Index;
import trackr.model.Model;
import trackr.model.ModelManager;
import trackr.model.UserPrefs;
import trackr.model.task.Task;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteTaskCommand}.
 */
public class DeleteTaskCommandTest {

    private Model model = new ModelManager(getTypicalSupplierList(), getTypicalTaskList(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredTaskList_success() {
        Task taskToDelete = model.getFilteredTaskList().get(INDEX_FIRST_OBJECT.getZeroBased());
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_FIRST_OBJECT);

        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS, taskToDelete);

        ModelManager expectedModel = new ModelManager(model.getSupplierList(), model.getTaskList(), new UserPrefs());
        expectedModel.deleteTask(taskToDelete);

        assertCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredTaskList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(outOfBoundIndex);

        assertCommandFailure(deleteTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredTaskList_success() {
        showTaskAtIndex(model, INDEX_FIRST_OBJECT);

        Task taskToDelete = model.getFilteredTaskList().get(INDEX_FIRST_OBJECT.getZeroBased());
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_FIRST_OBJECT);

        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS, taskToDelete);

        Model expectedModel = new ModelManager(model.getSupplierList(), model.getTaskList(), new UserPrefs());
        expectedModel.deleteTask(taskToDelete);
        showNoTask(expectedModel);

        assertCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredTaskList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_OBJECT);

        Index outOfBoundIndex = INDEX_SECOND_OBJECT;
        // ensures that outOfBoundIndex is still in bounds of task list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTaskList().getTaskList().size());

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(outOfBoundIndex);

        assertCommandFailure(deleteTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteTaskCommand deleteTaskFirstCommand = new DeleteTaskCommand(INDEX_FIRST_OBJECT);
        DeleteTaskCommand deleteTaskSecondCommand = new DeleteTaskCommand(INDEX_SECOND_OBJECT);

        // same object -> returns true
        assertTrue(deleteTaskFirstCommand.equals(deleteTaskFirstCommand));

        // same values -> returns true
        DeleteTaskCommand deleteTaskFirstCommandCopy = new DeleteTaskCommand(INDEX_FIRST_OBJECT);
        assertTrue(deleteTaskFirstCommand.equals(deleteTaskFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteTaskFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteTaskFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(deleteTaskFirstCommand.equals(deleteTaskSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered task list to show no one.
     */
    private void showNoTask(Model model) {
        model.updateFilteredTaskList(p -> false);

        assertTrue(model.getFilteredTaskList().isEmpty());
    }
}
