package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DR_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIALTY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_YOE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showDoctorAtIndex;
import static seedu.address.testutil.TypicalDoctors.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditDoctorCommand.EditDoctorDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Doctor;
import seedu.address.testutil.DoctorBuilder;
import seedu.address.testutil.EditDoctorDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditDoctorCommand.
 */
public class EditDoctorCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Doctor editedDoctor = new DoctorBuilder().build();
        EditDoctorDescriptor descriptor = new EditDoctorDescriptorBuilder(editedDoctor).build();
        EditDoctorCommand editCommand = new EditDoctorCommand(INDEX_FIRST_PERSON, descriptor);

        String expectedMessage = String.format(EditDoctorCommand.MESSAGE_EDIT_DOCTOR_SUCCESS, editedDoctor);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setDoctor(model.getFilteredDoctorList().get(0), editedDoctor);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastDoctor = Index.fromOneBased(model.getFilteredDoctorList().size());
        Doctor lastDoctor = model.getFilteredDoctorList().get(indexLastDoctor.getZeroBased());

        DoctorBuilder doctorInList = new DoctorBuilder(lastDoctor);
        Doctor editedDoctor = doctorInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withSpecialty(VALID_SPECIALTY_BOB).withYoe(VALID_YOE_BOB).withTags(VALID_TAG_HUSBAND).build();

        EditDoctorDescriptor descriptor = new EditDoctorDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withSpecialty(VALID_SPECIALTY_BOB).withYoe(VALID_YOE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        EditDoctorCommand editCommand = new EditDoctorCommand(indexLastDoctor, descriptor);

        String expectedMessage = String.format(EditDoctorCommand.MESSAGE_EDIT_DOCTOR_SUCCESS, editedDoctor);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setDoctor(lastDoctor, editedDoctor);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditDoctorCommand editCommand = new EditDoctorCommand(INDEX_FIRST_PERSON, new EditDoctorDescriptor());
        Doctor editedDoctor = model.getFilteredDoctorList().get(INDEX_FIRST_PERSON.getZeroBased());

        String expectedMessage = String.format(EditDoctorCommand.MESSAGE_EDIT_DOCTOR_SUCCESS, editedDoctor);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showDoctorAtIndex(model, INDEX_FIRST_PERSON);

        Doctor doctorInFilteredList = model.getFilteredDoctorList().get(INDEX_FIRST_PERSON.getZeroBased());
        Doctor editedDoctor = new DoctorBuilder(doctorInFilteredList).withName(VALID_NAME_BOB).build();
        EditDoctorCommand editCommand = new EditDoctorCommand(INDEX_FIRST_PERSON,
                new EditDoctorDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditDoctorCommand.MESSAGE_EDIT_DOCTOR_SUCCESS, editedDoctor);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setDoctor(model.getFilteredDoctorList().get(0), editedDoctor);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateDoctorUnfilteredList_failure() {
        Doctor firstDoctor = model.getFilteredDoctorList().get(INDEX_FIRST_PERSON.getZeroBased());
        EditDoctorDescriptor descriptor = new EditDoctorDescriptorBuilder(firstDoctor).build();
        EditDoctorCommand editCommand = new EditDoctorCommand(INDEX_SECOND_PERSON, descriptor);

        assertCommandFailure(editCommand, model, EditDoctorCommand.MESSAGE_DUPLICATE_DOCTOR);
    }

    @Test
    public void execute_duplicateDoctorFilteredList_failure() {
        showDoctorAtIndex(model, INDEX_FIRST_PERSON);

        // edit doctor in filtered list into a duplicate in address book
        Doctor doctorInList = model.getAddressBook().getDoctorList().get(INDEX_SECOND_PERSON.getZeroBased());
        EditDoctorCommand editCommand = new EditDoctorCommand(INDEX_FIRST_PERSON,
                new EditDoctorDescriptorBuilder(doctorInList).build());

        assertCommandFailure(editCommand, model, EditDoctorCommand.MESSAGE_DUPLICATE_DOCTOR);
    }

    @Test
    public void execute_invalidDoctorIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredDoctorList().size() + 1);
        EditDoctorDescriptor descriptor = new EditDoctorDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditDoctorCommand editCommand = new EditDoctorCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidDoctorIndexFilteredList_failure() {
        showDoctorAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getDoctorList().size());

        EditDoctorCommand editCommand = new EditDoctorCommand(outOfBoundIndex,
                new EditDoctorDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditDoctorCommand standardCommand = new EditDoctorCommand(INDEX_FIRST_PERSON, DESC_DR_AMY);

        // same values -> returns true
        EditDoctorDescriptor copyDescriptor = new EditDoctorDescriptor(DESC_DR_AMY);
        EditDoctorCommand commandWithSameValues = new EditDoctorCommand(INDEX_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditDoctorCommand(INDEX_SECOND_PERSON, DESC_DR_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditDoctorCommand(INDEX_FIRST_PERSON, DESC_DR_BOB)));
    }

}
