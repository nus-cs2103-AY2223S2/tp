package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.showApplicationAtIndex;
import static seedu.address.testutil.TypicalApplicationIndexes.INDEX_FIRST_APPLICATION;
import static seedu.address.testutil.TypicalApplicationIndexes.INDEX_SECOND_APPLICATION;
import static seedu.address.testutil.TypicalApplications.getTypicalInternshipBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.ApplicationModel;
import seedu.address.model.ApplicationModelManager;
import seedu.address.model.InternshipBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.application.Application;
import seedu.address.testutil.ApplicationBuilder;

public class DeleteTaskCommandTest {
    private ApplicationModel model = new ApplicationModelManager(getTypicalInternshipBook(), new UserPrefs());

    @Test
    public void execute_validIndexTaskExistsUnfilteredList_success() {
        Index indexLastApplication = Index.fromOneBased(model.getFilteredApplicationList().size());
        Application applicationToDeleteTask = model.getFilteredApplicationList()
                .get(indexLastApplication.getZeroBased());
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(indexLastApplication);

        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS,
                applicationToDeleteTask.getTask());

        ApplicationBuilder applicationInList = new ApplicationBuilder(applicationToDeleteTask);
        Application editedApplication = applicationInList.withoutTask().build();

        ApplicationModel expectedModel = new ApplicationModelManager(
                new InternshipBook(model.getInternshipBook()), new UserPrefs());
        expectedModel.setApplication(applicationToDeleteTask, editedApplication);
        assertCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexTaskExistsFilteredList_success() {
        Index indexLastApplication = Index.fromOneBased(model.getFilteredApplicationList().size());
        showApplicationAtIndex(model, indexLastApplication);

        Application applicationToDeleteTask = model.getFilteredApplicationList().get(0);
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(Index.fromZeroBased(0));

        String expectedMessage = String.format(DeleteTaskCommand.MESSAGE_DELETE_TASK_SUCCESS,
                applicationToDeleteTask.getTask());

        ApplicationBuilder applicationInList = new ApplicationBuilder(applicationToDeleteTask);
        Application editedApplication = applicationInList.withoutTask().build();

        ApplicationModel expectedModel = new ApplicationModelManager(
                new InternshipBook(model.getInternshipBook()), new UserPrefs());
        expectedModel.setApplication(applicationToDeleteTask, editedApplication);
        assertCommandSuccess(deleteTaskCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicationList().size() + 1);
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(outOfBoundIndex);

        assertCommandFailure(deleteTaskCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showApplicationAtIndex(model, INDEX_FIRST_APPLICATION);
        Index outOfBoundIndex = INDEX_SECOND_APPLICATION;

        assertTrue(outOfBoundIndex.getZeroBased() < model.getInternshipBook().getApplicationList().size());
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(outOfBoundIndex);
        assertCommandFailure(deleteTaskCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_taskDoesNotExist_throwsCommandException() {
        Index indexApplicationToRemoveTask = INDEX_FIRST_APPLICATION;
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(indexApplicationToRemoveTask);
        String expectedMessage = DeleteTaskCommand.MESSAGE_TASK_DOES_NOT_EXIST;
        assertCommandFailure(deleteTaskCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        Index indexLastApplication = Index.fromOneBased(model.getFilteredApplicationList().size());
        Index indexSecondLastApplication = Index.fromOneBased(model.getFilteredApplicationList().size() - 1);
        final DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(indexLastApplication);
        DeleteTaskCommand commandWithSameValues = new DeleteTaskCommand(indexLastApplication);

        // same object -> returns true
        assertTrue(deleteTaskCommand.equals(commandWithSameValues));

        // null -> returns false
        assertFalse(deleteTaskCommand.equals(null));

        // different types -> returns false
        assertFalse(deleteTaskCommand.equals(new ListApplicationCommand()));

        // different index -> returns false
        assertFalse(deleteTaskCommand.equals(new DeleteTaskCommand(indexSecondLastApplication)));
    }
}
