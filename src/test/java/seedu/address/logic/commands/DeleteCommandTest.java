package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showOpeningAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_OPENING;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_OPENING;
import static seedu.address.testutil.TypicalOpenings.getTypicalUltron;

import org.junit.jupiter.api.Test;

import seedu.ultron.commons.core.Messages;
import seedu.ultron.commons.core.index.Index;
import seedu.ultron.logic.commands.DeleteCommand;
import seedu.ultron.model.Model;
import seedu.ultron.model.ModelManager;
import seedu.ultron.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalUltron(), new UserPrefs());

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showOpeningAtIndex(model, INDEX_FIRST_OPENING);

        Index outOfBoundIndex = INDEX_SECOND_OPENING;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUltron().getOpeningList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_OPENING_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_OPENING);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_OPENING);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_OPENING);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different opening -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoOpening(Model model) {
        model.updateFilteredOpeningList(p -> false);

        assertTrue(model.getFilteredOpeningList().isEmpty());
    }
}
