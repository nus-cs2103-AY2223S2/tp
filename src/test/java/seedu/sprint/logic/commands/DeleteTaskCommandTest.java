package seedu.sprint.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.assertCommandFailure;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.assertCommandSuccess;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.showApplicationAtIndex;
import static seedu.sprint.testutil.TypicalApplicationIndexes.INDEX_FIRST_APPLICATION;
import static seedu.sprint.testutil.TypicalApplicationIndexes.INDEX_SECOND_APPLICATION;
import static seedu.sprint.testutil.TypicalApplicationIndexes.INDEX_THIRD_APPLICATION;
import static seedu.sprint.testutil.TypicalApplications.getTypicalInternshipBook;

import org.junit.jupiter.api.Test;

import seedu.sprint.commons.core.Messages;
import seedu.sprint.commons.core.index.Index;
import seedu.sprint.logic.CommandHistory;
import seedu.sprint.model.InternshipBook;
import seedu.sprint.model.Model;
import seedu.sprint.model.ModelManager;
import seedu.sprint.model.UserPrefs;
import seedu.sprint.model.application.Application;
import seedu.sprint.testutil.ApplicationBuilder;

public class DeleteTaskCommandTest {
    private Model model = new ModelManager(getTypicalInternshipBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();


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

        Model expectedModel = new ModelManager(
                new InternshipBook(model.getInternshipBook()), new UserPrefs());
        expectedModel.setApplication(applicationToDeleteTask, editedApplication);
        expectedModel.commitInternshipBookChange();
        assertCommandSuccess(deleteTaskCommand, model, commandHistory, expectedMessage, expectedModel);
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

        Model expectedModel = new ModelManager(
                new InternshipBook(model.getInternshipBook()), new UserPrefs());
        expectedModel.setApplication(applicationToDeleteTask, editedApplication);
        expectedModel.commitInternshipBookChange();
        assertCommandSuccess(deleteTaskCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicationList().size() + 1);
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(outOfBoundIndex);

        assertCommandFailure(deleteTaskCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showApplicationAtIndex(model, INDEX_FIRST_APPLICATION);
        Index outOfBoundIndex = INDEX_SECOND_APPLICATION;

        assertTrue(outOfBoundIndex.getZeroBased() < model.getInternshipBook().getApplicationList().size());
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(outOfBoundIndex);
        assertCommandFailure(deleteTaskCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }


    @Test
    public void execute_taskDoesNotExist_throwsCommandException() {
        Index indexApplicationToRemoveTask = INDEX_THIRD_APPLICATION;
        DeleteTaskCommand deleteTaskCommand = new DeleteTaskCommand(indexApplicationToRemoveTask);
        String expectedMessage = DeleteTaskCommand.MESSAGE_TASK_DOES_NOT_EXIST;
        assertCommandFailure(deleteTaskCommand, model, commandHistory, expectedMessage);
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
        assertFalse(deleteTaskCommand.equals(new ListCommand()));

        // different index -> returns false
        assertFalse(deleteTaskCommand.equals(new DeleteTaskCommand(indexSecondLastApplication)));
    }
}
