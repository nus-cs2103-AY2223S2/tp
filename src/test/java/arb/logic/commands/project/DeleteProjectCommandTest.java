package arb.logic.commands.project;

import static arb.logic.commands.CommandTestUtil.assertCommandFailure;
import static arb.logic.commands.CommandTestUtil.assertCommandSuccess;
import static arb.logic.commands.CommandTestUtil.showProjectAtIndex;
import static arb.testutil.TypicalAddressBook.getTypicalAddressBook;
import static arb.testutil.TypicalIndexes.INDEX_FIRST;
import static arb.testutil.TypicalIndexes.INDEX_SECOND;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import arb.commons.core.Messages;
import arb.commons.core.index.Index;
import arb.model.ListType;
import arb.model.Model;
import arb.model.ModelManager;
import arb.model.UserPrefs;
import arb.model.project.Project;

/**
 * Contains integration and unit tests for {@code DeleteProjectCommand}.
 */
public class DeleteProjectCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_sucess() {
        Project projectToDelete = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        DeleteProjectCommand deleteProjectCommand = new DeleteProjectCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteProjectCommand.MESSAGE_DELETE_PROJECT_SUCCESS, projectToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteProject(projectToDelete);

        assertCommandSuccess(deleteProjectCommand, ListType.PROJECT, ListType.PROJECT,
                model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundsIndex = Index.fromOneBased(model.getFilteredProjectList().size() + 1);
        DeleteProjectCommand deleteProjectCommand = new DeleteProjectCommand(outOfBoundsIndex);

        assertCommandFailure(deleteProjectCommand, ListType.PROJECT, model,
                Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showProjectAtIndex(model, INDEX_FIRST);

        Project projectToDelete = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        DeleteProjectCommand deleteProjectCommand = new DeleteProjectCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteProjectCommand.MESSAGE_DELETE_PROJECT_SUCCESS, projectToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteProject(projectToDelete);
        showNoProject(expectedModel);

        assertCommandSuccess(deleteProjectCommand, ListType.PROJECT, ListType.PROJECT, model, expectedMessage,
                expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showProjectAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getProjectList().size());

        DeleteProjectCommand deleteProjectCommand = new DeleteProjectCommand(outOfBoundIndex);

        assertCommandFailure(deleteProjectCommand, ListType.PROJECT, model,
                Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_currentListShownClient_failure() {
        Index validIndex = INDEX_FIRST;
        assertCommandFailure(new DeleteProjectCommand(validIndex),
                        ListType.CLIENT, model, Messages.MESSAGE_INVALID_LIST_PROJECT);
    }

    @Test
    public void equals() {
        DeleteProjectCommand deleteFirstCommand = new DeleteProjectCommand(INDEX_FIRST);
        DeleteProjectCommand deleteSecondCommand = new DeleteProjectCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteProjectCommand deleteFirstCommandCopy = new DeleteProjectCommand(INDEX_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different project -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no project.
     */
    private void showNoProject(Model model) {
        model.updateFilteredProjectList(p -> false);

        assertTrue(model.getFilteredProjectList().isEmpty());
    }

}
