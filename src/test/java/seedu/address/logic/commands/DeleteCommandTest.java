package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPetAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PET;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PET;
import static seedu.address.testutil.TypicalPets.getTypicalPetPal;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.pet.Pet;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalPetPal(), getTypicalPetPal(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Pet petToDelete = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PET);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PET_SUCCESS, petToDelete);

        ModelManager expectedModel = new ModelManager(model.getPetPal(), model.getPetPalArchive(), new UserPrefs());
        expectedModel.deletePet(petToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPetList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPetAtIndex(model, INDEX_FIRST_PET);

        Pet petToDelete = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PET);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PET_SUCCESS, petToDelete);

        Model expectedModel = new ModelManager(model.getPetPal(), model.getPetPalArchive(), new UserPrefs());
        expectedModel.deletePet(petToDelete);
        showNoPet(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPetAtIndex(model, INDEX_FIRST_PET);

        Index outOfBoundIndex = INDEX_SECOND_PET;
        // ensures that outOfBoundIndex is still in bounds of PetPal list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPetPal().getPetList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_PET);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_PET);

        // same object -> returns true
        assertEquals(deleteFirstCommand, deleteFirstCommand);

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_PET);
        assertEquals(deleteFirstCommand, deleteFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, deleteFirstCommand);

        // null -> returns false
        assertNotEquals(null, deleteFirstCommand);

        // different PET -> returns false
        assertNotEquals(deleteFirstCommand, deleteSecondCommand);
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPet(Model model) {
        model.updateFilteredPetList(p -> false);

        assertTrue(model.getFilteredPetList().isEmpty());
    }
}
