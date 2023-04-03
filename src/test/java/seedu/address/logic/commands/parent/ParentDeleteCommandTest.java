package seedu.address.logic.commands.parent;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.assertParentCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showParentAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalParents.ALICE;
import static seedu.address.testutil.TypicalParents.BENSON;
import static seedu.address.testutil.TypicalParents.getTypicalPcParents;
import static seedu.address.testutil.TypicalParents.HOON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.parent.Parent;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class ParentDeleteCommandTest {

    private Model model = new ModelManager(getTypicalPcParents(), new UserPrefs());

    @Test
    public void execute_validNameAndNumberUnfilteredList_success() {
        Parent parentToDelete = model.getFilteredParentList().get(INDEX_FIRST_PERSON.getZeroBased());
        ParentDeleteCommand deleteCommand = new ParentDeleteCommand(ALICE.getName(), ALICE.getPhone());

        String expectedMessage = String.format(ParentDeleteCommand.MESSAGE_DELETE_PARENT_SUCCESS, parentToDelete);

        ModelManager expectedModel = new ModelManager(model.getParents(), new UserPrefs());
        expectedModel.deleteParent(parentToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidNameUnfilteredList_throwsCommandException() {
        ParentDeleteCommand deleteCommand = new ParentDeleteCommand(HOON.getName(), ALICE.getPhone());
        assertParentCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PARENT);
    }

    @Test
    public void execute_invalidPhoneNumberUnfilteredList_throwsCommandException() {
        ParentDeleteCommand deleteCommand = new ParentDeleteCommand(ALICE.getName(), HOON.getPhone());
        assertParentCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PARENT);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showParentAtIndex(model, INDEX_FIRST_PERSON);

        Parent parentToDelete = model.getFilteredParentList().get(INDEX_FIRST_PERSON.getZeroBased());
        ParentDeleteCommand deleteCommand = new ParentDeleteCommand(ALICE.getName(), ALICE.getPhone());

        String expectedMessage = String.format(ParentDeleteCommand.MESSAGE_DELETE_PARENT_SUCCESS, parentToDelete);

        Model expectedModel = new ModelManager(model.getParents(), new UserPrefs());
        expectedModel.deleteParent(parentToDelete);
        showNoParent(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        ParentDeleteCommand deleteFirstCommand = new ParentDeleteCommand(ALICE.getName(), ALICE.getPhone());
        ParentDeleteCommand deleteSecondCommand = new ParentDeleteCommand(BENSON.getName(), BENSON.getPhone());

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        ParentDeleteCommand deleteFirstCommandCopy = new ParentDeleteCommand(ALICE.getName(), ALICE.getPhone());
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoParent(Model model) {
        model.updateFilteredParentList(p -> false);
        assertTrue(model.getFilteredParentList().isEmpty());
    }
}