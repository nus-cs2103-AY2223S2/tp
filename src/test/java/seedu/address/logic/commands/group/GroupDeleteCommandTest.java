package seedu.address.logic.commands.group;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showGroupAtIndex;
import static seedu.address.testutil.TypicalGroups.getTypicalAddressBookWithEmptyGroups;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_GENERIC_ITEM;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_GENERIC_ITEM;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.Group;

/**
 * Contains integration tests (interaction with the Model) and unit tests for GroupDeleteCommand.
 */
class GroupDeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBookWithEmptyGroups(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Group groupToDelete = model.getFilteredGroupList().get(INDEX_FIRST_GENERIC_ITEM.getZeroBased());
        GroupDeleteCommand deleteCommand = new GroupDeleteCommand(INDEX_FIRST_GENERIC_ITEM);

        String expectedMessage = String.format(GroupDeleteCommand.MESSAGE_SUCCESS, groupToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteGroup(groupToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredGroupList().size() + 1);
        GroupDeleteCommand groupDeleteCommand = new GroupDeleteCommand(outOfBoundIndex);

        assertCommandFailure(groupDeleteCommand, model, Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showGroupAtIndex(model, INDEX_FIRST_GENERIC_ITEM);

        Group groupToDelete = model.getFilteredGroupList().get(INDEX_FIRST_GENERIC_ITEM.getZeroBased());
        GroupDeleteCommand groupDeleteCommand = new GroupDeleteCommand(INDEX_FIRST_GENERIC_ITEM);

        String expectedMessage = String.format(GroupDeleteCommand.MESSAGE_SUCCESS, groupToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteGroup(groupToDelete);
        showNoGroup(expectedModel);

        assertCommandSuccess(groupDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showGroupAtIndex(model, INDEX_FIRST_GENERIC_ITEM);

        Index outOfBoundIndex = Index.fromZeroBased(model.getAddressBook().getGroupList().size() - 1);
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getGroupList().size());

        GroupDeleteCommand groupDeleteCommand = new GroupDeleteCommand(outOfBoundIndex);

        assertCommandFailure(groupDeleteCommand, model, Messages.MESSAGE_INVALID_GROUP_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        GroupDeleteCommand deleteFirstCommand = new GroupDeleteCommand(INDEX_FIRST_GENERIC_ITEM);
        GroupDeleteCommand deleteSecondCommand = new GroupDeleteCommand(INDEX_SECOND_GENERIC_ITEM);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        GroupDeleteCommand deleteFirstCommandCopy = new GroupDeleteCommand(INDEX_FIRST_GENERIC_ITEM);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show any group.
     */
    private void showNoGroup(Model model) {
        model.updateFilteredGroupList(p -> false);

        assertTrue(model.getFilteredGroupList().isEmpty());
    }
}
