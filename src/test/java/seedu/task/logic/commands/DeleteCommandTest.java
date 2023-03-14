package seedu.task.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.task.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.task.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.task.logic.commands.CommandTestUtil.showTaskAtIndexList;
import static seedu.task.testutil.TypicalDailyPlans.getTypicalDailyPlans;
import static seedu.task.testutil.TypicalIndexLists.INDEXLIST_FIRST_TASK;
import static seedu.task.testutil.TypicalIndexLists.INDEXLIST_SECOND_TASK;
import static seedu.task.testutil.TypicalTasks.getTypicalTaskBook;

import org.junit.jupiter.api.Test;

import seedu.task.commons.core.Messages;
import seedu.task.commons.core.index.Index;
import seedu.task.commons.core.index.IndexList;
import seedu.task.model.Model;
import seedu.task.model.ModelManager;
import seedu.task.model.UserPrefs;
import seedu.task.model.task.Task;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalTaskBook(), new UserPrefs(), getTypicalDailyPlans());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        DeleteCommand deleteCommand = new DeleteCommand(INDEXLIST_FIRST_TASK);

        String expectedMessage = DeleteCommand.MESSAGE_DELETE_TASK_SUCCESS;
        ModelManager expectedModel = new ModelManager(model.getTaskBook(), new UserPrefs(), model.getPlanner());

        int n = INDEXLIST_FIRST_TASK.size();

        for (int i = 0; i < n; i++) {
            Task taskToDelete = model.getFilteredTaskList().get(INDEXLIST_FIRST_TASK.getZeroBasedIndex(i));
            expectedMessage = expectedMessage + "\n" + taskToDelete;
            expectedModel.deleteTask(taskToDelete);
        }

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        IndexList il = new IndexList();
        il.add(outOfBoundIndex);

        DeleteCommand deleteCommand = new DeleteCommand(il);
        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndexList(model, INDEXLIST_FIRST_TASK);

        int n = INDEXLIST_FIRST_TASK.size();

        DeleteCommand deleteCommand = new DeleteCommand(INDEXLIST_FIRST_TASK);
        Model expectedModel = new ModelManager(model.getTaskBook(), new UserPrefs(), model.getPlanner());

        String expectedMessage = DeleteCommand.MESSAGE_DELETE_TASK_SUCCESS;

        for (int i = 0; i < n; i++) {
            Task taskToDelete = model.getFilteredTaskList().get(INDEXLIST_FIRST_TASK.getZeroBasedIndex(i));
            expectedMessage = expectedMessage + "\n" + taskToDelete;
            expectedModel.deleteTask(taskToDelete);
        }

        showNoTask(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndexList(model, INDEXLIST_FIRST_TASK);

        IndexList outOfBoundIndexList = INDEXLIST_SECOND_TASK;

        int n = outOfBoundIndexList.size();

        for (int i = 0; i < n; i++) {
            // ensures that outOfBoundIndexList is still in bounds of task book list
            assertTrue(outOfBoundIndexList.getZeroBasedIndex(i) < model.getTaskBook().getTaskList().size());
        }

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndexList);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEXLIST_FIRST_TASK);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEXLIST_SECOND_TASK);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEXLIST_FIRST_TASK);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoTask(Model model) {
        model.updateFilteredTaskList(p -> false);

        assertTrue(model.getFilteredTaskList().isEmpty());
    }
}
