package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPets.getTypicalPetPal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.pet.Pet;
import seedu.address.testutil.PetBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPetPal(), new UserPrefs());
    }

    @Test
    public void execute_newPet_success() {
        Pet validPet = new PetBuilder().build();

        Model expectedModel = new ModelManager(model.getPetPal(), new UserPrefs());
        expectedModel.addPet(validPet);

        assertCommandSuccess(new AddCommand(validPet), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validPet), expectedModel);
    }

    @Test
    public void execute_duplicatePet_throwsCommandException() {
        Pet PetInList = model.getPetPal().getPetList().get(0);
        assertCommandFailure(new AddCommand(PetInList), model, AddCommand.MESSAGE_DUPLICATE_PET);
    }

}
