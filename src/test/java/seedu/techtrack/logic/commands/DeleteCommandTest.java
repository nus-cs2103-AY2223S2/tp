package seedu.techtrack.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.techtrack.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.techtrack.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.techtrack.logic.commands.CommandTestUtil.showRoleAtIndex;
import static seedu.techtrack.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.techtrack.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.techtrack.testutil.TypicalRoles.getTypicalRoleBook;

import org.junit.jupiter.api.Test;

import seedu.techtrack.commons.core.Messages;
import seedu.techtrack.commons.core.index.Index;
import seedu.techtrack.model.Model;
import seedu.techtrack.model.ModelManager;
import seedu.techtrack.model.UserPrefs;
import seedu.techtrack.model.role.Role;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalRoleBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Role roleToDelete = model.getFilteredRoleList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, roleToDelete);

        ModelManager expectedModel = new ModelManager(model.getRoleBook(), new UserPrefs());
        expectedModel.deleteRole(roleToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredRoleList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showRoleAtIndex(model, INDEX_FIRST_PERSON);

        Role roleToDelete = model.getFilteredRoleList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, roleToDelete);

        Model expectedModel = new ModelManager(model.getRoleBook(), new UserPrefs());
        expectedModel.deleteRole(roleToDelete);
        showNoRole(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showRoleAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getRoleBook().getRoleList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_PERSON);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different role -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoRole(Model model) {
        model.updateFilteredRoleList(p -> false);

        assertTrue(model.getFilteredRoleList().isEmpty());
    }
}
