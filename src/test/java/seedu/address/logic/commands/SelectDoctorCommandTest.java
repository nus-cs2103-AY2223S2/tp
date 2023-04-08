package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.showDoctorAtIndex;
import static seedu.address.testutil.TypicalDoctors.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code SelectDoctorCommand}.
 */
public class SelectDoctorCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredDoctorList().size() + 1);
        SelectDoctorCommand selectDoctorCommand = new SelectDoctorCommand(outOfBoundIndex);

        assertCommandFailure(selectDoctorCommand, model, Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
    }


    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showDoctorAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getDoctorList().size());

        SelectDoctorCommand selectDoctorCommand = new SelectDoctorCommand(outOfBoundIndex);

        assertCommandFailure(selectDoctorCommand, model, Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
    }

    @Test
    public void execute_getCommandUsageSuccessful() {
        String messageUsage = SelectDoctorCommand.COMMAND_WORD
                + ": Selects the doctor identified by the index number used in the displayed doctor list.\n"
                + "Parameters: INDEX (must be a positive integer)\n"
                + "Example: " + SelectDoctorCommand.COMMAND_WORD + " 1";

        assertEquals(messageUsage, SelectDoctorCommand.getCommandUsage());
    }

    @Test
    public void execute_getMessageSuccessSuccessful() {
        String messageSuccess = "Selected doctor %1$s";

        assertEquals(messageSuccess, SelectDoctorCommand.getMessageSuccess());
    }

    @Test
    public void equals() {
        SelectDoctorCommand selectFirstCommand = new SelectDoctorCommand(INDEX_FIRST_PERSON);
        SelectDoctorCommand selectSecondCommand = new SelectDoctorCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(selectFirstCommand.equals(selectFirstCommand));

        // same values -> returns false since list has been filtered once already
        SelectDoctorCommand selectFirstCommandCopy = new SelectDoctorCommand(INDEX_FIRST_PERSON);
        assertFalse(selectFirstCommand.equals(selectFirstCommandCopy));

        // different types -> returns false
        assertFalse(selectFirstCommand.equals(1));

        // null -> returns false
        assertFalse(selectFirstCommand.equals(null));

        // different doctor -> returns false
        assertFalse(selectFirstCommand.equals(selectSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoDoctor(Model model) {
        model.updateFilteredDoctorList(p -> false);

        assertTrue(model.getFilteredDoctorList().isEmpty());
    }
}
