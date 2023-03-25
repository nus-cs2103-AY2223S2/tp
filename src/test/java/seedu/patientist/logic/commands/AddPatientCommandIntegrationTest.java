package seedu.patientist.logic.commands;

import static seedu.patientist.logic.commands.CommandTestUtil.VALID_WARD_AMY;
import static seedu.patientist.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.patientist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.patientist.testutil.TypicalWards.getTypicalPatientist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.patientist.model.Model;
import seedu.patientist.model.ModelManager;
import seedu.patientist.model.UserPrefs;
import seedu.patientist.model.person.patient.Patient;
import seedu.patientist.testutil.PatientBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddPatientCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPatientist(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Patient validPatient = new PatientBuilder().build();

        Model expectedModel = new ModelManager(getTypicalPatientist(), new UserPrefs());
        expectedModel.addPatient(validPatient, expectedModel.getWard(VALID_WARD_AMY));

        assertCommandSuccess(new AddPatientCommand(VALID_WARD_AMY, validPatient), model,
                String.format(AddPatientCommand.MESSAGE_SUCCESS, validPatient), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Patient personInList = (Patient) model.getPatientist().getPersonList().get(1);
        assertCommandFailure(new AddPatientCommand("Block A Ward 1", personInList), model,
                AddPatientCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
