package seedu.powercards.logic.commands.deckcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.powercards.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.powercards.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.powercards.logic.commands.CommandTestUtil.showDeckAtIndex;
import static seedu.powercards.testutil.TypicalCards.getTypicalMasterDeck;
import static seedu.powercards.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.powercards.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.powercards.commons.core.Messages;
import seedu.powercards.commons.core.index.Index;
import seedu.powercards.model.Model;
import seedu.powercards.model.ModelManager;
import seedu.powercards.model.UserPrefs;
import seedu.powercards.model.deck.Deck;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteDeckCommand}.
 */
public class DeleteDeckCommandTest {

    private Model model = new ModelManager(getTypicalMasterDeck(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Deck deckToDelete = model.getFilteredDeckList().get(INDEX_FIRST.getZeroBased());
        DeleteDeckCommand deleteDeckCommand = new DeleteDeckCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteDeckCommand.MESSAGE_SUCCESS, deckToDelete);

        ModelManager expectedModel = new ModelManager(model.getMasterDeck(), new UserPrefs());
        expectedModel.deleteDeck(deckToDelete);

        assertCommandSuccess(deleteDeckCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredDeckList().size() + 1);
        DeleteDeckCommand deleteDeckCommand = new DeleteDeckCommand(outOfBoundIndex);

        assertCommandFailure(deleteDeckCommand, model, Messages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showDeckAtIndex(model, INDEX_FIRST);
        Deck deckToDelete = model.getFilteredDeckList().get(INDEX_FIRST.getZeroBased());

        DeleteDeckCommand deleteDeckCommand = new DeleteDeckCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteDeckCommand.MESSAGE_SUCCESS, deckToDelete);

        Model expectedModel = new ModelManager(model.getMasterDeck(), new UserPrefs());
        expectedModel.deleteDeck(deckToDelete);
        showNoDeck(expectedModel);
        assertCommandSuccess(deleteDeckCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showDeckAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of master deck list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getMasterDeck().getDeckList().size());

        DeleteDeckCommand deleteDeckCommand = new DeleteDeckCommand(outOfBoundIndex);

        assertCommandFailure(deleteDeckCommand, model, Messages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_deletingDeck_deletesCards() {
        Deck deckToDelete = model.getFilteredDeckList().get(INDEX_FIRST.getZeroBased()); // contains 2 cards
        Model expectedModel = new ModelManager(model.getMasterDeck(), new UserPrefs());
        expectedModel.deleteDeck(deckToDelete);
        assertEquals(model.getMasterDeck().getCardList().size() - 2,
                expectedModel.getMasterDeck().getCardList().size());
    }

    @Test
    public void equals() {
        DeleteDeckCommand deleteFirstCommand = new DeleteDeckCommand(INDEX_FIRST);
        DeleteDeckCommand deleteSecondCommand = new DeleteDeckCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteDeckCommand deleteFirstCommandCopy = new DeleteDeckCommand(INDEX_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different card -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no deck.
     */
    @Test
    private void showNoDeck(Model model) {
        model.updateFilteredDeckList(p -> false);

        assertTrue(model.getFilteredDeckList().isEmpty());
    }
}
