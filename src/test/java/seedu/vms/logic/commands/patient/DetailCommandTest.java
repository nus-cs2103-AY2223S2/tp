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

import seedu.vms.commons.core.index.Index;
import seedu.vms.model.Model;
import seedu.vms.model.ModelManager;
import seedu.vms.model.UserPrefs;
import seedu.vms.model.patient.Patient;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DetailCommand}.
 */
public class DetailCommandTest {

    private Model model = new ModelManager(getTypicalPatientManager(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Patient patientToDetail = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased()).getValue();
        DetailCommand detailCommand = new DetailCommand(INDEX_FIRST_PATIENT);

        String expectedMessage = String.format(DetailCommand.MESSAGE_SUCCESS, INDEX_FIRST_PATIENT.getOneBased(),
                patientToDetail);

        assertCommandSuccess(detailCommand, model, expectedMessage, model);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        DetailCommand detailCommand = new DetailCommand(outOfBoundIndex);

        assertCommandFailure(detailCommand, model, DetailCommand.MESSAGE_FAILURE);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPatientAtIndex(model, INDEX_FIRST_PATIENT);

        Patient patientToDetail = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased()).getValue();
        DetailCommand detailCommand = new DetailCommand(INDEX_FIRST_PATIENT);

        String expectedMessage = String.format(DetailCommand.MESSAGE_SUCCESS, INDEX_FIRST_PATIENT.getOneBased(),
                patientToDetail);

        assertCommandSuccess(detailCommand, model, expectedMessage, model);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPatientAtIndex(model, INDEX_FIRST_PATIENT);

        Index outOfBoundIndex = INDEX_NINE_NINE_PATIENT;

        DetailCommand detailCommand = new DetailCommand(outOfBoundIndex);

        assertCommandFailure(detailCommand, model, DetailCommand.MESSAGE_FAILURE);
    }

    @Test
    public void equals() {
        DetailCommand detailFirstCommand = new DetailCommand(INDEX_FIRST_PATIENT);
        DetailCommand detailSecondCommand = new DetailCommand(INDEX_SECOND_PATIENT);

        // same object -> returns true
        assertTrue(detailFirstCommand.equals(detailFirstCommand));

        // same values -> returns true
        DetailCommand detailFirstCommandCopy = new DetailCommand(INDEX_FIRST_PATIENT);
        assertTrue(detailFirstCommand.equals(detailFirstCommandCopy));

        // different types -> returns false
        assertFalse(detailFirstCommand.equals(1));

        // null -> returns false
        assertFalse(detailFirstCommand.equals(null));

        // different patient -> returns false
        assertFalse(detailFirstCommand.equals(detailSecondCommand));
    }
}
