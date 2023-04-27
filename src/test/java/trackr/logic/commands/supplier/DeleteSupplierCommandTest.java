package trackr.logic.commands.supplier;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.logic.commands.CommandTestUtil.assertCommandFailure;
import static trackr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackr.logic.commands.CommandTestUtil.showSupplierAtIndex;
import static trackr.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
import static trackr.testutil.TypicalIndexes.INDEX_SECOND_OBJECT;
import static trackr.testutil.TypicalMenuItems.getTypicalMenu;
import static trackr.testutil.TypicalOrders.getTypicalOrderList;
import static trackr.testutil.TypicalSuppliers.getTypicalSupplierList;
import static trackr.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.Test;

import trackr.commons.core.Messages;
import trackr.commons.core.index.Index;
import trackr.logic.parser.exceptions.ParseException;
import trackr.model.Model;
import trackr.model.ModelEnum;
import trackr.model.ModelManager;
import trackr.model.UserPrefs;
import trackr.model.person.Supplier;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteSupplierCommand}.
 */
//@@author liumc-sg-reused
public class DeleteSupplierCommandTest {

    private Model model = new ModelManager(getTypicalSupplierList(), getTypicalTaskList(),
            getTypicalMenu(), getTypicalOrderList(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() throws ParseException {
        Supplier supplierToDelete = model.getFilteredSupplierList().get(INDEX_FIRST_OBJECT.getZeroBased());
        DeleteSupplierCommand deleteSupplierCommand = new DeleteSupplierCommand(INDEX_FIRST_OBJECT);

        String expectedMessage = String.format(DeleteSupplierCommand.MESSAGE_DELETE_ITEM_SUCCESS,
                ModelEnum.SUPPLIER,
                supplierToDelete);

        ModelManager expectedModel = new ModelManager(model.getSupplierList(), model.getTaskList(),
                model.getMenu(), model.getOrderList(), new UserPrefs());
        expectedModel.deleteItem(supplierToDelete, ModelEnum.SUPPLIER);

        assertCommandSuccess(deleteSupplierCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredSupplierList().size() + 1);
        DeleteSupplierCommand deleteSupplierCommand = new DeleteSupplierCommand(outOfBoundIndex);

        assertCommandFailure(deleteSupplierCommand, model, Messages.MESSAGE_INVALID_SUPPLIER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() throws ParseException {
        showSupplierAtIndex(model, INDEX_FIRST_OBJECT);

        Supplier supplierToDelete = model.getFilteredSupplierList().get(INDEX_FIRST_OBJECT.getZeroBased());
        DeleteSupplierCommand deleteSupplierCommand = new DeleteSupplierCommand(INDEX_FIRST_OBJECT);

        String expectedMessage = String.format(DeleteSupplierCommand.MESSAGE_DELETE_ITEM_SUCCESS,
                ModelEnum.SUPPLIER,
                supplierToDelete);

        ModelManager expectedModel = new ModelManager(model.getSupplierList(), model.getTaskList(),
                model.getMenu(), model.getOrderList(), new UserPrefs());
        expectedModel.deleteItem(supplierToDelete, ModelEnum.SUPPLIER);
        showNoSupplier(expectedModel);

        assertCommandSuccess(deleteSupplierCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showSupplierAtIndex(model, INDEX_FIRST_OBJECT);

        Index outOfBoundIndex = INDEX_SECOND_OBJECT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getSupplierList().getItemList().size());

        DeleteSupplierCommand deleteCommand = new DeleteSupplierCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_SUPPLIER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteSupplierCommand deleteFirstCommand = new DeleteSupplierCommand(INDEX_FIRST_OBJECT);
        DeleteSupplierCommand deleteSecondCommand = new DeleteSupplierCommand(INDEX_SECOND_OBJECT);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteSupplierCommand deleteFirstCommandCopy = new DeleteSupplierCommand(INDEX_FIRST_OBJECT);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different supplier -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoSupplier(Model model) {
        model.updateFilteredItemList(p -> false, ModelEnum.SUPPLIER);

        assertTrue(model.getFilteredSupplierList().isEmpty());
    }
}
