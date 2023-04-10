package seedu.medinfo.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.medinfo.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.medinfo.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.medinfo.testutil.TypicalPatients.getTypicalMedInfo;

import org.junit.jupiter.api.Test;

import seedu.medinfo.model.Model;
import seedu.medinfo.model.ModelManager;
import seedu.medinfo.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalMedInfo(), new UserPrefs());

    //    @Test
    //    public void execute_validIndexUnfilteredList_success() {
    //        Patient patientToDelete = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());
    //        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);
    //
    //        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PATIENT_SUCCESS, patientToDelete);
    //
    //        ModelManager expectedModel = new ModelManager(model.getMedInfo(), new UserPrefs());
    //        expectedModel.deletePatient(patientToDelete);
    //
    //        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    //    }
    //
    //    @Test
    //    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
    //        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
    //        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);
    //
    //        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    //    }
    //
    //    @Test
    //    public void execute_validIndexFilteredList_success() {
    //        showPersonAtIndex(model, INDEX_FIRST_PERSON);
    //
    //        Patient patientToDelete = model.getFilteredPatientList().get(INDEX_FIRST_PERSON.getZeroBased());
    //        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_PERSON);
    //
    //        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_PATIENT_SUCCESS, patientToDelete);
    //
    //        Model expectedModel = new ModelManager(model.getMedInfo(), new UserPrefs());
    //        expectedModel.deletePatient(patientToDelete);
    //        showNoPerson(expectedModel);
    //
    //        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    //    }
    //
    //    @Test
    //    public void execute_invalidIndexFilteredList_throwsCommandException() {
    //        showPersonAtIndex(model, INDEX_FIRST_PERSON);
    //
    //        Index outOfBoundIndex = INDEX_SECOND_PERSON;
    //        // ensures that outOfBoundIndex is still in bounds of medinfo book list
    //        assertTrue(outOfBoundIndex.getZeroBased() < model.getMedInfo().getPatientList().size());
    //
    //        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);
    //
    //        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    //    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_PERSON);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_PERSON);
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
    private void showNoPerson(Model model) {
        model.updateFilteredPatientList(p -> false);

        assertTrue(model.getFilteredPatientList().isEmpty());
    }
}
