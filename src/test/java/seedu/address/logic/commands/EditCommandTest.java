package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPetAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PET;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PET;
import static seedu.address.testutil.TypicalPets.getTypicalPetPal;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand.EditPetDescriptor;
import seedu.address.model.PetPal;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.pet.Pet;
import seedu.address.testutil.EditPetDescriptorBuilder;
import seedu.address.testutil.PetBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalPetPal(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Pet editedPet = new PetBuilder().build();
        EditPetDescriptor descriptor = new EditPetDescriptorBuilder(editedPet).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PET, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PET_SUCCESS, editedPet);

        Model expectedModel = new ModelManager(new PetPal(model.getPetPal()), new UserPrefs());
        expectedModel.setPet(model.getFilteredPetList().get(0), editedPet);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPet = Index.fromOneBased(model.getFilteredPetList().size());
        Pet lastPet = model.getFilteredPetList().get(indexLastPet.getZeroBased());

        PetBuilder PetInList = new PetBuilder(lastPet);
        Pet editedPet = PetInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();

        EditPetDescriptor descriptor = new EditPetDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withTags(VALID_TAG_HUSBAND).build();
        EditCommand editCommand = new EditCommand(indexLastPet, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PET_SUCCESS, editedPet);

        Model expectedModel = new ModelManager(new PetPal(model.getPetPal()), new UserPrefs());
        expectedModel.setPet(lastPet, editedPet);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PET, new EditPetDescriptor());
        Pet editedPet = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PET_SUCCESS, editedPet);

        Model expectedModel = new ModelManager(new PetPal(model.getPetPal()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPetAtIndex(model, INDEX_FIRST_PET);

        Pet PetInFilteredList = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        Pet editedPet = new PetBuilder(PetInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PET,
                new EditPetDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PET_SUCCESS, editedPet);

        Model expectedModel = new ModelManager(new PetPal(model.getPetPal()), new UserPrefs());
        expectedModel.setPet(model.getFilteredPetList().get(0), editedPet);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatePetUnfilteredList_failure() {
        Pet firstPet = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        EditPetDescriptor descriptor = new EditPetDescriptorBuilder(firstPet).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_PET, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PET);
    }

    @Test
    public void execute_duplicatePetFilteredList_failure() {
        showPetAtIndex(model, INDEX_FIRST_PET);

        // edit Pet in filtered list into a duplicate in address book
        Pet PetInList = model.getPetPal().getPetList().get(INDEX_SECOND_PET.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PET,
                new EditPetDescriptorBuilder(PetInList).build());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_PET);
    }

    @Test
    public void execute_invalidPetIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPetList().size() + 1);
        EditPetDescriptor descriptor = new EditPetDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPetIndexFilteredList_failure() {
        showPetAtIndex(model, INDEX_FIRST_PET);
        Index outOfBoundIndex = INDEX_SECOND_PET;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPetPal().getPetList().size());

        EditCommand editCommand = new EditCommand(outOfBoundIndex,
                new EditPetDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PET_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PET, DESC_AMY);

        // same values -> returns true
        EditPetDescriptor copyDescriptor = new EditPetDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PET, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PET, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PET, DESC_BOB)));
    }

}
