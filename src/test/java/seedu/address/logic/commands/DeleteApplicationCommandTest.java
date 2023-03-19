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
import seedu.address.model.UserPrefs;
import seedu.address.model.application.Application;

/**
 * Contains integration tests (interaction with the ApplicationModel) and unit tests for
 * {@code DeleteApplicationCommand}.
 */
public class DeleteApplicationCommandTest {
    private ApplicationModel model = new ApplicationModelManager(getTypicalInternshipBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Application applicationToDelete = model.getFilteredApplicationList()
                .get(INDEX_FIRST_APPLICATION.getZeroBased());
        DeleteApplicationCommand deleteCommand = new DeleteApplicationCommand(INDEX_FIRST_APPLICATION);

        String expectedMessage = String.format(DeleteApplicationCommand.MESSAGE_DELETE_APPLICATION_SUCCESS,
                applicationToDelete);

        ApplicationModelManager expectedModel = new ApplicationModelManager(model.getInternshipBook(), new UserPrefs());
        expectedModel.deleteApplication(applicationToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicationList().size() + 1);
        DeleteApplicationCommand deleteCommand = new DeleteApplicationCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showApplicationAtIndex(model, INDEX_FIRST_APPLICATION);

        Application applicationToDelete = model.getFilteredApplicationList()
                .get(INDEX_FIRST_APPLICATION.getZeroBased());
        DeleteApplicationCommand deleteCommand = new DeleteApplicationCommand(INDEX_FIRST_APPLICATION);

        String expectedMessage = String.format(DeleteApplicationCommand.MESSAGE_DELETE_APPLICATION_SUCCESS,
                applicationToDelete);

        ApplicationModel expectedModel = new ApplicationModelManager(model.getInternshipBook(), new UserPrefs());
        expectedModel.deleteApplication(applicationToDelete);
        showNoApplication(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showApplicationAtIndex(model, INDEX_FIRST_APPLICATION);

        Index outOfBoundIndex = INDEX_SECOND_APPLICATION;
        // ensures that outOfBoundIndex is still in bounds of internship book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInternshipBook().getApplicationList().size());

        DeleteApplicationCommand deleteCommand = new DeleteApplicationCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_APPLICATION_DISPLAYED_INDEX);
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
    private void showNoApplication(ApplicationModel model) {
        model.updateFilteredApplicationList(p -> false);

        assertTrue(model.getFilteredApplicationList().isEmpty());
    }
}
