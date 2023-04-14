package seedu.powercards.logic.commands.cardcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.powercards.logic.commands.CommandTestUtil.DESC_GRAVITY;
import static seedu.powercards.logic.commands.CommandTestUtil.DESC_PHOTOSYNTHESIS;
import static seedu.powercards.logic.commands.CommandTestUtil.VALID_QUESTION_PHOTOSYNTHESIS;
import static seedu.powercards.logic.commands.CommandTestUtil.VALID_TAG_HARD;
import static seedu.powercards.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.powercards.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.powercards.logic.commands.CommandTestUtil.showCardAtIndex;
import static seedu.powercards.testutil.TypicalCards.getTypicalMasterDeck;
import static seedu.powercards.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.powercards.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.powercards.commons.core.Messages;
import seedu.powercards.commons.core.index.Index;
import seedu.powercards.logic.commands.ClearCommand;
import seedu.powercards.logic.commands.cardcommands.EditCardCommand.EditCardDescriptor;
import seedu.powercards.model.MasterDeck;
import seedu.powercards.model.Model;
import seedu.powercards.model.ModelManager;
import seedu.powercards.model.UserPrefs;
import seedu.powercards.model.card.Card;
import seedu.powercards.testutil.CardBuilder;
import seedu.powercards.testutil.EditCardDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCardCommand.
 */
public class EditCardCommandTest {

    private final Model model = new ModelManager(getTypicalMasterDeck(), new UserPrefs());

    {
        model.updateFilteredCardList(Model.PREDICATE_SHOW_ALL_CARDS);
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Card editedCard = new CardBuilder().build(); // in DEFAULT deck
        EditCardCommand.EditCardDescriptor descriptor = new EditCardDescriptorBuilder(editedCard).build();
        EditCardCommand editCardCommand = new EditCardCommand(INDEX_FIRST, descriptor);

        String expectedMessage = String.format(EditCardCommand.MESSAGE_EDIT_CARD_SUCCESS, editedCard);

        Model expectedModel = new ModelManager(new MasterDeck(model.getMasterDeck()), new UserPrefs());
        expectedModel.updateFilteredCardList(Model.PREDICATE_SHOW_ALL_CARDS);
        Card toEdit = model.getFilteredCardList().get(0); // card at first index
        Card toReplace = new Card(editedCard.getQuestion(), editedCard.getAnswer(),
                editedCard.getTag(), toEdit.getDeck()); // new card is in the same deck as old card
        expectedModel.setCard(toEdit, toReplace);

        assertCommandSuccess(editCardCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastCard = Index.fromOneBased(model.getFilteredCardList().size());
        Card lastCard = model.getFilteredCardList().get(indexLastCard.getZeroBased());

        CardBuilder cardInList = new CardBuilder(lastCard);
        Card editedCard = cardInList.withQuestion(VALID_QUESTION_PHOTOSYNTHESIS)
                .withTag(VALID_TAG_HARD).build();

        EditCardDescriptor descriptor = new EditCardDescriptorBuilder()
                .withQuestion(VALID_QUESTION_PHOTOSYNTHESIS).withTag(VALID_TAG_HARD).build();
        EditCardCommand editCardCommand = new EditCardCommand(indexLastCard, descriptor);

        String expectedMessage = String.format(EditCardCommand.MESSAGE_EDIT_CARD_SUCCESS, editedCard);

        Model expectedModel = new ModelManager(new MasterDeck(model.getMasterDeck()), new UserPrefs());
        expectedModel.updateFilteredCardList(Model.PREDICATE_SHOW_ALL_CARDS);
        expectedModel.setCard(lastCard, editedCard);

        assertCommandSuccess(editCardCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCardCommand editCardCommand = new EditCardCommand(INDEX_FIRST, new EditCardCommand.EditCardDescriptor());
        Card editedCard = model.getFilteredCardList().get(INDEX_FIRST.getZeroBased());

        String expectedMessage = String.format(EditCardCommand.MESSAGE_EDIT_CARD_SUCCESS, editedCard);

        Model expectedModel = new ModelManager(new MasterDeck(model.getMasterDeck()), new UserPrefs());
        expectedModel.updateFilteredCardList(Model.PREDICATE_SHOW_ALL_CARDS);

        assertCommandSuccess(editCardCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showCardAtIndex(model, INDEX_FIRST);

        Card cardInFilteredList = model.getFilteredCardList().get(INDEX_FIRST.getZeroBased());
        Card editedCard = new CardBuilder(cardInFilteredList).withQuestion(VALID_QUESTION_PHOTOSYNTHESIS).build();
        EditCardCommand editCardCommand = new EditCardCommand(INDEX_FIRST,
                new EditCardDescriptorBuilder().withQuestion(VALID_QUESTION_PHOTOSYNTHESIS).build());

        String expectedMessage = String.format(EditCardCommand.MESSAGE_EDIT_CARD_SUCCESS, editedCard);

        Model expectedModel = new ModelManager(new MasterDeck(model.getMasterDeck()), new UserPrefs());
        expectedModel.setCard(model.getFilteredCardList().get(0), editedCard);

        assertCommandSuccess(editCardCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateCardUnfilteredList_failure() {
        Card firstCard = model.getFilteredCardList().get(INDEX_FIRST.getZeroBased());
        EditCardCommand.EditCardDescriptor descriptor = new EditCardDescriptorBuilder(firstCard).build();
        EditCardCommand editCardCommand = new EditCardCommand(INDEX_SECOND, descriptor);

        assertCommandFailure(editCardCommand, model, EditCardCommand.MESSAGE_DUPLICATE_CARD);
    }

    @Test
    public void execute_duplicateCardFilteredList_failure() {
        showCardAtIndex(model, INDEX_FIRST);

        // edit card in filtered list into a duplicate in master deck
        Card cardInList = model.getMasterDeck().getCardList().get(INDEX_SECOND.getZeroBased());
        EditCardCommand editCardCommand = new EditCardCommand(INDEX_FIRST,
                new EditCardDescriptorBuilder(cardInList).build());

        assertCommandFailure(editCardCommand, model, EditCardCommand.MESSAGE_DUPLICATE_CARD);
    }

    @Test
    public void execute_invalidCardIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCardList().size() + 1);
        EditCardCommand.EditCardDescriptor descriptor = new EditCardDescriptorBuilder()
                .withQuestion(VALID_QUESTION_PHOTOSYNTHESIS).build();
        EditCardCommand editCardCommand = new EditCardCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCardCommand, model, Messages.MESSAGE_INVALID_CARD_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of master deck
     */
    @Test
    public void execute_invalidCardIndexFilteredList_failure() {
        showCardAtIndex(model, INDEX_FIRST);
        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getMasterDeck().getCardList().size());

        EditCardCommand editCardCommand = new EditCardCommand(outOfBoundIndex,
                new EditCardDescriptorBuilder().withQuestion(VALID_QUESTION_PHOTOSYNTHESIS).build());

        assertCommandFailure(editCardCommand, model, Messages.MESSAGE_INVALID_CARD_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCardCommand standardCommand = new EditCardCommand(INDEX_FIRST, DESC_GRAVITY);

        // same values -> returns true
        EditCardDescriptor copyDescriptor = new EditCardDescriptor(DESC_GRAVITY);
        EditCardCommand commandWithSameValues = new EditCardCommand(INDEX_FIRST, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCardCommand(INDEX_SECOND, DESC_GRAVITY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCardCommand(INDEX_FIRST, DESC_PHOTOSYNTHESIS)));
    }

}
