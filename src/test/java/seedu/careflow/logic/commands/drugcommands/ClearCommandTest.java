package seedu.careflow.logic.commands.drugcommands;

import static seedu.careflow.logic.commands.drugcommands.DrugCommandTestUtil.assertCommandSuccess;
import static seedu.careflow.testutil.TypicalDrugs.getTypicalDrugInventory;

import org.junit.jupiter.api.Test;

import seedu.careflow.model.CareFlowModel;
import seedu.careflow.model.CareFlowModelManager;
import seedu.careflow.model.DrugInventory;
import seedu.careflow.model.PatientRecord;
import seedu.careflow.model.UserPrefs;

class ClearCommandTest {

    @Test
    public void execute_emptyDrugInventory_success() {
        CareFlowModel model = new CareFlowModelManager();
        CareFlowModel expectedModel = new CareFlowModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyDrugInventory_success() {
        CareFlowModel model = new CareFlowModelManager(new PatientRecord(),
                getTypicalDrugInventory(), new UserPrefs());
        CareFlowModel expectedModel = new CareFlowModelManager(new PatientRecord(),
                getTypicalDrugInventory(), new UserPrefs());
        expectedModel.setDrugInventory(new DrugInventory());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
