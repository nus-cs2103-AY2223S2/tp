package seedu.sprint.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.assertCommandFailure;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.assertCommandSuccess;
import static seedu.sprint.logic.commands.ApplicationCommandTestUtil.showApplicationAtIndex;
import static seedu.sprint.testutil.TypicalApplicationIndexes.INDEX_FIRST_APPLICATION;
import static seedu.sprint.testutil.TypicalApplicationIndexes.INDEX_SECOND_APPLICATION;
import static seedu.sprint.testutil.TypicalApplications.getTypicalInternshipBook;

import org.junit.jupiter.api.Test;

import seedu.sprint.commons.core.Messages;
import seedu.sprint.commons.core.index.Index;
import seedu.sprint.logic.CommandHistory;
import seedu.sprint.model.Model;
import seedu.sprint.model.ModelManager;
import seedu.sprint.model.UserPrefs;
import seedu.sprint.model.application.Application;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteApplicationCommand}.
 */
public class DeleteApplicationCommandTest {
    private Model model = new ModelManager(getTypicalInternshipBook(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();


    @Test
    public void execute_validIndexUnfilteredList_success() {
        Application applicationToDelete = model.getFilteredApplicationList()
                .get(INDEX_FIRST_APPLICATION.getZeroBased());
        DeleteApplicationCommand deleteCommand = new DeleteApplicationCommand(INDEX_FIRST_APPLICATION);

        String expectedMessage = String.format(DeleteApplicationCommand.MESSAGE_DELETE_APPLICATION_SUCCESS,
                applicationToDelete);

        ModelManager expectedModel = new ModelManager(model.getInternshipBook(), new UserPrefs());
        expectedModel.deleteApplication(applicationToDelete);
        expectedModel.commitInternshipBookChange();

        assertCommandSuccess(deleteCommand, model, commandHistory, expectedMessage, expectedModel);
    }


    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicationList().size() + 1);
        DeleteApplicationCommand deleteCommand = new DeleteApplicationCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }


    @Test
    public void execute_validIndexFilteredList_success() {
        showApplicationAtIndex(model, INDEX_FIRST_APPLICATION);

        Application applicationToDelete = model.getFilteredApplicationList()
                .get(INDEX_FIRST_APPLICATION.getZeroBased());
        DeleteApplicationCommand deleteCommand = new DeleteApplicationCommand(INDEX_FIRST_APPLICATION);

        String expectedMessage = String.format(DeleteApplicationCommand.MESSAGE_DELETE_APPLICATION_SUCCESS,
                applicationToDelete);

        Model expectedModel = new ModelManager(model.getInternshipBook(), new UserPrefs());
        expectedModel.deleteApplication(applicationToDelete);
        expectedModel.commitInternshipBookChange();
        showNoApplication(expectedModel);

        assertCommandSuccess(deleteCommand, model, commandHistory, expectedMessage, expectedModel);
    }


    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showApplicationAtIndex(model, INDEX_FIRST_APPLICATION);

        Index outOfBoundIndex = INDEX_SECOND_APPLICATION;
        // ensures that outOfBoundIndex is still in bounds of internship book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInternshipBook().getApplicationList().size());

        DeleteApplicationCommand deleteCommand = new DeleteApplicationCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, commandHistory,
                Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteApplicationCommand deleteFirstCommand = new DeleteApplicationCommand(INDEX_FIRST_APPLICATION);
        DeleteApplicationCommand deleteSecondCommand = new DeleteApplicationCommand(INDEX_SECOND_APPLICATION);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteApplicationCommand deleteFirstCommandCopy = new DeleteApplicationCommand(INDEX_FIRST_APPLICATION);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different application -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no application.
     */
    private void showNoApplication(Model model) {
        model.updateFilteredApplicationList(p -> false);

        assertTrue(model.getFilteredApplicationList().isEmpty());
    }
}
