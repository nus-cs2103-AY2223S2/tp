package seedu.careflow.logic.commands.drugcommands;

import static seedu.careflow.logic.commands.drugcommands.DrugCommandTestUtil.assertCommandSuccess;
import static seedu.careflow.logic.commands.drugcommands.DrugCommandTestUtil.assertDrugCommandFailure;
import static seedu.careflow.testutil.Assert.assertThrows;
import static seedu.careflow.testutil.TypicalDrugs.PROZAC;
import static seedu.careflow.testutil.TypicalDrugs.getTypicalDrugInventory;

import org.junit.jupiter.api.Test;

import seedu.careflow.model.CareFlowModel;
import seedu.careflow.model.CareFlowModelManager;
import seedu.careflow.model.DrugInventory;
import seedu.careflow.model.PatientRecord;
import seedu.careflow.model.UserPrefs;
import seedu.careflow.model.drug.Drug;
import seedu.careflow.model.drug.TradeName;
import seedu.careflow.testutil.DrugBuilder;

class UpdateCommandTest {

    private CareFlowModel model = new CareFlowModelManager(new PatientRecord(),
            getTypicalDrugInventory(), new UserPrefs());

    @Test
    public void execute_allfieldSpecified_success() {
        // increase storage count
        String expectedStorageCount = Integer.toString(PROZAC.getStorageCount().getCount() + 10);
        Drug editedDrug = new DrugBuilder(PROZAC).withStorageCount(expectedStorageCount).build();
        UpdateCommand updateCommand = new UpdateCommand(editedDrug.getTradeName(), 10, true);

        String expectedMessage = String.format(UpdateCommand.MESSAGE_SUCCESS,
                editedDrug.getTradeName(), editedDrug.getStorageCount());

        CareFlowModel expectedModel = new CareFlowModelManager(new PatientRecord(),
                new DrugInventory(model.getDrugInventory()), new UserPrefs());
        expectedModel.setDrug(model.getFilteredDrugList().get(0), editedDrug);

        assertCommandSuccess(updateCommand, model, expectedMessage, expectedModel);

        // for decrease storage count
        expectedStorageCount = Integer.toString(PROZAC.getStorageCount().getCount() - 10);
        editedDrug = new DrugBuilder(PROZAC).withStorageCount(expectedStorageCount).build();
        updateCommand = new UpdateCommand(editedDrug.getTradeName(), 10, false);

        expectedMessage = String.format(UpdateCommand.MESSAGE_SUCCESS,
                editedDrug.getTradeName(), editedDrug.getStorageCount());

        expectedModel = new CareFlowModelManager(new PatientRecord(),
                new DrugInventory(model.getDrugInventory()), new UserPrefs());
        expectedModel.setDrug(model.getFilteredDrugList().get(0), editedDrug);

        assertCommandSuccess(updateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldSpecified_throwNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new UpdateCommand(null, 1, true).execute(model));
        assertThrows(NullPointerException.class, () ->
                new UpdateCommand(PROZAC.getTradeName(), null, true).execute(model));
    }

    @Test
    public void execute_invalidDrugTradeName_failure() {
        TradeName invalidTradeName = new TradeName("invalid trade name");
        UpdateCommand updateCommand = new UpdateCommand(invalidTradeName, 10, true);
        String expectedErrorMessage = String.format(updateCommand.MESSAGE_FAILURE, invalidTradeName.tradeName);
        assertDrugCommandFailure(updateCommand, model, expectedErrorMessage);
    }
}
