package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PET;
import static seedu.address.testutil.TypicalPets.getTypicalPet;
import static seedu.address.testutil.TypicalPets.getTypicalPetPal;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PetPal;
import seedu.address.model.UserPrefs;
import seedu.address.model.pet.Pet;

public class UndoCommandTest {

    @Test
    public void undo_AddCommand_success() {
        Model model = new ModelManager();
        model.addPet(getTypicalPet().get(1));

        Model expectedModel = new ModelManager();

        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void undo_deleteCommand_success() {
        Model model = new ModelManager(getTypicalPetPal(), getTypicalPetPal(), new UserPrefs());
        Pet petToDelete = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        model.deletePet(petToDelete);

        Model expectedModel = new ModelManager(getTypicalPetPal(), getTypicalPetPal(), new UserPrefs());

        assertCommandSuccess(new UndoCommand(), model, UndoCommand.MESSAGE_SUCCESS, expectedModel);
    }

}