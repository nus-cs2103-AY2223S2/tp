package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_PHOTOSYNTHESIS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HARD;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalCards.getTypicalMasterDeck;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditCardDescriptor;
import seedu.address.model.MasterDeck;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.card.Card;
import seedu.address.model.deck.Deck;
import seedu.address.testutil.CardBuilder;
import seedu.address.testutil.EditCardDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditDeckCommand.
 */
public class EditDeckCommandTest {

    private Model model = new ModelManager(getTypicalMasterDeck(), new UserPrefs());

    @Test
    public void execute_editDeck_editSuccessful() {
        Deck editedDeck = new Deck("Edited Deck");

        EditDeckCommand editDeckCommand = new EditDeckCommand(INDEX_FIRST, editedDeck);

        String expectedMessage = String.format(EditDeckCommand.MESSAGE_EDIT_DECK_SUCCESS, editedDeck);

        Model expectedModel = new ModelManager(new MasterDeck(model.getMasterDeck()), new UserPrefs());
        expectedModel.setDeck(model.getFilteredDeckList().get(0), editedDeck);
        assertCommandSuccess(editDeckCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST);

        Card cardInFilteredList = model.getFilteredCardList().get(INDEX_FIRST.getZeroBased());
        Card editedCard = new CardBuilder(cardInFilteredList).withQuestion(VALID_QUESTION_PHOTOSYNTHESIS).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST,
                new EditCardDescriptorBuilder().withQuestion(VALID_QUESTION_PHOTOSYNTHESIS).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CARD_SUCCESS, editedCard);

        Model expectedModel = new ModelManager(new MasterDeck(model.getMasterDeck()), new UserPrefs());
        expectedModel.setCard(model.getFilteredCardList().get(0), editedCard);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        Card firstCard = model.getFilteredCardList().get(INDEX_FIRST.getZeroBased());
        EditCommand.EditCardDescriptor descriptor = new EditCardDescriptorBuilder(firstCard).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_CARD);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST);

        // edit card in filtered list into a duplicate in address book
        Card cardInList = model.getMasterDeck().getCardList().get(INDEX_SECOND.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST,
                new EditCardDescriptorBuilder(cardInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_CARD);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCardList().size() + 1);
        EditCommand.EditCardDescriptor descriptor = new EditCardDescriptorBuilder()
                .withQuestion(VALID_QUESTION_PHOTOSYNTHESIS).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_CARD_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getMasterDeck().getCardList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditCardDescriptorBuilder().withQuestion(VALID_QUESTION_PHOTOSYNTHESIS).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_CARD_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST, DESC_AMY);

        // same values -> returns true
        EditCardDescriptor copyDescriptor = new EditCardDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST, DESC_BOB)));
    }

}
