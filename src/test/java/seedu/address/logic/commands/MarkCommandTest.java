package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertTaskCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertTaskCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
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

public class MarkCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private OfficeConnectModel officeConnectModel = new OfficeConnectModel(
            new RepositoryModelManager<>(getTypicalTaskRepository()),
            new RepositoryModelManager<>(new Repository<PersonTask>()));
    private OfficeConnectModel expectedOfficeConnectModel = new OfficeConnectModel(new
            RepositoryModelManager<>(officeConnectModel.getTaskModelManager().getReadOnlyRepository()),
            new RepositoryModelManager<>(new Repository<PersonTask>()));

    @Test
    public void execute_validIndexList_success() {
        Task task = officeConnectModel.getTaskModelManager().getFilteredItemList().get(INDEX_FIRST.getZeroBased());
        MarkCommand command = new MarkCommand(INDEX_FIRST);

        Task markedTask = new TaskBuilder(task).withStatus(true).build();
        String expectedMessage = String.format(MarkCommand.MESSAGE_MARK_TASK_SUCCESS, markedTask);

        expectedOfficeConnectModel.getTaskModelManager().setItem(task, markedTask);

        assertTaskCommandSuccess(command, officeConnectModel, expectedMessage, expectedOfficeConnectModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBounds = Index.fromOneBased(officeConnectModel.getTaskModelManager()
                .getFilteredItemList().size() + 1);
        MarkCommand command = new MarkCommand(outOfBounds);
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX, () ->
                command.execute(model, officeConnectModel));
        assertTaskCommandFailure(command, officeConnectModel, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_alreadyMarkedTask_throwsCommandException() {
        Index markedTaskIndex = Index.fromOneBased(officeConnectModel.getTaskModelManager()
                .getFilteredItemList().size());
        MarkCommand command = new MarkCommand(markedTaskIndex);
        assertThrows(CommandException.class, Messages.MESSAGE_TASK_ALREADY_DONE, () ->
                command.execute(model, officeConnectModel));
        assertTaskCommandFailure(command, officeConnectModel, Messages.MESSAGE_TASK_ALREADY_DONE);
    }

    @Test
    public void equals() {
        MarkCommand markFirstCommand = new MarkCommand(INDEX_FIRST);
        MarkCommand markSecondCommand = new MarkCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(markFirstCommand.equals(markFirstCommand));

        // same values -> returns true
        MarkCommand markFirstCommandCopy = new MarkCommand(INDEX_FIRST);
        assertTrue(markFirstCommand.equals(markFirstCommandCopy));

        // different types -> returns false
        assertFalse(markFirstCommand.equals(1));

        // null -> returns false
        assertFalse(markFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(markFirstCommand.equals(markSecondCommand));
    }
}
