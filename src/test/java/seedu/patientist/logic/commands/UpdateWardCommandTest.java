package seedu.patientist.logic.commands;

import static seedu.patientist.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.patientist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.patientist.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.patientist.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.patientist.testutil.TypicalWards.getTypicalPatientist;

import org.junit.jupiter.api.Test;

import seedu.patientist.commons.core.Messages;
import seedu.patientist.commons.core.index.Index;
import seedu.patientist.model.Model;
import seedu.patientist.model.ModelManager;
import seedu.patientist.model.UserPrefs;
import seedu.patientist.model.person.Person;
import seedu.patientist.model.person.patient.Patient;
import seedu.patientist.model.person.staff.Staff;

public class UpdateWardCommandTest {
    private Model model = new ModelManager(getTypicalPatientist(), new UserPrefs());

    @Test
    public void execute_validIndexList_success() {
        Person personToBeUpdated = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        UpdateWardCommand updatePatientWardCommand = new UpdateWardCommand(INDEX_SECOND_PERSON,
                "Block A Ward 2");

        String expectedMessage = String.format(UpdateWardCommand.MESSAGE_SUCCESS,
                INDEX_SECOND_PERSON.getOneBased(), "Block A Ward 1", "Block A Ward 2");

        ModelManager expectedModel = new ModelManager(getTypicalPatientist(), new UserPrefs());
        expectedModel.transferPatient((Patient) personToBeUpdated,
                expectedModel.getWard("Block A Ward 1"), expectedModel.getWard("Block A Ward 2"));
        expectedModel.getPatientist().updatePersonList();

        assertCommandSuccess(updatePatientWardCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        UpdateWardCommand updatePatientWardCommand = new UpdateWardCommand(outOfBoundIndex,
                "Block A Ward 2");

        assertCommandFailure(updatePatientWardCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidWard_throwsCommandException() {
        Person personToBeUpdated = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        UpdateWardCommand updatePatientWardCommand = new UpdateWardCommand(INDEX_SECOND_PERSON,
                "Block C Ward 1");
        assertCommandFailure(updatePatientWardCommand, model,
                String.format(updatePatientWardCommand.MESSAGE_WARD_NOT_FOUND, "Block C Ward 1"));
    }

    @Test
    public void execute_staff_throwsCommandException() {
        Person personToBeUpdated = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UpdateWardCommand updatePatientWardCommand = new UpdateWardCommand(INDEX_FIRST_PERSON,
                "Block A Ward 2");

        String expectedMessage = String.format(UpdateWardCommand.MESSAGE_STAFF_DETECTED,
                INDEX_FIRST_PERSON.getOneBased(), "Block A Ward 1", "Block A Ward 2");

        ModelManager expectedModel = new ModelManager(getTypicalPatientist(), new UserPrefs());
        expectedModel.transferStaff((Staff) personToBeUpdated,
                expectedModel.getWard("Block A Ward 1"), expectedModel.getWard("Block A Ward 2"));
        expectedModel.getPatientist().updatePersonList();

        assertCommandSuccess(updatePatientWardCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_wrongStaffInWard_throwsCommandException() {
        Person personToBeUpdated = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UpdateWardCommand updatePatientWardCommand = new UpdateWardCommand(INDEX_FIRST_PERSON,
                "Block A Ward 1");
        assertCommandFailure(updatePatientWardCommand, model,
                String.format(updatePatientWardCommand.MESSAGE_WARD_INCORRECT));
    }

    @Test
    public void execute_staffTranfToInvalidWard_throwsCommandException() {
        Person personToBeUpdated = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UpdateWardCommand updatePatientWardCommand = new UpdateWardCommand(INDEX_FIRST_PERSON,
                "Block A Ward 5");
        assertCommandFailure(updatePatientWardCommand, model,
                String.format(updatePatientWardCommand.MESSAGE_WARD_NOT_FOUND, "Block A Ward 5"));
    }

    @Test
    public void execute_sameWard_throwsCommandException() {
        Person personToBeUpdated = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        UpdateWardCommand updatePatientWardCommand = new UpdateWardCommand(INDEX_SECOND_PERSON,
                "Block A Ward 1");
        assertCommandFailure(updatePatientWardCommand, model,
                String.format(updatePatientWardCommand.MESSAGE_WARD_INCORRECT));
    }

    @Test
    public void execute_staffAndWrongStaffInWard_throwsCommandException() {
        Person personToBeUpdated = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UpdateWardCommand updatePatientWardCommand = new UpdateWardCommand(INDEX_FIRST_PERSON,
                "Block A Ward 1");
        assertCommandFailure(updatePatientWardCommand, model,
                String.format(updatePatientWardCommand.MESSAGE_WARD_INCORRECT,
                "Block A Ward 1"));
    }
}
