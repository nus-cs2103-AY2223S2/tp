package seedu.powercards.logic.commands.deckcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.powercards.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.powercards.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.powercards.testutil.TypicalCards.getTypicalMasterDeck;
import static seedu.powercards.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.powercards.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.powercards.commons.core.Messages;
import seedu.powercards.commons.core.index.Index;
import seedu.powercards.logic.commands.ClearCommand;
import seedu.powercards.model.Model;
import seedu.powercards.model.ModelManager;
import seedu.powercards.model.UserPrefs;
import seedu.powercards.model.deck.Deck;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditDeckCommand.
 */
public class EditDeckCommandTest {

    private final Model model = new ModelManager(getTypicalMasterDeck(), new UserPrefs());

    @Test
    public void execute_editDeck_editSuccessful() {
        Deck editedDeck = new Deck("Edited Deck");

        EditDeckCommand editDeckCommand = new EditDeckCommand(INDEX_FIRST, editedDeck);

        String expectedMessage = String.format(EditDeckCommand.MESSAGE_EDIT_DECK_SUCCESS,
                INDEX_FIRST.getOneBased(), editedDeck);

        Model expectedModel = new ModelManager(model.getMasterDeck(), new UserPrefs());
        expectedModel.setDeck(model.getFilteredDeckList().get(0), editedDeck);
        expectedModel.moveCards(model.getFilteredDeckList().get(0), editedDeck);

        assertCommandSuccess(editDeckCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateDeck_failure() {
        Deck firstDeck = model.getFilteredDeckList().get(INDEX_FIRST.getZeroBased());
        EditDeckCommand editDeckCommand = new EditDeckCommand(INDEX_SECOND, firstDeck);

        assertCommandFailure(editDeckCommand, model, EditDeckCommand.MESSAGE_DUPLICATE_DECK);
    }

    @Test
    public void execute_invalidDeckIndex_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredDeckList().size() + 1);

        EditDeckCommand editDeckCommand = new EditDeckCommand(outOfBoundIndex, new Deck("Invalid Deck"));

        assertCommandFailure(editDeckCommand, model, Messages.MESSAGE_INVALID_DECK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Deck deck = new Deck("Original Deck");
        final EditDeckCommand standardCommand = new EditDeckCommand(INDEX_FIRST, deck);

        // same values -> returns true
        Deck copyDeck = new Deck(deck.getDeckName());
        EditDeckCommand commandWithSameValues = new EditDeckCommand(INDEX_FIRST, copyDeck);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditDeckCommand(INDEX_SECOND, deck)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditDeckCommand(INDEX_FIRST, new Deck("Different Deck"))));
    }

}
