package seedu.patientist.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.patientist.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.patientist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.patientist.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.patientist.testutil.TypicalWards.getTypicalPatientist;

import org.junit.jupiter.api.Test;

import seedu.patientist.model.Model;
import seedu.patientist.model.ModelManager;
import seedu.patientist.model.UserPrefs;
import seedu.patientist.model.person.IdNumber;
import seedu.patientist.model.person.Person;
import seedu.patientist.model.person.staff.IsStaffPredicate;
import seedu.patientist.model.person.staff.Staff;

public class DeleteStaffCommandTest {
    private Model model = new ModelManager(getTypicalPatientist(), new UserPrefs());

    @Test
    public void execute_validId_success() {
        model.updateFilteredPersonList(new IsStaffPredicate());
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        IdNumber idToDelete = personToDelete.getIdNumber();
        DeleteStaffCommand deleteStaffCommand = new DeleteStaffCommand(idToDelete);

        String expectedMessage = String.format(DeleteStaffCommand.MESSAGE_DELETE_STAFF_ID_SUCCESS, idToDelete);

        ModelManager expectedModel = new ModelManager(getTypicalPatientist(), new UserPrefs());
        expectedModel.deleteStaff((Staff) personToDelete, expectedModel.getWard("Block A Ward 1"));
        expectedModel.getPatientist().updatePersonList();
        expectedModel.updateFilteredPersonList(new IsStaffPredicate());

        assertCommandSuccess(deleteStaffCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidId_throwCommandException() {
        IdNumber invalidId = new IdNumber("FAK3");
        DeleteStaffCommand deleteStaffCommand = new DeleteStaffCommand(invalidId);
        String expectedMessage = String.format(DeleteStaffCommand.MESSAGE_STAFF_ID_NOT_FOUND, invalidId);

        assertCommandFailure(deleteStaffCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        IdNumber id1 = new IdNumber("test1");
        IdNumber id2 = new IdNumber("test2");
        DeleteStaffCommand deleteStaffCommand1 = new DeleteStaffCommand(id1);
        DeleteStaffCommand deleteStaffCommand2 = new DeleteStaffCommand(id2);
        DeleteStaffCommand deleteStaffCommand3 = new DeleteStaffCommand(id1);
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(id1);

        // same object -> returns true
        assertTrue(deleteStaffCommand1.equals(deleteStaffCommand1));

        // same id number -> returns true
        assertTrue(deleteStaffCommand1.equals(deleteStaffCommand3));

        // different types -> returns false
        assertFalse(deleteStaffCommand2.equals(3));

        // null -> returns false
        assertFalse(deleteStaffCommand1.equals(null));

        // different id number -> returns false
        assertFalse(deleteStaffCommand2.equals(deleteStaffCommand3));
        assertFalse(deleteStaffCommand2.equals(deleteStaffCommand1));

        // same id number different class
        assertFalse(deleteStaffCommand1.equals(deletePatientCommand));
        assertFalse(deleteStaffCommand2.equals(deletePatientCommand));
    }
}
