package seedu.careflow.logic.commands.drugcommands;

import static seedu.careflow.logic.commands.drugcommands.DrugCommandTestUtil.assertCommandSuccess;
import static seedu.careflow.logic.commands.drugcommands.DrugCommandTestUtil.showDrugAtIndex;
import static seedu.careflow.testutil.TypicalDrugs.getTypicalDrugInventory;
import static seedu.careflow.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.careflow.model.CareFlowModel;
import seedu.careflow.model.CareFlowModelManager;
import seedu.careflow.model.PatientRecord;
import seedu.careflow.model.UserPrefs;

class ListCommandTest {

    private CareFlowModel model;
    private CareFlowModel expectedModel;

    @BeforeEach
    public void setUp() {
        model = new CareFlowModelManager(new PatientRecord(),
                getTypicalDrugInventory(), new UserPrefs());
        expectedModel = new CareFlowModelManager(new PatientRecord(),
                model.getDrugInventory(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showDrugAtIndex(model, INDEX_FIRST);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
