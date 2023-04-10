package seedu.task.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.task.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.task.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.task.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.task.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.task.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.task.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.task.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.task.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.task.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.task.testutil.TypicalDailyPlans.getTypicalPlanner;
import static seedu.task.testutil.TypicalDeadlines.getTypicalDeadlineBook;
import static seedu.task.testutil.TypicalEvents.getTypicalEventBook;
import static seedu.task.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.task.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.task.testutil.TypicalTasks.getTypicalTaskBook;

import org.junit.jupiter.api.Test;

import seedu.task.commons.core.Messages;
import seedu.task.commons.core.index.Index;
import seedu.task.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.task.logic.commands.exceptions.CommandException;
import seedu.task.model.Model;
import seedu.task.model.ModelManager;
import seedu.task.model.TaskBook;
import seedu.task.model.UserPrefs;
import seedu.task.model.task.Task;
import seedu.task.testutil.DeadlineBuilder;
import seedu.task.testutil.EditTaskDescriptorBuilder;
import seedu.task.testutil.EventBuilder;
import seedu.task.testutil.SimpleTaskBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalTaskBook(), new UserPrefs(), getTypicalPlanner());
    private Model eventModel = new ModelManager(getTypicalEventBook(), new UserPrefs(), getTypicalPlanner());

    private Model deadlineModel = new ModelManager(getTypicalDeadlineBook(), new UserPrefs(), getTypicalPlanner());
    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() throws CommandException {
        Task editedTask = new SimpleTaskBuilder().build();
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(editedTask).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_TASK, descriptor);
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new TaskBook(model.getTaskBook()), new UserPrefs(), model.getPlanner());
        expectedModel.setTask(model.getFilteredTaskList().get(0), editedTask);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);

        Task editedEvent = new EventBuilder().build();
        EditTaskDescriptor descriptor1 = new EditTaskDescriptorBuilder(editedEvent).build();
        EditCommand editCommand1 = new EditCommand(INDEX_FIRST_TASK, descriptor1);
        expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TASK_SUCCESS, editedEvent);
        expectedModel = new ModelManager(new TaskBook(eventModel.getTaskBook()),
            new UserPrefs(), eventModel.getPlanner());
        expectedModel.setTask(eventModel.getFilteredTaskList().get(0), editedEvent);

        assertCommandSuccess(editCommand1, eventModel, expectedMessage, expectedModel);

        Task editedDeadline = new DeadlineBuilder().build();
        EditTaskDescriptor descriptor2 = new EditTaskDescriptorBuilder(editedDeadline).build();
        EditCommand editCommand2 = new EditCommand(INDEX_FIRST_TASK, descriptor2);
        expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TASK_SUCCESS, editedDeadline);
        expectedModel = new ModelManager(new TaskBook(deadlineModel.getTaskBook()),
            new UserPrefs(), deadlineModel.getPlanner());
        expectedModel.setTask(deadlineModel.getFilteredTaskList().get(0), editedDeadline);

        assertCommandSuccess(editCommand2, deadlineModel, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastTask = Index.fromOneBased(model.getFilteredTaskList().size());
        Task lastTask = model.getFilteredTaskList().get(indexLastTask.getZeroBased());

        SimpleTaskBuilder taskInList = new SimpleTaskBuilder(lastTask);
        Task editedTask = taskInList.withName(VALID_NAME_BOB).withDescription(VALID_DESCRIPTION_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withName(VALID_NAME_BOB)
                .withDescription(VALID_DESCRIPTION_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastTask, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new TaskBook(model.getTaskBook()), new UserPrefs(), model.getPlanner());
        expectedModel.setTask(lastTask, editedTask);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_TASK, new EditTaskDescriptor());
        Task editedTask = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new TaskBook(model.getTaskBook()), new UserPrefs(), model.getPlanner());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task taskInFilteredList = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task editedTask = new SimpleTaskBuilder(taskInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_TASK,
                new EditTaskDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new TaskBook(model.getTaskBook()), new UserPrefs(), model.getPlanner());
        expectedModel.setTask(model.getFilteredTaskList().get(0), editedTask);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_deadlineOnSimpleTask_failure() {
        Task editedDeadline = new DeadlineBuilder().build();
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(editedDeadline).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_TASK, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DEADLINE_ON_TASK);
    }

    @Test
    public void execute_deadlineOnEvent_failure() {
        Task editedDeadline = new DeadlineBuilder().build();
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(editedDeadline).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_TASK, descriptor);

        assertCommandFailure(editCommand, eventModel, EditCommand.MESSAGE_DEADLINE_ON_EVENT);
    }

    @Test
    public void execute_fromOnSimpleTask_failure() {
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withFrom("2023-01-01 1800").build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_TASK, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_FROM_ON_TASK);
    }

    @Test
    public void execute_fromOnDeadline_failure() {
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withFrom("2023-01-01 1800").build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_TASK, descriptor);

        assertCommandFailure(editCommand, deadlineModel, EditCommand.MESSAGE_FROM_ON_DEADLINE);
    }

    @Test
    public void execute_toOnSimpleTask_failure() {
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTo("2023-01-03 1800").build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_TASK, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_TO_ON_TASK);
    }

    @Test
    public void execute_toOnDeadline_failure() {
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTo("2023-01-03 1800").build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_TASK, descriptor);

        assertCommandFailure(editCommand, deadlineModel, EditCommand.MESSAGE_TO_ON_DEADLINE);
    }




    @Test
    public void execute_duplicateTaskUnfilteredList_failure() {
        Task firstTask = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(firstTask).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_TASK, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_TASK);
    }

    @Test
    public void execute_duplicateTaskFilteredList_failure() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        // edit task in filtered list into a duplicate in task book
        Task taskInList = model.getTaskBook().getTaskList().get(INDEX_SECOND_TASK.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_TASK,
                new EditTaskDescriptorBuilder(taskInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_TASK);
    }

    @Test
    public void execute_invalidTaskIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditCommand.MESSAGE_USAGE));
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of task book
     */
    @Test
    public void execute_invalidTaskIndexFilteredList_failure() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of task book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTaskBook().getTaskList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditTaskDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
    }

    @Test
    public void execute_invalidStartEndEventTime() {
        showTaskAtIndex(eventModel, INDEX_FIRST_TASK);
        EditCommand editCommand = new EditCommand(INDEX_FIRST_TASK,
            new EditTaskDescriptorBuilder().withFrom("2023-01-03 1800").build());
        assertCommandFailure(editCommand, eventModel, Messages.MESSAGE_INVALID_EVENT_DATES);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_TASK, DESC_AMY);

        // same values -> returns true
        EditTaskDescriptor copyDescriptor = new EditTaskDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_TASK, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_TASK, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_TASK, DESC_BOB)));
    }

}
