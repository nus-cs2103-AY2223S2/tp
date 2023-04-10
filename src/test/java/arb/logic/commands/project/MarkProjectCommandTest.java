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
 * {@code MarkProjectCommand}.
 */
class MarkProjectCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_validIndexUnfilteredList_success() {
        Project projectToMark = model.getFilteredProjectList().get(INDEX_FIRST.getZeroBased());
        Project projectToMarkCopy = new ProjectBuilder(projectToMark).build();
        MarkProjectCommand markProjectCommand = new MarkProjectCommand(INDEX_FIRST);
        String expectedMessage = String.format(MarkProjectCommand.MESSAGE_MARK_PROJECT_SUCCESS, projectToMark);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        projectToMarkCopy.markAsDone();
        expectedModel.setProject(projectToMark, projectToMarkCopy);
        assertCommandSuccess(markProjectCommand, ListType.PROJECT, ListType.PROJECT, model, expectedMessage,
                expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProjectList().size() + 1);
        MarkProjectCommand markProjectCommand = new MarkProjectCommand(outOfBoundIndex);
        assertCommandFailure(markProjectCommand, ListType.PROJECT, model,
                Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_currentListShownClient_failure() {
        Index validIndex = INDEX_FIRST;
        assertCommandFailure(new MarkProjectCommand(validIndex), ListType.CLIENT,
                model, Messages.MESSAGE_INVALID_LIST_PROJECT);
    }

    @Test
    public void equals() {
        MarkProjectCommand markFirstCommand = new MarkProjectCommand(INDEX_FIRST);
        MarkProjectCommand markSecondCommand = new MarkProjectCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(markFirstCommand.equals(markFirstCommand));

        // same values -> returns true
        MarkProjectCommand markFirstCommandCopy = new MarkProjectCommand(INDEX_FIRST);
        assertTrue(markFirstCommand.equals(markFirstCommandCopy));

        // different types -> returns false
        assertFalse(markFirstCommand.equals(1));

        // null -> returns false
        assertFalse(markFirstCommand.equals(null));

        // different project -> returns false
        assertFalse(markFirstCommand.equals(markSecondCommand));
    }
}

