package trackr.logic.commands.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.logic.commands.CommandTestUtil.assertCommandFailure;
import static trackr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackr.logic.commands.CommandTestUtil.showOrderAtIndex;
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
import trackr.model.order.Order;

//@@author chongweiguan-reused
/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteOrderCommand}.
 */
public class DeleteOrderCommandTest {
    private Model model = new ModelManager(getTypicalSupplierList(), getTypicalTaskList(),
            getTypicalMenu(), getTypicalOrderList(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredOrderList_success() throws ParseException {
        Order orderToDelete = model.getFilteredOrderList().get(INDEX_FIRST_OBJECT.getZeroBased());
        DeleteOrderCommand deleteOrderCommand = new DeleteOrderCommand(INDEX_FIRST_OBJECT);

        String expectedMessage = String.format(DeleteOrderCommand.MESSAGE_DELETE_ITEM_SUCCESS,
                ModelEnum.ORDER,
                orderToDelete);

        ModelManager expectedModel = new ModelManager(model.getSupplierList(), model.getTaskList(),
                model.getMenu(), model.getOrderList(), new UserPrefs());

        expectedModel.deleteItem(orderToDelete, ModelEnum.ORDER);

        assertCommandSuccess(deleteOrderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredOrderList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredOrderList().size() + 1);
        DeleteOrderCommand deleteOrderCommand = new DeleteOrderCommand(outOfBoundIndex);

        assertCommandFailure(deleteOrderCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredOrderList_success() throws ParseException {
        showOrderAtIndex(model, INDEX_FIRST_OBJECT);

        Order orderToDelete = model.getFilteredOrderList().get(INDEX_FIRST_OBJECT.getZeroBased());
        DeleteOrderCommand deleteOrderCommand = new DeleteOrderCommand(INDEX_FIRST_OBJECT);

        String expectedMessage = String.format(DeleteOrderCommand.MESSAGE_DELETE_ITEM_SUCCESS,
                ModelEnum.ORDER,
                orderToDelete);

        Model expectedModel = new ModelManager(model.getSupplierList(), model.getTaskList(),
                model.getMenu(), model.getOrderList(), new UserPrefs());
        expectedModel.deleteItem(orderToDelete, ModelEnum.ORDER);
        showNoOrder(expectedModel);

        assertCommandSuccess(deleteOrderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredOrderList_throwsCommandException() {
        showOrderAtIndex(model, INDEX_FIRST_OBJECT);

        Index outOfBoundIndex = INDEX_SECOND_OBJECT;
        // ensures that outOfBoundIndex is still in bounds of task list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getOrderList().getItemList().size());

        DeleteOrderCommand deleteOrderCommand = new DeleteOrderCommand(outOfBoundIndex);

        assertCommandFailure(deleteOrderCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteOrderCommand deleteOrderFirstCommand = new DeleteOrderCommand(INDEX_FIRST_OBJECT);
        DeleteOrderCommand deleteOrderSecondCommand = new DeleteOrderCommand(INDEX_SECOND_OBJECT);

        // same object -> returns true
        assertTrue(deleteOrderFirstCommand.equals(deleteOrderFirstCommand));

        // same values -> returns true
        DeleteOrderCommand deleteOrderCommandFirstCopy = new DeleteOrderCommand(INDEX_FIRST_OBJECT);
        assertTrue(deleteOrderFirstCommand.equals(deleteOrderCommandFirstCopy));

        // different types -> returns false
        assertFalse(deleteOrderFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteOrderFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(deleteOrderFirstCommand.equals(deleteOrderSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered task list to show no one.
     */
    private void showNoOrder(Model model) {
        model.updateFilteredItemList(p -> false, ModelEnum.ORDER);

        assertTrue(model.getFilteredOrderList().isEmpty());
    }
    //@@author
}
