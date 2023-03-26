package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertAssignTaskCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertTaskCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertTaskCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.model.util.TypicalPersons.ALICE;
import static seedu.address.model.util.TypicalPersons.BENSON;
import static seedu.address.model.util.TypicalPersons.CARL;
import static seedu.address.model.util.TypicalPersons.DANIEL;
import static seedu.address.model.util.TypicalTasks.CHECK_BALANCES;
import static seedu.address.model.util.TypicalTasks.COMPLETE_SLIDES;
import static seedu.address.model.util.TypicalTasks.SEND_EMAIL_TO_CLIENT;
import static seedu.address.model.util.TypicalTasks.getTypicalTaskRepository;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.OfficeConnectModel;
import seedu.address.model.Repository;
import seedu.address.model.RepositoryModelManager;
import seedu.address.model.mapping.AssignTask;
import seedu.address.model.task.Task;

public class DeleteTaskCommandTest {

    private final OfficeConnectModel model = new OfficeConnectModel(
        new RepositoryModelManager<>(getTypicalTaskRepository()),
        new RepositoryModelManager<>(getPersonTaskRepository()));
    private final OfficeConnectModel expectedModel = new OfficeConnectModel(new
        RepositoryModelManager<>(model.getTaskModelManager().getReadOnlyRepository()),
        new RepositoryModelManager<>(model.getAssignTaskModelManager().getReadOnlyRepository()));

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task taskToDelete = model.getTaskModelManagerFilteredItemList().get(INDEX_FIRST.getZeroBased());
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_FIRST);
        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS, taskToDelete);
        expectedModel.deleteTaskModelManagerItem(taskToDelete);
        assertTaskCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(
                model.getTaskModelManagerFilteredItemList().size() + 1);
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(outOfBoundIndex);
        assertTaskCommandFailure(deleteTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTaskAtIndex(model, INDEX_FIRST);

        Task taskToDelete = model.getTaskModelManagerFilteredItemList().get(INDEX_FIRST.getZeroBased());
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS, taskToDelete);

        expectedModel.deleteTaskModelManagerItem(taskToDelete);
        showNoTask(expectedModel);

        assertTaskCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased()
                < model.getTaskModelManagerReadOnlyRepository().getData().size());

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(outOfBoundIndex);

        assertTaskCommandFailure(deleteTaskCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_checkDeletionOfAssignments_success() {
        Task taskToDelete = model.getTaskModelManagerFilteredItemList().get(INDEX_FIRST.getZeroBased());
        AssignTask assignmentToDelete1 = new AssignTask(ALICE.getId(), SEND_EMAIL_TO_CLIENT.getId());
        AssignTask assignmentToDelete2 = new AssignTask(DANIEL.getId(), SEND_EMAIL_TO_CLIENT.getId());

        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(INDEX_FIRST);
        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS, taskToDelete);

        expectedModel.deleteTaskModelManagerItem(taskToDelete);
        expectedModel.deleteAssignTaskModelManagerItem(assignmentToDelete1);
        expectedModel.deleteAssignTaskModelManagerItem(assignmentToDelete2);

        assertAssignTaskCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        DeleteTaskCommand deleteFirstCommand = new DeleteTaskCommand(INDEX_FIRST);
        DeleteTaskCommand deleteSecondCommand = new DeleteTaskCommand(INDEX_SECOND);

        // same object -> returns true
        assertEquals(deleteFirstCommand, deleteFirstCommand);

        // same values -> returns true
        DeleteTaskCommand deleteFirstCommandCopy = new DeleteTaskCommand(INDEX_FIRST);
        assertEquals(deleteFirstCommand, deleteFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, deleteFirstCommand);

        // null -> returns false
        assertNotEquals(null, deleteFirstCommand);

        // different task -> returns false
        assertNotEquals(deleteFirstCommand, deleteSecondCommand);
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoTask(OfficeConnectModel officeConnectModel) {
        officeConnectModel.updateTaskModelManagerFilteredItemList(x -> false);

        assertTrue(officeConnectModel.getTaskModelManagerFilteredItemList().isEmpty());
    }

    /**
     * Returns a {@code Repository} with a few AssignTask mappings for the TypicalTaskRepository and
     * TypicalAddressBook used in this class.
     */
    private Repository<AssignTask> getPersonTaskRepository() {
        AssignTask mapping1 = new AssignTask(ALICE.getId(), SEND_EMAIL_TO_CLIENT.getId());
        AssignTask mapping2 = new AssignTask(BENSON.getId(), COMPLETE_SLIDES.getId());
        AssignTask mapping3 = new AssignTask(CARL.getId(), CHECK_BALANCES.getId());
        AssignTask mapping4 = new AssignTask(DANIEL.getId(), SEND_EMAIL_TO_CLIENT.getId());

        Repository<AssignTask> ptl = new Repository<>();
        ptl.addItem(mapping1);
        ptl.addItem(mapping2);
        ptl.addItem(mapping3);
        ptl.addItem(mapping4);
        return ptl;
    }
}
