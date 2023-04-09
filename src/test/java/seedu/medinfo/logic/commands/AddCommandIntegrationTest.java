package seedu.medinfo.logic.commands;

import static seedu.medinfo.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.medinfo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.medinfo.testutil.TypicalPatients.getTypicalMedInfo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.medinfo.logic.commands.exceptions.CommandException;
import seedu.medinfo.model.Model;
import seedu.medinfo.model.ModelManager;
import seedu.medinfo.model.UserPrefs;
import seedu.medinfo.model.patient.Patient;
import seedu.medinfo.testutil.PatientBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalMedInfo(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Patient validPatient = new PatientBuilder().build();

        Model expectedModel = new ModelManager(model.getMedInfo(), new UserPrefs());
        try {
            expectedModel.addPatient(validPatient);
        } catch (CommandException e) {

        }

        assertCommandSuccess(new AddCommand(validPatient), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validPatient), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Patient patientInList = model.getMedInfo().getPatientList().get(0);
        assertCommandFailure(new AddCommand(patientInList), model, AddCommand.MESSAGE_DUPLICATE_PATIENT);
    }

}
