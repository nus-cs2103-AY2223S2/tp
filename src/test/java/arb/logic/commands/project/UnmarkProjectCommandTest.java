package arb.logic.commands.project;

import static arb.logic.commands.CommandTestUtil.assertCommandFailure;
import static arb.logic.commands.CommandTestUtil.assertCommandSuccess;
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
import arb.testutil.ProjectBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code UnmarkProjectCommand}.
 */
class UnmarkProjectCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_validIndexUnfilteredList_success() {
        Project projectToUnmark = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        Project projectToUnmarkCopy = new ProjectBuilder(projectToUnmark).build();
        UnmarkProjectCommand unmarkProjectCommand = new UnmarkProjectCommand(INDEX_FIRST);
        String expectedMessage = String.format(UnmarkProjectCommand.MESSAGE_UNMARK_PROJECT_SUCCESS, projectToUnmark);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        projectToUnmarkCopy.markAsUndone();
        expectedModel.setProject(projectToUnmark, projectToUnmarkCopy);
        assertCommandSuccess(unmarkProjectCommand, ListType.PROJECT, ListType.PROJECT, model, expectedMessage,
                expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProjectList().size() + 1);
        UnmarkProjectCommand unmarkProjectCommand = new UnmarkProjectCommand(outOfBoundIndex);
        assertCommandFailure(unmarkProjectCommand, ListType.PROJECT, model,
                Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_currentListShownClient_failure() {
        Index validIndex = INDEX_FIRST;
        assertCommandFailure(new UnmarkProjectCommand(validIndex), ListType.CLIENT,
                model, Messages.MESSAGE_INVALID_LIST_PROJECT);
    }

    @Test
    public void equals() {
        UnmarkProjectCommand unmarkFirstCommand = new UnmarkProjectCommand(INDEX_FIRST);
        UnmarkProjectCommand unmarkSecondCommand = new UnmarkProjectCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(unmarkFirstCommand.equals(unmarkFirstCommand));

        // same values -> returns true
        UnmarkProjectCommand unmarkFirstCommandCopy = new UnmarkProjectCommand(INDEX_FIRST);
        assertTrue(unmarkFirstCommand.equals(unmarkFirstCommandCopy));

        // different types -> returns false
        assertFalse(unmarkFirstCommand.equals(1));

        // null -> returns false
        assertFalse(unmarkFirstCommand.equals(null));

        // different project -> returns false
        assertFalse(unmarkFirstCommand.equals(unmarkSecondCommand));
    }
}

