package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showPatientAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPatients.getTypicalPatientsOnlyAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code SelectPatientCommand}.
 */
public class SelectPatientCommandTest {

    private Model model = new ModelManager(getTypicalPatientsOnlyAddressBook(), new UserPrefs());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        SelectPatientCommand selectPatientCommand = new SelectPatientCommand(outOfBoundIndex);

        assertCommandFailure(selectPatientCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }


    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPatientAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPatientList().size());

        SelectPatientCommand selectPatientCommand = new SelectPatientCommand(outOfBoundIndex);

        assertCommandFailure(selectPatientCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_getCommandUsageSuccessful() {
        String messageUsage = SelectPatientCommand.COMMAND_WORD
                + ": Selects the patient identified by the index number used in the displayed patient list.\n"
                + "Parameters: INDEX (must be a positive integer)\n"
                + "Example: " + SelectPatientCommand.COMMAND_WORD + " 1";
        assertEquals(messageUsage, SelectPatientCommand.getCommandUsage());
    }

    @Test
    public void execute_getMessageSuccessSuccessful() {
        String messageSuccess = "Selected patient %1$s";
        assertEquals(messageSuccess, SelectPatientCommand.getMessageSuccess());
    }

    @Test
    public void equals() {
        SelectPatientCommand selectFirstCommand = new SelectPatientCommand(INDEX_FIRST_PERSON);
        SelectPatientCommand selectSecondCommand = new SelectPatientCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(selectFirstCommand.equals(selectFirstCommand));

        // same values -> returns false since list has been filtered once already
        SelectPatientCommand selectFirstCommandCopy = new SelectPatientCommand(INDEX_FIRST_PERSON);
        assertFalse(selectFirstCommand.equals(selectFirstCommandCopy));

        // different types -> returns false
        assertFalse(selectFirstCommand.equals(1));

        // null -> returns false
        assertFalse(selectFirstCommand.equals(null));

        // different patient -> returns false
        assertFalse(selectFirstCommand.equals(selectSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPatient(Model model) {
        model.updateFilteredPatientList(p -> false);

        assertTrue(model.getFilteredPatientList().isEmpty());
    }
}
