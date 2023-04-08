package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showFishAtIndex;
import static seedu.address.testutil.TypicalFishes.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_FISH;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_FISH;
import static seedu.address.testutil.TypicalReadings.getTypicalFullReadingLevels;
import static seedu.address.testutil.TypicalTanks.getTypicalTankList;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.fish.FishDeleteCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.fish.Fish;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList(),
            getTypicalTankList(), getTypicalFullReadingLevels());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Fish fishToDelete = model.getFilteredFishList().get(INDEX_FIRST_FISH.getZeroBased());
        FishDeleteCommand deleteCommand = new FishDeleteCommand(INDEX_FIRST_FISH);

        String expectedMessage = String.format(FishDeleteCommand.MESSAGE_DELETE_FISH_SUCCESS, fishToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), getTypicalTaskList(),
                getTypicalTankList(), getTypicalFullReadingLevels());
        expectedModel.deleteFish(fishToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFishList().size() + 1);
        FishDeleteCommand deleteCommand = new FishDeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_FISH_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showFishAtIndex(model, INDEX_FIRST_FISH);

        Fish fishToDelete = model.getFilteredFishList().get(INDEX_FIRST_FISH.getZeroBased());
        FishDeleteCommand deleteCommand = new FishDeleteCommand(INDEX_FIRST_FISH);

        String expectedMessage = String.format(FishDeleteCommand.MESSAGE_DELETE_FISH_SUCCESS, fishToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs(), getTypicalTaskList(),
                getTypicalTankList(), getTypicalFullReadingLevels());
        expectedModel.deleteFish(fishToDelete);
        showNoFish(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showFishAtIndex(model, INDEX_FIRST_FISH);

        Index outOfBoundIndex = INDEX_SECOND_FISH;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getFishList().size());

        FishDeleteCommand deleteCommand = new FishDeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_FISH_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        FishDeleteCommand deleteFirstCommand = new FishDeleteCommand(INDEX_FIRST_FISH);
        FishDeleteCommand deleteSecondCommand = new FishDeleteCommand(INDEX_SECOND_FISH);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        FishDeleteCommand deleteFirstCommandCopy = new FishDeleteCommand(INDEX_FIRST_FISH);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different fish -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoFish(Model model) {
        model.updateFilteredFishList(p -> false);

        assertTrue(model.getFilteredFishList().isEmpty());
    }
}
