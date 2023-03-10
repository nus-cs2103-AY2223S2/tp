package seedu.vms.logic.commands.patient;

import static seedu.vms.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.vms.testutil.TypicalPatients.getTypicalPatientManager;

import org.junit.jupiter.api.Test;

import seedu.vms.model.Model;
import seedu.vms.model.ModelManager;
import seedu.vms.model.UserPrefs;
import seedu.vms.model.patient.PatientManager;

public class ClearCommandTest {

    @Test
    public void execute_emptyPatientManager_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyPatientManager_success() {
        Model model = new ModelManager(getTypicalPatientManager(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalPatientManager(), new UserPrefs());
        expectedModel.setPatientManager(new PatientManager());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
