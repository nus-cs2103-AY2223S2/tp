package seedu.careflow.logic.commands.patientcommands;

import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.assertCommandSuccess;
import static seedu.careflow.testutil.TypicalDrugs.getTypicalDrugInventory;
import static seedu.careflow.testutil.TypicalPatients.getTypicalPatientRecord;

import org.junit.jupiter.api.Test;

import seedu.careflow.model.CareFlowModel;
import seedu.careflow.model.CareFlowModelManager;
import seedu.careflow.model.PatientRecord;
import seedu.careflow.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyCareFlowPatientRecord_success() {
        CareFlowModel model = new CareFlowModelManager();
        CareFlowModel expectedCareFlowModel = new CareFlowModelManager();
        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedCareFlowModel);
    }

    @Test
    public void execute_nonEmptyCareFlowPatientRecord_success() {
        CareFlowModel model = new CareFlowModelManager(getTypicalPatientRecord(), getTypicalDrugInventory(),
                new UserPrefs());
        CareFlowModel expectedCareFlowModel = new CareFlowModelManager(getTypicalPatientRecord(),
                getTypicalDrugInventory(), new UserPrefs());
        expectedCareFlowModel.setPatientRecord(new PatientRecord());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedCareFlowModel);
    }

}
