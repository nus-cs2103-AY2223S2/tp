package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PET;
import static seedu.address.testutil.TypicalPets.getTypicalPetPal;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PetPal;
import seedu.address.model.UserPrefs;
import seedu.address.model.pet.Pet;

public class ChangeCostCommandTest {

    private Model model = new ModelManager(getTypicalPetPal(), getTypicalPetPal(), new UserPrefs());

    @Test
    public void execute_success() {
        Pet changedCostPet = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        ChangeCostCommand changeCostCommand = new ChangeCostCommand(INDEX_FIRST_PET, 0.1, 10);

        String expectedMessage = String.format(ChangeCostCommand.MESSAGE_SUCCESS, changedCostPet);

        Model expectedModel = new ModelManager(new PetPal(model.getPetPal()),
                model.getPetPalArchive(), new UserPrefs());
        expectedModel.setPet(model.getFilteredPetList().get(0), changedCostPet);

        assertCommandSuccess(changeCostCommand, model, expectedMessage, expectedModel);
    }
}
