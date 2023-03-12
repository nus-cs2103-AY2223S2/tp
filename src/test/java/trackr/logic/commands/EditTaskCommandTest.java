package trackr.logic.commands;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.logic.commands.CommandTestUtil.DESC_BUY_FLOUR;
import static trackr.logic.commands.CommandTestUtil.DESC_SORT_INVENTORY;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_2100;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_NAME_SORT_INVENTORY;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_STATUS_DONE;
import static trackr.logic.commands.CommandTestUtil.assertCommandFailure;
import static trackr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackr.logic.commands.CommandTestUtil.showTaskAtIndex;
import static trackr.logic.commands.EditTaskCommand.MESSAGE_DUPLICATE_TASK;
import static trackr.logic.commands.EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS;
import static trackr.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
import static trackr.testutil.TypicalIndexes.INDEX_SECOND_OBJECT;
import static trackr.testutil.TypicalPersons.getTypicalAddressBook;
import static trackr.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.Test;

import trackr.commons.core.Messages;
import trackr.commons.core.index.Index;
import trackr.logic.commands.EditTaskCommand.EditTaskDescriptor;
import trackr.model.AddressBook;
import trackr.model.Model;
import trackr.model.ModelManager;
import trackr.model.TaskList;
import trackr.model.UserPrefs;
import trackr.model.task.Task;
import trackr.testutil.EditTaskDescriptorBuilder;
import trackr.testutil.TaskBuilder;

public class EditTaskCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalTaskList(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredTaskList_success() {
        Task editedTask = new TaskBuilder().build();
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(editedTask).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_OBJECT, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new TaskList(model.getTaskList()), new UserPrefs());
        expectedModel.setTask(model.getFilteredTaskList().get(0), editedTask);

        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredTaskList_success() {
        Index indexLastTask = Index.fromOneBased(model.getFilteredTaskList().size());
        Task lastTask = model.getFilteredTaskList().get(indexLastTask.getZeroBased());

        TaskBuilder taskInList = new TaskBuilder(lastTask);
        Task editedTask = taskInList.withTaskName(VALID_TASK_NAME_SORT_INVENTORY)
                .withTaskDeadline(VALID_TASK_DEADLINE_2100)
                .withTaskStatus(VALID_TASK_STATUS_DONE).build();

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withTaskName(VALID_TASK_NAME_SORT_INVENTORY)
                .withTaskDeadline(VALID_TASK_DEADLINE_2100)
                .withTaskStatus(VALID_TASK_STATUS_DONE).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(indexLastTask, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new TaskList(model.getTaskList()), new UserPrefs());
        expectedModel.setTask(lastTask, editedTask);

        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredTaskList_success() {
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_OBJECT, new EditTaskDescriptor());
        Task editedTask = model.getFilteredTaskList().get(INDEX_FIRST_OBJECT.getZeroBased());

        String expectedMessage = String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new TaskList(model.getTaskList()), new UserPrefs());

        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredTaskList_success() {
        showTaskAtIndex(model, INDEX_FIRST_OBJECT);

        Task taskInFilteredList = model.getFilteredTaskList().get(INDEX_FIRST_OBJECT.getZeroBased());
        Task editedTask = new TaskBuilder(taskInFilteredList).withTaskName(VALID_TASK_NAME_SORT_INVENTORY).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_OBJECT,
                new EditTaskDescriptorBuilder().withTaskName(VALID_TASK_NAME_SORT_INVENTORY).build());

        String expectedMessage = String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new TaskList(model.getTaskList()), new UserPrefs());
        expectedModel.setTask(model.getFilteredTaskList().get(0), editedTask);

        assertCommandSuccess(editTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateTaskUnfilteredTaskList_failure() {
        Task firstTask = model.getFilteredTaskList().get(INDEX_FIRST_OBJECT.getZeroBased());
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(firstTask).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_SECOND_OBJECT, descriptor);

        assertCommandFailure(editTaskCommand, model, MESSAGE_DUPLICATE_TASK);
    }

    @Test
    public void execute_duplicateTaskFilteredTaskList_failure() {
        showTaskAtIndex(model, INDEX_FIRST_OBJECT);

        // edit task in filtered task list into a duplicate in task list
        Task taskInList = model.getTaskList().getTaskList().get(INDEX_SECOND_OBJECT.getZeroBased());
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST_OBJECT,
                new EditTaskDescriptorBuilder(taskInList).build());

        assertCommandFailure(editTaskCommand, model, MESSAGE_DUPLICATE_TASK);
    }

    @Test
    public void execute_invalidTaskIndexUnfilteredTaskList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withTaskName(VALID_TASK_NAME_SORT_INVENTORY).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered task list where index is larger than size of filtered task list,
     * but smaller than size of task list
     */
    @Test
    public void execute_invalidTaskIndexFilteredTaskList_failure() {
        showTaskAtIndex(model, INDEX_FIRST_OBJECT);
        Index outOfBoundIndex = INDEX_SECOND_OBJECT;
        // ensures that outOfBoundIndex is still in bounds of task list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTaskList().getTaskList().size());

        EditTaskCommand editTaskCommand = new EditTaskCommand(outOfBoundIndex,
                new EditTaskDescriptorBuilder().withTaskName(VALID_TASK_NAME_SORT_INVENTORY).build());

        assertCommandFailure(editTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditTaskCommand standardTaskCommand =
                new EditTaskCommand(INDEX_FIRST_OBJECT, DESC_SORT_INVENTORY);

        // same values -> returns true
        EditTaskDescriptor copyTaskDescriptor = new EditTaskDescriptor(DESC_SORT_INVENTORY);
        EditTaskCommand commandWithSameValues =
                new EditTaskCommand(INDEX_FIRST_OBJECT, copyTaskDescriptor);
        assertTrue(standardTaskCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardTaskCommand.equals(standardTaskCommand));

        // null -> returns false
        assertFalse(standardTaskCommand.equals(null));

        // different types -> returns false
        assertFalse(standardTaskCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardTaskCommand.equals(
                new EditTaskCommand(INDEX_SECOND_OBJECT, DESC_SORT_INVENTORY)));

        // different descriptor -> returns false
        assertFalse(standardTaskCommand.equals(
                new EditTaskCommand(INDEX_FIRST_OBJECT, DESC_BUY_FLOUR)));
    }

}
