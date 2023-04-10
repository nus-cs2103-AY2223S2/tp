package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.logic.commands.DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskBook;
import seedu.address.model.TaskBookModel;
import seedu.address.model.TaskBookModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.DeadlineTask;
import seedu.address.model.task.Task;
import seedu.address.testutil.DeadlineTaskBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteTaskCommand}.
 */
public class DeleteTaskCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private TaskBookModel taskBookModel = new TaskBookModelManager(new TaskBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        DeadlineTask validDeadlineTask = new DeadlineTaskBuilder().build();
        taskBookModel.addTask(validDeadlineTask);
        Task taskToDelete = taskBookModel.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        TaskBookModel expectedTaskBookModel = new TaskBookModelManager(taskBookModel.getTaskBook(), new UserPrefs());

        expectedTaskBookModel.deleteTask(taskToDelete);

        assertCommandSuccess(deleteTaskCommand, model, taskBookModel, expectedMessage,
            expectedModel, expectedTaskBookModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(taskBookModel.getFilteredTaskList().size() + 1);
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(outOfBoundIndex);

        assertCommandFailure(deleteTaskCommand, model, taskBookModel, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        DeadlineTask validDeadlineTask = new DeadlineTaskBuilder().build();
        taskBookModel.addTask(validDeadlineTask);
        showTaskAtIndex(taskBookModel, INDEX_FIRST_TASK);

        Task taskToDelete = taskBookModel.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_FIRST_TASK);
        String expectedMessage = String.format(MESSAGE_DELETE_TASK_SUCCESS, taskToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        TaskBookModel expectedTaskBookModel = new TaskBookModelManager(taskBookModel.getTaskBook(), new UserPrefs());
        expectedTaskBookModel.deleteTask(taskToDelete);
        showNoTask(expectedTaskBookModel);

        assertCommandSuccess(deleteTaskCommand, model, taskBookModel, expectedMessage,
            expectedModel, expectedTaskBookModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        DeadlineTask validDeadlineTask = new DeadlineTaskBuilder().build();
        taskBookModel.addTask(validDeadlineTask);
        showTaskAtIndex(taskBookModel, INDEX_FIRST_TASK);

        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() >= taskBookModel.getTaskBook().getTaskList().size());

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(outOfBoundIndex);

        assertCommandFailure(deleteTaskCommand, model, taskBookModel, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteTaskCommand deleteFirstTaskCommand = new DeleteTaskCommand(INDEX_FIRST_TASK);
        DeleteTaskCommand deleteSecondTaskCommand = new DeleteTaskCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(deleteFirstTaskCommand.equals(deleteFirstTaskCommand));

        // same values -> returns true
        DeleteTaskCommand deleteFirstTaskCommandCopy = new DeleteTaskCommand(INDEX_FIRST_TASK);
        assertTrue(deleteFirstTaskCommand.equals(deleteFirstTaskCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstTaskCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstTaskCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstTaskCommand.equals(deleteSecondTaskCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no task.
     */
    private void showNoTask(TaskBookModel taskBookModel) {
        taskBookModel.updateFilteredTaskList(p -> false);

        assertTrue(taskBookModel.getFilteredTaskList().isEmpty());
    }
}
