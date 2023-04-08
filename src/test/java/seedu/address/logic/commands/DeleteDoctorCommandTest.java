package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
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
import seedu.address.model.person.doctor.Doctor;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteDoctorCommand}.
 */
public class DeleteDoctorCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Doctor doctorToDelete = model.getFilteredDoctorList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteDoctorCommand deleteDoctorCommand = new DeleteDoctorCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteDoctorCommand.getMessageSuccess(), doctorToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteDoctor(doctorToDelete);

        assertCommandSuccess(deleteDoctorCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredDoctorList().size() + 1);
        DeleteDoctorCommand deleteDoctorCommand = new DeleteDoctorCommand(outOfBoundIndex);

        assertCommandFailure(deleteDoctorCommand, model, Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showDoctorAtIndex(model, INDEX_FIRST_PERSON);

        Doctor doctorToDelete = model.getFilteredDoctorList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeleteDoctorCommand deleteDoctorCommand = new DeleteDoctorCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeleteDoctorCommand.getMessageSuccess(), doctorToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteDoctor(doctorToDelete);
        showNoDoctor(expectedModel);

        assertCommandSuccess(deleteDoctorCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showDoctorAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getDoctorList().size());

        DeleteDoctorCommand deleteDoctorCommand = new DeleteDoctorCommand(outOfBoundIndex);

        assertCommandFailure(deleteDoctorCommand, model, Messages.MESSAGE_INVALID_DOCTOR_DISPLAYED_INDEX);
    }

    @Test
    public void execute_getCommandUsage_success() {
        String messageUsage = DeleteDoctorCommand.COMMAND_WORD + " (short form: "
                + DeleteDoctorCommand.SHORTHAND_COMMAND_WORD + ")"
                + ": Deletes the doctor identified by the index number used in the displayed doctor list.\n"
                + "Parameters: INDEX (must be a positive integer)\n"
                + "Example: " + DeleteDoctorCommand.COMMAND_WORD + " 1";
        assertEquals(DeleteDoctorCommand.getCommandUsage(), messageUsage);
    }

    @Test
    public void execute_deleteDoctor_getMessageSuccessSuccessful() {
        Doctor doctorToDelete = model.getFilteredDoctorList().get(INDEX_FIRST_PERSON.getZeroBased());

        String messageSuccess = "Deleted Doctor: %1$s";
        String expectedMessage = String.format(messageSuccess, doctorToDelete);

        assertEquals(String.format(DeleteDoctorCommand.getMessageSuccess(), doctorToDelete), expectedMessage);
    }

    @Test
    public void equals() {
        DeleteDoctorCommand deleteFirstCommand = new DeleteDoctorCommand(INDEX_FIRST_PERSON);
        DeleteDoctorCommand deleteSecondCommand = new DeleteDoctorCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteDoctorCommand deleteFirstCommandCopy = new DeleteDoctorCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different doctor -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoDoctor(Model model) {
        model.updateFilteredDoctorList(p -> false);

        assertTrue(model.getFilteredDoctorList().isEmpty());
    }

}
