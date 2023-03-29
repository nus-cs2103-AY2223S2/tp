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
import seedu.patientist.model.person.patient.IsPatientPredicate;
import seedu.patientist.model.person.patient.Patient;

public class DeletePatientCommandTest {
    private Model model = new ModelManager(getTypicalPatientist(), new UserPrefs());

    @Test
    public void execute_validId_success() {
        model.updateFilteredPersonList(new IsPatientPredicate());
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        IdNumber idToDelete = personToDelete.getIdNumber();
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(idToDelete);

        String expectedMessage = String.format(DeletePatientCommand.MESSAGE_DELETE_PATIENT_ID_SUCCESS, idToDelete);

        ModelManager expectedModel = new ModelManager(getTypicalPatientist(), new UserPrefs());
        expectedModel.deletePatient((Patient) personToDelete, expectedModel.getWard("Block A Ward 1"));
        expectedModel.getPatientist().updatePersonList();
        expectedModel.updateFilteredPersonList(new IsPatientPredicate());

        assertCommandSuccess(deletePatientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidId_throwCommandException() {
        IdNumber invalidId = new IdNumber("FAK3");
        DeletePatientCommand deletePatientCommand = new DeletePatientCommand(invalidId);
        String expectedMessage = String.format(DeletePatientCommand.MESSAGE_PATIENT_ID_NOT_FOUND, invalidId);

        assertCommandFailure(deletePatientCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        IdNumber id1 = new IdNumber("test1");
        IdNumber id2 = new IdNumber("test2");
        DeletePatientCommand deletePatientCommand1 = new DeletePatientCommand(id1);
        DeletePatientCommand deletePatientCommand2 = new DeletePatientCommand(id2);
        DeletePatientCommand deletePatientCommand3 = new DeletePatientCommand(id1);
        DeleteStaffCommand deleteStaffCommand = new DeleteStaffCommand(id1);

        // same object -> returns true
        assertTrue(deletePatientCommand1.equals(deletePatientCommand1));

        // same id number -> returns true
        assertTrue(deletePatientCommand1.equals(deletePatientCommand3));

        // different types -> returns false
        assertFalse(deletePatientCommand2.equals(3));

        // null -> returns false
        assertFalse(deletePatientCommand1.equals(null));

        // different id number -> returns false
        assertFalse(deletePatientCommand2.equals(deletePatientCommand3));
        assertFalse(deletePatientCommand2.equals(deletePatientCommand1));

        // same id number different class
        assertFalse(deletePatientCommand1.equals(deleteStaffCommand));
        assertFalse(deletePatientCommand2.equals(deleteStaffCommand));
    }
}

