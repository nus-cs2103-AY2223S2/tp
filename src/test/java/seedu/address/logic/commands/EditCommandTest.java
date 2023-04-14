package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showOpeningAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_OPENING;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_OPENING;
import static seedu.address.testutil.TypicalOpenings.getTypicalUltron;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditOpeningDescriptorBuilder;
import seedu.ultron.commons.core.Messages;
import seedu.ultron.commons.core.index.Index;
import seedu.ultron.logic.commands.ClearCommand;
import seedu.ultron.logic.commands.EditCommand;
import seedu.ultron.logic.commands.EditCommand.EditOpeningDescriptor;
import seedu.ultron.model.Model;
import seedu.ultron.model.ModelManager;
import seedu.ultron.model.Ultron;
import seedu.ultron.model.UserPrefs;
import seedu.ultron.model.opening.Opening;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalUltron(), new UserPrefs());

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_OPENING, new EditOpeningDescriptor());
        Opening editedOpening = model.getFilteredOpeningList().get(INDEX_FIRST_OPENING.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_OPENING_SUCCESS, editedOpening);

        Model expectedModel = new ModelManager(new Ultron(model.getUltron()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateOpeningUnfilteredList_failure() {
        Opening firstOpening = model.getFilteredOpeningList().get(INDEX_FIRST_OPENING.getZeroBased());
        EditOpeningDescriptor descriptor = new EditOpeningDescriptorBuilder(firstOpening).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_OPENING, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_OPENING);
    }

    @Test
    public void execute_duplicateOpeningFilteredList_failure() {
        showOpeningAtIndex(model, INDEX_FIRST_OPENING);

        // edit opening in filtered list into a duplicate in address book
        Opening openingInList = model.getUltron().getOpeningList().get(INDEX_SECOND_OPENING.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_OPENING,
                new EditOpeningDescriptorBuilder(openingInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_OPENING);
    }

    @Test
    public void execute_invalidOpeningIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredOpeningList().size() + 1);
        EditOpeningDescriptor descriptor = new EditOpeningDescriptorBuilder()
                .withPosition(VALID_POSITION_SHOPEE).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_OPENING_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidOpeningIndexFilteredList_failure() {
        showOpeningAtIndex(model, INDEX_FIRST_OPENING);
        Index outOfBoundIndex = INDEX_SECOND_OPENING;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUltron().getOpeningList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditOpeningDescriptorBuilder().withPosition(VALID_POSITION_SHOPEE).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_OPENING_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_OPENING, DESC_GOOGLE);

        // same values -> returns true
        EditOpeningDescriptor copyDescriptor = new EditOpeningDescriptor(DESC_GOOGLE);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_OPENING, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_OPENING, DESC_GOOGLE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_OPENING, DESC_SHOPEE)));
    }

}
