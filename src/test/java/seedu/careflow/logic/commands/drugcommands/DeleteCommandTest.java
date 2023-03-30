package seedu.careflow.logic.commands.drugcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.careflow.logic.commands.drugcommands.DrugCommandTestUtil.assertCommandSuccess;
import static seedu.careflow.logic.commands.drugcommands.DrugCommandTestUtil.assertDrugCommandFailure;
import static seedu.careflow.logic.commands.drugcommands.DrugCommandTestUtil.showDrugAtIndex;
import static seedu.careflow.testutil.TypicalDrugs.getTypicalDrugInventory;
import static seedu.careflow.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.careflow.testutil.TypicalIndexes.INDEX_SECOND;

import org.junit.jupiter.api.Test;

import seedu.careflow.commons.core.Messages;
import seedu.careflow.commons.core.index.Index;
import seedu.careflow.model.CareFlowModel;
import seedu.careflow.model.CareFlowModelManager;
import seedu.careflow.model.PatientRecord;
import seedu.careflow.model.UserPrefs;
import seedu.careflow.model.drug.Drug;

class DeleteCommandTest {

    private CareFlowModel model = new CareFlowModelManager(new PatientRecord(),
            getTypicalDrugInventory(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Drug drugToDelete = model.getFilteredDrugList().get(INDEX_FIRST.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_SUCCESS, drugToDelete);

        CareFlowModelManager expectedModel = new CareFlowModelManager(new PatientRecord(),
                model.getDrugInventory(), new UserPrefs());
        expectedModel.deleteDrug(drugToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredDrugList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertDrugCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_DRUG_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showDrugAtIndex(model, INDEX_FIRST);

        Drug drugToDelete = model.getFilteredDrugList().get(INDEX_FIRST.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_SUCCESS, drugToDelete);

        CareFlowModel expectedModel = new CareFlowModelManager(new PatientRecord(),
                model.getDrugInventory(), new UserPrefs());
        expectedModel.deleteDrug(drugToDelete);
        showNoDrug(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showDrugAtIndex(model, INDEX_FIRST);

        Index outOfBoundIndex = INDEX_SECOND;
        // ensures that outOfBoundIndex is still in bounds of drug inventory list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getDrugInventory().getDrugList().size());

        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertDrugCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_DRUG_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code CareFlowModel}'s filtered list to show no one.
     */
    private void showNoDrug(CareFlowModel model) {
        model.updateFilteredDrugList(p -> false);

        assertTrue(model.getFilteredDrugList().isEmpty());
    }
}
