package seedu.socket.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.socket.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.socket.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.socket.logic.commands.CommandTestUtil.showProjectAtIndex;
import static seedu.socket.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.socket.testutil.TypicalIndexes.INDEX_SECOND_PROJECT;
import static seedu.socket.testutil.TypicalProjects.getTypicalSocket;

import org.junit.jupiter.api.Test;

import seedu.socket.commons.core.Messages;
import seedu.socket.commons.core.index.Index;
import seedu.socket.model.Model;
import seedu.socket.model.ModelManager;
import seedu.socket.model.UserPrefs;
import seedu.socket.model.project.Project;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteProjectCommand}.
 */
public class DeleteProjectCommandTest {

    private Model model = new ModelManager(getTypicalSocket(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Project projectToDelete = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        DeleteProjectCommand deleteProjectCommand = new DeleteProjectCommand(INDEX_FIRST_PROJECT);

        String expectedMessage = String.format(DeleteProjectCommand.MESSAGE_DELETE_PROJECT_SUCCESS, projectToDelete);

        ModelManager expectedModel = new ModelManager(model.getSocket(), new UserPrefs());
        expectedModel.deleteProject(projectToDelete);

        assertCommandSuccess(deleteProjectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProjectList().size() + 1);
        DeleteProjectCommand deleteProjectCommand = new DeleteProjectCommand(outOfBoundIndex);

        assertCommandFailure(deleteProjectCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showProjectAtIndex(model, INDEX_FIRST_PROJECT);

        Project projectToDelete = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        DeleteProjectCommand deleteProjectCommand = new DeleteProjectCommand(INDEX_FIRST_PROJECT);

        String expectedMessage = String.format(DeleteProjectCommand.MESSAGE_DELETE_PROJECT_SUCCESS, projectToDelete);

        Model expectedModel = new ModelManager(model.getSocket(), new UserPrefs());
        expectedModel.deleteProject(projectToDelete);
        showNoProject(expectedModel);

        assertCommandSuccess(deleteProjectCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showProjectAtIndex(model, INDEX_FIRST_PROJECT);

        Index outOfBoundIndex = INDEX_SECOND_PROJECT;
        // ensures that outOfBoundIndex is still in bounds of socket list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getSocket().getProjectList().size());

        DeleteProjectCommand deleteProjectCommand = new DeleteProjectCommand(outOfBoundIndex);

        assertCommandFailure(deleteProjectCommand, model, Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteProjectCommand deleteProjectFirstCommand = new DeleteProjectCommand(INDEX_FIRST_PROJECT);
        DeleteProjectCommand deleteProjectSecondCommand = new DeleteProjectCommand(INDEX_SECOND_PROJECT);

        // same object -> returns true
        assertTrue(deleteProjectFirstCommand.equals(deleteProjectFirstCommand));

        // same values -> returns true
        DeleteProjectCommand deleteProjectFirstCommandCopy = new DeleteProjectCommand(INDEX_FIRST_PROJECT);
        assertTrue(deleteProjectFirstCommand.equals(deleteProjectFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteProjectFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteProjectSecondCommand.equals(null));

        // different project -> returns false
        assertFalse(deleteProjectFirstCommand.equals(deleteProjectSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoProject(Model model) {
        model.updateFilteredProjectList(p -> false);

        assertTrue(model.getFilteredProjectList().isEmpty());
    }
}
