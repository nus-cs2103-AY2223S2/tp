package seedu.careflow.logic.commands.patientcommands;

import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.assertCommandSuccess;
import static seedu.careflow.logic.commands.patientcommands.PatientCommandTestUtil.showPatientAtIndex;
import static seedu.careflow.testutil.TypicalDrugs.getTypicalDrugInventory;
import static seedu.careflow.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.careflow.testutil.TypicalPatients.getTypicalPatientRecord;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.careflow.model.CareFlowModel;
import seedu.careflow.model.CareFlowModelManager;
import seedu.careflow.model.UserPrefs;

/**
 * Contains integration tests (interaction with the CareFlowModel) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private CareFlowModel model;
    private CareFlowModel expectedCareFlowModel;

    @BeforeEach
    public void setUp() {
        model = new CareFlowModelManager(getTypicalPatientRecord(), getTypicalDrugInventory(), new UserPrefs());
        expectedCareFlowModel = new CareFlowModelManager(model.getPatientRecord(), model.getDrugInventory(),
                new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedCareFlowModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPatientAtIndex(model, INDEX_FIRST);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedCareFlowModel);
    }
}
