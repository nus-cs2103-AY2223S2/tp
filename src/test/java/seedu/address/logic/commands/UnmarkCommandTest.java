package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertTaskCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertTaskCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_SEVENTH;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskRepository;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.OfficeConnectModel;
import seedu.address.model.Repository;
import seedu.address.model.RepositoryModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.mapping.PersonTask;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

public class UnmarkCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private OfficeConnectModel officeConnectModel = new OfficeConnectModel(
            new RepositoryModelManager<>(getTypicalTaskRepository()),
            new RepositoryModelManager<>(new Repository<PersonTask>()));
    private OfficeConnectModel expectedOfficeConnectModel = new OfficeConnectModel(new
            RepositoryModelManager<>(officeConnectModel.getTaskModelManager().getReadOnlyRepository()),
            new RepositoryModelManager<>(new Repository<PersonTask>()));

    @Test
    public void execute_validIndexList_success() {
        Task task = officeConnectModel.getTaskModelManager().getFilteredItemList().get(INDEX_SEVENTH.getZeroBased());
        UnmarkCommand command = new UnmarkCommand(INDEX_SEVENTH);

        Task unmarkedTask = new TaskBuilder(task).withStatus(false).build();
        String expectedMessage = String.format(UnmarkCommand.MESSAGE_UNMARK_TASK_SUCCESS, unmarkedTask);

        expectedOfficeConnectModel.getTaskModelManager().setItem(task, unmarkedTask);

        assertTaskCommandSuccess(command, officeConnectModel, expectedMessage, expectedOfficeConnectModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBounds = Index.fromOneBased(officeConnectModel.getTaskModelManager()
                .getFilteredItemList().size() + 1);
        UnmarkCommand command = new UnmarkCommand(outOfBounds);
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX, () ->
                command.execute(model, officeConnectModel));
        assertTaskCommandFailure(command, officeConnectModel, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_alreadyUnmarkedTask_throwsCommandException() {
        Index unmarkedTaskIndex = INDEX_FIRST;
        UnmarkCommand command = new UnmarkCommand(unmarkedTaskIndex);
        assertThrows(CommandException.class, Messages.MESSAGE_TASK_ALREADY_NOT_DONE, () ->
                command.execute(model, officeConnectModel));
        assertTaskCommandFailure(command, officeConnectModel, Messages.MESSAGE_TASK_ALREADY_NOT_DONE);
    }

    @Test
    public void equals() {
        UnmarkCommand unmarkFirstCommand = new UnmarkCommand(INDEX_FIRST);
        UnmarkCommand unmarkSecondCommand = new UnmarkCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(unmarkFirstCommand.equals(unmarkFirstCommand));

        // same values -> returns true
        UnmarkCommand unmarkFirstCommandCopy = new UnmarkCommand(INDEX_FIRST);
        assertTrue(unmarkFirstCommand.equals(unmarkFirstCommandCopy));

        // different types -> returns false
        assertFalse(unmarkFirstCommand.equals(1));

        // null -> returns false
        assertFalse(unmarkFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(unmarkFirstCommand.equals(unmarkSecondCommand));
    }
}
