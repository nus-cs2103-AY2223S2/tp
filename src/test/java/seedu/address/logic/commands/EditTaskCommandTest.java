package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TASK_1;
import static seedu.address.logic.commands.CommandTestUtil.DESC_TASK_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_TYPE_T;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;
import static seedu.address.logic.commands.EditTaskCommand.MESSAGE_DUPLICATE_TASK;
import static seedu.address.logic.commands.EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TaskBookModel;
import seedu.address.model.TaskBookModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;
import seedu.address.testutil.EditTaskDescriptorBuilder;
import seedu.address.testutil.TaskBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditTaskCommand.
 */
public class EditTaskCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private TaskBookModel taskBookModel = new TaskBookModelManager(getTypicalTaskBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Task editedTask = new TaskBuilder().build();
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(editedTask).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_TASK, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        TaskBookModel expectedTaskBookModel = new TaskBookModelManager(taskBookModel.getTaskBook(), new UserPrefs());
        expectedTaskBookModel.setTask(taskBookModel.getFilteredTaskList().get(0), editedTask);

        assertCommandSuccess(editTaskCommand, model, taskBookModel, expectedMessage,
                expectedModel, expectedTaskBookModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastTask = Index.fromOneBased(taskBookModel.getFilteredTaskList().size());
        Task lastTask = taskBookModel.getFilteredTaskList().get(indexLastTask.getZeroBased());

        TaskBuilder taskInList = new TaskBuilder(lastTask);
        Task editedTask = taskInList.withTaskType(VALID_TASK_TYPE_T).build();

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTaskType(VALID_TASK_TYPE_T).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(indexLastTask, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        TaskBookModel expectedTaskBookModel = new TaskBookModelManager(taskBookModel.getTaskBook(), new UserPrefs());

        expectedTaskBookModel.setTask(lastTask, editedTask);

        assertCommandSuccess(editTaskCommand, model, taskBookModel, expectedMessage,
                expectedModel, expectedTaskBookModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_TASK, new EditTaskDescriptor());
        Task editedTask = taskBookModel.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());

        String expectedMessage = String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        TaskBookModel expectedTaskBookModel = new TaskBookModelManager(taskBookModel.getTaskBook(), new UserPrefs());

        assertCommandSuccess(editTaskCommand, model, taskBookModel, expectedMessage,
                expectedModel, expectedTaskBookModel);
    }

    @Test
    public void execute_filteredList_success() {
        showTaskAtIndex(taskBookModel, INDEX_FIRST_TASK);

        Task taskInFilteredList = taskBookModel.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task editedTask = new TaskBuilder(taskInFilteredList).withComment(VALID_COMMENT).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_TASK,
                new EditTaskDescriptorBuilder().withComment(VALID_COMMENT).build());

        String expectedMessage = String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        TaskBookModel expectedTaskBookModel = new TaskBookModelManager(taskBookModel.getTaskBook(), new UserPrefs());
        expectedTaskBookModel.setTask(taskBookModel.getFilteredTaskList().get(0), editedTask);

        assertCommandSuccess(editTaskCommand, model, taskBookModel, expectedMessage,
                expectedModel, expectedTaskBookModel);
    }

    @Test
    public void execute_duplicateTaskFilteredList_failure() {
        Task firstTask = taskBookModel.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(firstTask).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_SECOND_TASK, descriptor);

        assertCommandFailure(editTaskCommand, model, taskBookModel, MESSAGE_DUPLICATE_TASK);
    }

    @Test
    public void execute_invalidTaskIndexUnFilteredList_failure() {
        Index outOfBoundsIndex = Index.fromOneBased(taskBookModel.getFilteredTaskList().size() + 1);
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTaskType(VALID_TASK_TYPE_T).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(outOfBoundsIndex, descriptor);

        assertCommandFailure(editTaskCommand, model, taskBookModel, MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void equals() {
        final EditTaskCommand standardCommand = new EditTaskCommand(INDEX_FIRST_TASK, DESC_TASK_1);

        // same values -> returns true
        EditTaskDescriptor copyDescriptor = new EditTaskDescriptor(DESC_TASK_1);
        EditTaskCommand commandWithSameValues = new EditTaskCommand(INDEX_FIRST_TASK, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditTaskCommand(INDEX_SECOND_TASK, DESC_TASK_1)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditTaskCommand(INDEX_FIRST_TASK, DESC_TASK_2)));
    }
}
