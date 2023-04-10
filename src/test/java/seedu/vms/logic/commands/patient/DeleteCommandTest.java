package seedu.vms.logic.commands.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.vms.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.vms.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.vms.logic.commands.CommandTestUtil.showPatientAtIndex;
import static seedu.vms.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.vms.testutil.TypicalIndexes.INDEX_NINE_NINE_PATIENT;
import static seedu.vms.testutil.TypicalIndexes.INDEX_SECOND_PATIENT;
import static seedu.vms.testutil.TypicalPatients.getTypicalPatientManager;

import org.junit.jupiter.api.Test;

import seedu.vms.commons.core.Messages;
import seedu.vms.commons.core.index.Index;
import seedu.vms.model.Model;
import seedu.vms.model.ModelManager;
import seedu.vms.model.UserPrefs;
import seedu.vms.model.patient.Patient;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalPatientManager(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() throws Exception {
        Patient patientToDelete = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased()).getValue();
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PATIENT);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PATIENT_SUCCESS, patientToDelete);

        ModelManager expectedModel = new ModelManager(model.getPatientManager(), new UserPrefs());
        expectedModel.deletePatient(INDEX_FIRST_PATIENT.getZeroBased(), true);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PATIENT_ID);
    }

    @Test void execute_previouslyDeletedIndex_throwsCommandException() throws Exception {
        // Delete patient
        Patient patientToDelete = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased()).getValue();
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PATIENT);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PATIENT_SUCCESS, patientToDelete);

        ModelManager expectedModel = new ModelManager(model.getPatientManager(), new UserPrefs());
        expectedModel.deletePatient(INDEX_FIRST_PATIENT.getZeroBased(), true);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);

        // Attempt to delete the same patient
        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PATIENT_ID);
    }

    @Test
    public void execute_validIndexFilteredList_success() throws Exception {
        showPatientAtIndex(model, INDEX_FIRST_PATIENT);

        Patient patientToDelete = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased()).getValue();
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PATIENT);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PATIENT_SUCCESS, patientToDelete);

        Model expectedModel = new ModelManager(model.getPatientManager(), new UserPrefs());
        expectedModel.deletePatient(INDEX_FIRST_PATIENT.getZeroBased(), true);
        showNoPatient(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPatientAtIndex(model, INDEX_FIRST_PATIENT);

        Index outOfBoundIndex = INDEX_NINE_NINE_PATIENT;

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PATIENT_ID);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_PATIENT);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_PATIENT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_PATIENT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different patient -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPatient(Model model) {
        model.updateFilteredPatientList(p -> false);

        assertTrue(model.getFilteredPatientList().isEmpty());
    }
}
