package seedu.careflow.logic.commands.patientcommands;

import static seedu.careflow.logic.commands.patientcommands.CommandTestUtil.*;
import static seedu.careflow.testutil.TypicalDrugs.getTypicalDrugInventory;
//import static seedu.careflow.testutil.TypicalIndexes.CARL;
import static seedu.careflow.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.careflow.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.careflow.testutil.TypicalPatients.*;

import seedu.careflow.commons.core.Messages;
import seedu.careflow.logic.commands.patientcommands.UpdateCommand.EditPatientDescriptor;

import org.junit.jupiter.api.Test;
import seedu.careflow.commons.core.index.Index;
import seedu.careflow.model.*;
import seedu.careflow.model.patient.Name;
import seedu.careflow.model.patient.Patient;
import seedu.careflow.testutil.EditPatientDescriptorBuilder;
import seedu.careflow.testutil.PatientBuilder;

import java.io.File;


/**
 * Contains integration tests (interaction with the CareFlowModel) and unit tests for UpdateCommand.
 */
public class UpdateCommandTest {
//TODO update all different fields.
    private CareFlowModel model = new CareFlowModelManager(getTypicalPatientRecord(),
            getTypicalDrugInventory(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        //person that we wish to edit to
        Patient editedPatient = new PatientBuilder().build();
//        Patient editedPatient = new PatientBuilder().withName("Alice Pauline").withPhone("1111111")
//                .withEmail("editedalice@example.com").withAddress("edited Alice address").withDob("01-01-1998")
//                .withGender("male").withIc("T1111111I").build();
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder(editedPatient).build();
        UpdateCommand editCommand = new UpdateCommand(ALICE.getName(), descriptor);

        String expectedMessage = String.format(UpdateCommand.MESSAGE_UPDATE_PATIENT_SUCCESS, editedPatient);

        CareFlowModel expectedCareFlowModel = new CareFlowModelManager( new PatientRecord(model.getPatientRecord()),
                new DrugInventory(model.getDrugInventory()), new UserPrefs());
        System.out.println( model.getFilteredPatientList());
        expectedCareFlowModel.setPatient(model.getFilteredPatientList().get(0), editedPatient);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedCareFlowModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPatient = Index.fromOneBased(model.getFilteredPatientList().size());
        Patient lastPatient = model.getFilteredPatientList().get(indexLastPatient.getZeroBased());

        PatientBuilder personInList = new PatientBuilder(lastPatient);
        Patient editedPatient = personInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB).build();

        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).build();
        UpdateCommand editCommand = new UpdateCommand(CARL.getName(), descriptor);

        String expectedMessage = String.format(UpdateCommand.MESSAGE_UPDATE_PATIENT_SUCCESS, editedPatient);

        CareFlowModel expectedCareFlowModel = new CareFlowModelManager( new PatientRecord(model.getPatientRecord()),
                new DrugInventory(model.getDrugInventory()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedCareFlowModel);
    }

//    @Test
//    public void execute_noFieldSpecifiedUnfilteredList_success() {
//        UpdateCommand editCommand = new UpdateCommand(CARL.getName(), new EditPatientDescriptor());
//        Patient editedPatient = model.getFilteredPatientList().get(CARL.getName());
//
//        String expectedMessage = String.format(UpdateCommand.MESSAGE_UPDATE_PATIENT_SUCCESS, editedPatient);
//
//        CareFlowModel expectedCareFlowModel = new CareFlowModelManager( new PatientRecord(model.getPatientRecord()),
//                new DrugInventory(model.getDrugInventory()), new UserPrefs());
//        assertCommandSuccess(editCommand, model, expectedMessage, expectedCareFlowModel);
//    }

//    @Test
//    public void execute_filteredList_success() {
//        showPatientAtIndex(model, CARL.getName());
//
//        Patient personInFilteredList = model.getFilteredPatientList().get(CARL.getName().getZeroBased());
//        Patient editedPatient = new PatientBuilder(personInFilteredList).withName(VALID_NAME_BOB).build();
//        UpdateCommand editCommand = new UpdateCommand(CARL.getName(),
//                new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB).build());
//
//        String expectedMessage = String.format(UpdateCommand.MESSAGE_UPDATE_PATIENT_SUCCESS, editedPatient);
//
//        CareFlowModel expectedCareFlowModel = new CareFlowModelManager( new PatientRecord(model.getPatientRecord()),
//                new DrugInventory(model.getDrugInventory()), new UserPrefs());
//
//        assertCommandSuccess(editCommand, model, expectedMessage, expectedCareFlowModel);
//    }
//
//    @Test
//    public void execute_duplicatePatientUnfilteredList_failure() {
//        Patient firstPatient = model.getFilteredPatientList().get(CARL.getName().getZeroBased());
//        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder(firstPatient).build();
//        UpdateCommand editCommand = new UpdateCommand(INDEX_SECOND_PATIENT, descriptor);
//
//        assertCommandFailure(editCommand, model, UpdateCommand.MESSAGE_DUPLICATE_PATIENT);
//    }

    @Test
    public void execute_duplicatePatientFilteredList_failure() {
        showPatientAtIndex(model, INDEX_FIRST);

        // edit person in filtered list into a duplicate in address book
        Patient personInList = model.getPatientRecord().getPatientList().get(INDEX_SECOND.getZeroBased());
        UpdateCommand editCommand = new UpdateCommand(CARL.getName(),
                new EditPatientDescriptorBuilder(personInList).build());

        assertCommandFailure(editCommand, model, UpdateCommand.MESSAGE_DUPLICATE_PATIENT);
    }
//
//    @Test
//    public void execute_invalidPatientIndexUnfilteredList_failure() {
//        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
//        UpdateCommand.EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB).build();
//        UpdateCommand editCommand = new UpdateCommand(outOfBoundIndex, descriptor);
//
//        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
//    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPatientNameFilteredList_failure() {
        showPatientAtIndex(model, INDEX_FIRST);
        Name invalidName = new Name("InvalidName");
//        Index outOfBoundIndex = INDEX_SECOND_PATIENT;
        // ensures that outOfBoundIndex is still in bounds of address book list
//        assertTrue(outOfBoundIndex.getZeroBased() < model.getPatientRecord().getPatientList().size());

        UpdateCommand editCommand = new UpdateCommand(invalidName,
                new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB).build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

//    @Test
//    public void equals() {
//        final UpdateCommand standardCommand = new UpdateCommand(CARL.getName(), DESC_AMY);
//
//        // same values -> returns true
//        UpdateCommand.EditPatientDescriptor copyDescriptor = new EditPatientDescriptor(DESC_AMY);
//        UpdateCommand commandWithSameValues = new UpdateCommand(CARL.getName(), copyDescriptor);
//        assertTrue(standardCommand.equals(commandWithSameValues));
//
//        // same object -> returns true
//        assertTrue(standardCommand.equals(standardCommand));
//
//        // null -> returns false
//        assertFalse(standardCommand.equals(null));
//
//        // different types -> returns false
//        assertFalse(standardCommand.equals(new ClearCommand()));
//
//        // different index -> returns false
//        assertFalse(standardCommand.equals(new UpdateCommand(ELLE.getName(), DESC_AMY)));
//
//        // different descriptor -> returns false
//        assertFalse(standardCommand.equals(new UpdateCommand(CARL.getName(), DESC_BOB)));
//    }

}

