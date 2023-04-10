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
import seedu.address.model.PetPal;
import seedu.address.model.UserPrefs;
import seedu.address.model.pet.Pet;

public class ChangeCostCommandTest {

    private Model model = new ModelManager(getTypicalPetPal(), getTypicalPetPal(), new UserPrefs());
    private final double rate = 0.5;
    private final double flatCost = 10.0;

    @Test
    public void execute_success() {
        Pet changedCostPet = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        ChangeCostCommand changeCostCommand = new ChangeCostCommand(INDEX_FIRST_PET, rate, flatCost);

        String expectedMessage = String.format(ChangeCostCommand.MESSAGE_SUCCESS);

        Model expectedModel = new ModelManager(new PetPal(model.getPetPal()),
                model.getPetPalArchive(), new UserPrefs());
        expectedModel.setPet(model.getFilteredPetList().get(0), changedCostPet);

        assertCommandSuccess(changeCostCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPetList().size() + 1);
        ChangeCostCommand changeCostCommand = new ChangeCostCommand(outOfBoundIndex, rate, flatCost);

        assertCommandFailure(changeCostCommand, model, Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPetAtIndex(model, INDEX_FIRST_PET);

        Index outOfBoundIndex = INDEX_SECOND_PET;
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPetPal().getPetList().size());

        ChangeCostCommand changeCostCommand = new ChangeCostCommand(outOfBoundIndex, 0.5, 10.0);

        assertCommandFailure(changeCostCommand, model, Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ChangeCostCommand changeCostFirstCommand = new ChangeCostCommand(INDEX_FIRST_PET, rate, flatCost);
        ChangeCostCommand changeCostSecondCommand = new ChangeCostCommand(INDEX_SECOND_PET, rate, flatCost);

        // same object -> returns true
        assertEquals(changeCostFirstCommand, changeCostFirstCommand);

        // same values -> returns true
        ChangeCostCommand changeCostFirstCommandCopy = new ChangeCostCommand(INDEX_FIRST_PET, rate, flatCost);
        assertEquals(changeCostFirstCommand, changeCostFirstCommandCopy);


        // different types -> returns false
        assertNotEquals(1, changeCostFirstCommand);

        // null -> returns false
        assertNotEquals(null, changeCostFirstCommand);

        // different PET -> returns false
        assertNotEquals(changeCostFirstCommand, changeCostSecondCommand);
    }
}
