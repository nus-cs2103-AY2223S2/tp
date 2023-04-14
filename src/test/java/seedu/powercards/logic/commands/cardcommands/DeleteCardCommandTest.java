package seedu.powercards.logic.commands.cardcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.powercards.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.powercards.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.powercards.logic.commands.CommandTestUtil.showCardAtIndex;
import static seedu.powercards.testutil.TypicalCards.getTypicalMasterDeck;
import static seedu.powercards.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.powercards.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.powercards.commons.core.Messages;
import seedu.powercards.commons.core.index.Index;
import seedu.powercards.model.Model;
import seedu.powercards.model.ModelManager;
import seedu.powercards.model.UserPrefs;
import seedu.powercards.model.card.Card;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCardCommand}.
 */
public class DeleteCardCommandTest {

    private Model model = new ModelManager(getTypicalMasterDeck(), new UserPrefs());

    {
        model.updateFilteredCardList(Model.PREDICATE_SHOW_ALL_CARDS);
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Card cardToDelete = model.getFilteredCardList().get(INDEX_FIRST.getZeroBased());
        DeleteCardCommand deleteCardCommand = new DeleteCardCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteCardCommand.MESSAGE_DELETE_CARD_SUCCESS, cardToDelete);

        ModelManager expectedModel = new ModelManager(model.getMasterDeck(), new UserPrefs());
        expectedModel.updateFilteredCardList(Model.PREDICATE_SHOW_ALL_CARDS);
        expectedModel.deleteCard(cardToDelete);

        assertCommandSuccess(deleteCardCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCardList().size() + 1);
        DeleteCardCommand deleteCardCommand = new DeleteCardCommand(outOfBoundIndex);

        assertCommandFailure(deleteCardCommand, model, Messages.MESSAGE_INVALID_CARD_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showCardAtIndex(model, INDEX_FIRST);
        Card cardToDelete = model.getFilteredCardList().get(INDEX_FIRST.getZeroBased());

        DeleteCardCommand deleteCardCommand = new DeleteCardCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteCardCommand.MESSAGE_DELETE_CARD_SUCCESS, cardToDelete);

        Model expectedModel = new ModelManager(model.getMasterDeck(), new UserPrefs());
        expectedModel.deleteCard(cardToDelete);
        showNoCard(expectedModel);
        assertCommandSuccess(deleteCardCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showCardAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of master deck list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getMasterDeck().getCardList().size());

        DeleteCardCommand deleteCardCommand = new DeleteCardCommand(outOfBoundIndex);

        assertCommandFailure(deleteCardCommand, model, Messages.MESSAGE_INVALID_CARD_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCardCommand deleteFirstCommand = new DeleteCardCommand(INDEX_FIRST);
        DeleteCardCommand deleteSecondCommand = new DeleteCardCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCardCommand deleteFirstCommandCopy = new DeleteCardCommand(INDEX_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different card -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show nothing.
     */
    private void showNoCard(Model model) {
        model.updateFilteredCardList(p -> false);

        assertTrue(model.getFilteredCardList().isEmpty());
    }
}
