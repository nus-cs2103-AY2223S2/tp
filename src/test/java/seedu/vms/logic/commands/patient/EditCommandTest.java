package seedu.vms.logic.commands.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.vms.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.vms.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_ALLERGY_GLUTEN;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.vms.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.vms.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.vms.logic.commands.CommandTestUtil.showPatientAtIndex;
import static seedu.vms.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.vms.testutil.TypicalIndexes.INDEX_SECOND_PATIENT;
import static seedu.vms.testutil.TypicalPatients.getTypicalPatientManager;

import org.junit.jupiter.api.Test;

import seedu.vms.commons.core.Messages;
import seedu.vms.commons.core.index.Index;
import seedu.vms.logic.commands.patient.EditCommand.EditPatientDescriptor;
import seedu.vms.model.Model;
import seedu.vms.model.ModelManager;
import seedu.vms.model.UserPrefs;
import seedu.vms.model.patient.Patient;
import seedu.vms.model.patient.PatientManager;
import seedu.vms.testutil.EditPatientDescriptorBuilder;
import seedu.vms.testutil.PatientBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalPatientManager(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Patient editedPatient = new PatientBuilder().build();
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder(editedPatient).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PATIENT, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PATIENT_SUCCESS, editedPatient);

        Model expectedModel = new ModelManager(new PatientManager(model.getPatientManager()), new UserPrefs());
        expectedModel.setPatient(INDEX_FIRST_PATIENT.getZeroBased(), editedPatient);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastPatient = Index.fromOneBased(model.getFilteredPatientList().size());
        Patient lastPatient = model.getFilteredPatientList().get(indexLastPatient.getZeroBased()).getValue();

        PatientBuilder patientInList = new PatientBuilder(lastPatient);
        Patient editedPatient = patientInList.withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withAllergies(VALID_ALLERGY_GLUTEN).build();

        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withAllergies(VALID_ALLERGY_GLUTEN).build();
        EditCommand editCommand = new EditCommand(indexLastPatient, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PATIENT_SUCCESS, editedPatient);

        Model expectedModel = new ModelManager(new PatientManager(model.getPatientManager()), new UserPrefs());
        expectedModel.setPatient(indexLastPatient.getZeroBased(), editedPatient);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PATIENT, new EditPatientDescriptor());
        Patient editedPatient = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased()).getValue();

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PATIENT_SUCCESS, editedPatient);

        Model expectedModel = new ModelManager(new PatientManager(model.getPatientManager()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPatientAtIndex(model, INDEX_FIRST_PATIENT);

        Patient patientInFilteredList = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased())
                .getValue();
        Patient editedPatient = new PatientBuilder(patientInFilteredList).withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PATIENT,
                new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB).build());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PATIENT_SUCCESS, editedPatient);

        Model expectedModel = new ModelManager(new PatientManager(model.getPatientManager()), new UserPrefs());
        expectedModel.setPatient(0, editedPatient);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPatientIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withName(VALID_NAME_BOB).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PATIENT_ID);
    }

    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_PATIENT, DESC_AMY);

        // same values -> returns true
        EditPatientDescriptor copyDescriptor = new EditPatientDescriptor(DESC_AMY);
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_PATIENT, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_PATIENT, DESC_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_PATIENT, DESC_BOB)));
    }

}
