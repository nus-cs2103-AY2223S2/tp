package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SEND_EMAIL;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SUBMIT_REPORT;
import static seedu.address.logic.commands.CommandTestUtil.assertTaskCommandSuccess;
import static seedu.address.model.util.TypicalTasks.VALID_CONTENT_SEND_EMAIL;
import static seedu.address.model.util.TypicalTasks.VALID_STATUS_DONE;
import static seedu.address.model.util.TypicalTasks.VALID_TITLE_SEND_EMAIL;
import static seedu.address.model.util.TypicalTasks.getTypicalTaskRepository;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.address.model.OfficeConnectModel;
import seedu.address.model.Repository;
import seedu.address.model.RepositoryModelManager;
import seedu.address.model.mapping.AssignTask;
import seedu.address.model.task.Task;
import seedu.address.model.util.TaskBuilder;
import seedu.address.testutil.EditTaskDescriptorBuilder;


public class EditTaskCommandTest {
    private static OfficeConnectModel officeConnectModel = new OfficeConnectModel(
            new RepositoryModelManager<>(getTypicalTaskRepository()),
            new RepositoryModelManager<>(new Repository<AssignTask>()));

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Task editedTask = new TaskBuilder().build();
        EditTaskCommand.EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(editedTask).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        OfficeConnectModel expectedOfficeConnectModel = new OfficeConnectModel(
                new RepositoryModelManager<>(getTypicalTaskRepository()),
                new RepositoryModelManager<>(new Repository<AssignTask>()));

        expectedOfficeConnectModel.getTaskModelManager()
                .setItem(officeConnectModel.getTaskModelManagerFilteredItemList().get(0), editedTask);

        assertTaskCommandSuccess(editTaskCommand, officeConnectModel, expectedMessage, officeConnectModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastTask = Index.fromOneBased(officeConnectModel.getTaskModelManagerFilteredItemList().size());
        Task lastTask = officeConnectModel.getTaskModelManagerFilteredItemList().get(indexLastTask.getZeroBased());
        System.out.println(lastTask);
        System.out.println(lastTask.getId());
        TaskBuilder taskInList = new TaskBuilder(lastTask);
        Task editedTask = taskInList.withTitle(VALID_TITLE_SEND_EMAIL)
                .withContent(VALID_CONTENT_SEND_EMAIL)
                .withStatus(VALID_STATUS_DONE).withId(lastTask.getId()).build();
        System.out.println(editedTask);
        System.out.println(editedTask.getId());

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withTitle(VALID_TITLE_SEND_EMAIL)
                .withContent(VALID_CONTENT_SEND_EMAIL)
                .withStatus(VALID_STATUS_DONE).withId(lastTask.getId()).build();
        EditTaskCommand editTaskCommand = new EditTaskCommand(indexLastTask, descriptor);

        String expectedMessage = String.format(EditTaskCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);
        OfficeConnectModel expectedOfficeConnectModel = new OfficeConnectModel(
                new RepositoryModelManager<>(getTypicalTaskRepository()),
                new RepositoryModelManager<>(new Repository<AssignTask>()));

        expectedOfficeConnectModel.getTaskModelManager().setItem(lastTask, editedTask);

        assertTaskCommandSuccess(editTaskCommand, officeConnectModel, expectedMessage, officeConnectModel);
    }

    @Test
    public void equals() {
        final EditTaskCommand standardCommand = new EditTaskCommand(INDEX_FIRST, DESC_SEND_EMAIL);

        // same values -> returns true
        EditTaskCommand.EditTaskDescriptor copyDescriptor = new EditTaskCommand.EditTaskDescriptor(DESC_SEND_EMAIL);
        EditTaskCommand commandWithSameValues = new EditTaskCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditTaskCommand(INDEX_SECOND, DESC_SEND_EMAIL)));

        // different task -> returns false
        assertFalse(standardCommand.equals(new EditTaskCommand(INDEX_FIRST, DESC_SUBMIT_REPORT)));
    }
}
