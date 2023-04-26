package seedu.careflow.logic.commands.patientcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.assertCommandFailure;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.assertCommandSuccess;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.showPatientAtIndex;
import static seedu.careflow.testutil.TypicalDrugs.getTypicalDrugInventory;
import static seedu.careflow.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.careflow.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.careflow.testutil.TypicalPatients.getTypicalPatientRecord;

import org.junit.jupiter.api.Test;

import seedu.careflow.commons.core.Messages;
import seedu.careflow.commons.core.index.Index;
import seedu.careflow.model.CareFlowModel;
import seedu.careflow.model.CareFlowModelManager;
import seedu.careflow.model.UserPrefs;
import seedu.careflow.model.patient.Patient;

/**
 * Contains integration tests (interaction with the CareFlowModel) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private CareFlowModel model = new CareFlowModelManager(getTypicalPatientRecord(), getTypicalDrugInventory(),
            new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Patient patientToDelete = model.getFilteredPatientList().get(INDEX_FIRST.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PATIENT_SUCCESS, patientToDelete);

        CareFlowModelManager expectedCareFlowModel = new CareFlowModelManager(model.getPatientRecord(),
                model.getDrugInventory(), new UserPrefs());
        expectedCareFlowModel.deletePatient(patientToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedCareFlowModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPatientAtIndex(model, INDEX_FIRST);

        Patient patientToDelete = model.getFilteredPatientList().get(INDEX_FIRST.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PATIENT_SUCCESS, patientToDelete);

        CareFlowModel expectedCareFlowModel = new CareFlowModelManager(model.getPatientRecord(),
                model.getDrugInventory(), new UserPrefs());
        expectedCareFlowModel.deletePatient(patientToDelete);
        showNoPatient(expectedCareFlowModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedCareFlowModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPatientAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPatientRecord().getPatientList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST);
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
    private void showNoPatient(CareFlowModel model) {
        model.updateFilteredPatientList(p -> false);

        assertTrue(model.getFilteredPatientList().isEmpty());
    }
}
