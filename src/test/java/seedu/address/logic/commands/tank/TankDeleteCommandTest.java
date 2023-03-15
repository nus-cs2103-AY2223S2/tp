package seedu.address.logic.commands.tank;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTankAtIndex;
import static seedu.address.testutil.TypicalFishes.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TANK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TANK;
import static seedu.address.testutil.TypicalTanks.getTypicalTankList;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tank.Tank;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code TankDeleteCommand}.
 */
public class TankDeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList(),
            getTypicalTankList());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Tank tankToDelete = model.getFilteredTankList().get(INDEX_FIRST_TANK.getZeroBased());
        TankDeleteCommand deleteCommand = new TankDeleteCommand(INDEX_FIRST_TANK);

        String expectedMessage = String.format(TankDeleteCommand.MESSAGE_DELETE_TANK_SUCCESS, tankToDelete);

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList(),
                model.getTankList());
        expectedModel.deleteTank(tankToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTankList().size() + 1);
        TankDeleteCommand deleteCommand = new TankDeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_TANK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showTankAtIndex(model, INDEX_FIRST_TANK);

        Tank tankToDelete = model.getFilteredTankList().get(INDEX_FIRST_TANK.getZeroBased());
        TankDeleteCommand deleteCommand = new TankDeleteCommand(INDEX_FIRST_TANK);

        String expectedMessage = String.format(TankDeleteCommand.MESSAGE_DELETE_TANK_SUCCESS, tankToDelete);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs(), getTypicalTaskList(),
                model.getTankList());
        expectedModel.deleteTank(tankToDelete);
        showNoTank(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTankAtIndex(model, INDEX_FIRST_TANK);

        Index outOfBoundIndex = INDEX_SECOND_TANK;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getTankList().getTankList().size());

        TankDeleteCommand deleteCommand = new TankDeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_TANK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        TankDeleteCommand deleteFirstCommand = new TankDeleteCommand(INDEX_FIRST_TANK);
        TankDeleteCommand deleteSecondCommand = new TankDeleteCommand(INDEX_SECOND_TANK);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        TankDeleteCommand deleteFirstCommandCopy = new TankDeleteCommand(INDEX_FIRST_TANK);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different tank -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoTank(Model model) {
        model.updateFilteredTankList(p -> false);

        assertTrue(model.getFilteredTankList().isEmpty());
    }
}
