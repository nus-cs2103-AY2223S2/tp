package trackr.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.logic.commands.CommandTestUtil.DESC_CHOCO_COOKIE;
import static trackr.logic.commands.CommandTestUtil.DESC_CUPCAKE;
import static trackr.logic.commands.CommandTestUtil.VALID_CUSTOMER_ADDRESS;
import static trackr.logic.commands.CommandTestUtil.VALID_CUSTOMER_NAME;
import static trackr.logic.commands.CommandTestUtil.VALID_CUSTOMER_PHONE;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_NAME_CHOCOLATE_COOKIES;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_QUANTITY_ONE;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_STATUS_DONE;
import static trackr.logic.commands.CommandTestUtil.assertCommandFailure;
import static trackr.logic.commands.CommandTestUtil.assertCommandSuccess;
import static trackr.logic.commands.CommandTestUtil.showOrderAtIndex;
import static trackr.logic.commands.EditOrderCommand.MESSAGE_DUPLICATE_ORDER;
import static trackr.logic.commands.EditOrderCommand.MESSAGE_EDIT_ORDER_SUCCESS;
import static trackr.testutil.TypicalIndexes.INDEX_FIRST_OBJECT;
import static trackr.testutil.TypicalIndexes.INDEX_SECOND_OBJECT;
import static trackr.testutil.TypicalOrders.getTypicalOrderList;
import static trackr.testutil.TypicalSuppliers.getTypicalSupplierList;
import static trackr.testutil.TypicalTasks.getTypicalTaskList;

import org.junit.jupiter.api.Test;

import trackr.commons.core.Messages;
import trackr.commons.core.index.Index;
import trackr.model.Model;
import trackr.model.ModelManager;
import trackr.model.OrderList;
import trackr.model.SupplierList;
import trackr.model.TaskList;
import trackr.model.UserPrefs;
import trackr.model.order.Order;
import trackr.model.order.OrderDescriptor;
import trackr.testutil.OrderBuilder;
import trackr.testutil.OrderDescriptorBuilder;

public class EditOrderCommandTest {

    private Model model = new ModelManager(getTypicalSupplierList(), getTypicalTaskList(),
            getTypicalOrderList(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredOrderList_success() {
        Order editedOrder = new OrderBuilder().build();
        OrderDescriptor descriptor = new OrderDescriptorBuilder(editedOrder).build();
        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_FIRST_OBJECT, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_ORDER_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(new SupplierList(model.getSupplierList()),
                new TaskList(model.getTaskList()), new OrderList(model.getOrderList()), new UserPrefs());

        expectedModel.setOrder(model.getFilteredOrderList().get(0), editedOrder);

        assertCommandSuccess(editOrderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredOrderList_success() {
        Index indexLastOrder = Index.fromOneBased(model.getFilteredOrderList().size());
        Order lastOrder = model.getFilteredOrderList().get(indexLastOrder.getZeroBased());

        OrderBuilder orderInList = new OrderBuilder(lastOrder);
        orderInList.withOrderName(VALID_ORDER_NAME_CHOCOLATE_COOKIES);
        orderInList.withOrderStatus(VALID_ORDER_STATUS_DONE);
        orderInList.withOrderQuantity(VALID_ORDER_QUANTITY_ONE);
        orderInList.withCustomerName(VALID_CUSTOMER_NAME);
        orderInList.withCustomerPhone(VALID_CUSTOMER_PHONE);
        orderInList.withCustomerAddress(VALID_CUSTOMER_ADDRESS);
        Order editedOrder = orderInList.build();

        OrderDescriptor descriptor = new OrderDescriptorBuilder()
                .withOrderName(VALID_ORDER_NAME_CHOCOLATE_COOKIES)
                .withOrderStatus(VALID_ORDER_STATUS_DONE)
                .withOrderQuantity(VALID_ORDER_QUANTITY_ONE)
                .withCustomer(VALID_CUSTOMER_NAME, VALID_CUSTOMER_PHONE, VALID_CUSTOMER_ADDRESS).build();


        EditOrderCommand editOrderCommand = new EditOrderCommand(indexLastOrder, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_ORDER_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(new SupplierList(model.getSupplierList()),
                new TaskList(model.getTaskList()), new OrderList(model.getOrderList()), new UserPrefs());

        expectedModel.setOrder(lastOrder, editedOrder);

        assertCommandSuccess(editOrderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredOrderList_success() {
        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_FIRST_OBJECT, new OrderDescriptor());
        Order editedOrder = model.getFilteredOrderList().get(INDEX_FIRST_OBJECT.getZeroBased());

        String expectedMessage = String.format(MESSAGE_EDIT_ORDER_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(new SupplierList(model.getSupplierList()),
                new TaskList(model.getTaskList()), new OrderList(model.getOrderList()), new UserPrefs());

        assertCommandSuccess(editOrderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredOrderList_success() {
        showOrderAtIndex(model, INDEX_FIRST_OBJECT);

        Order orderInFilteredList = model.getFilteredOrderList().get(INDEX_FIRST_OBJECT.getZeroBased());
        Order editedOrder = new OrderBuilder(orderInFilteredList)
                .withOrderName(VALID_ORDER_NAME_CHOCOLATE_COOKIES).build();
        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_FIRST_OBJECT,
                new OrderDescriptorBuilder().withOrderName(VALID_ORDER_NAME_CHOCOLATE_COOKIES).build());

        String expectedMessage = String.format(MESSAGE_EDIT_ORDER_SUCCESS, editedOrder);

        Model expectedModel = new ModelManager(new SupplierList(model.getSupplierList()),
                new TaskList(model.getTaskList()), new OrderList(model.getOrderList()), new UserPrefs());

        expectedModel.setOrder(model.getFilteredOrderList().get(0), editedOrder);

        assertCommandSuccess(editOrderCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateOrderUnfilteredOrderList_failure() {
        Order firstOrder = model.getFilteredOrderList().get(INDEX_FIRST_OBJECT.getZeroBased());
        OrderDescriptor descriptor = new OrderDescriptorBuilder(firstOrder).build();
        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_SECOND_OBJECT, descriptor);

        assertCommandFailure(editOrderCommand, model, MESSAGE_DUPLICATE_ORDER);
    }

    @Test
    public void execute_duplicateOrderFilteredOrderList_failure() {
        showOrderAtIndex(model, INDEX_FIRST_OBJECT);

        // edit order in filtered order list into a duplicate in order list
        Order orderInList = model.getOrderList().getOrderList().get(INDEX_SECOND_OBJECT.getZeroBased());
        EditOrderCommand editOrderCommand = new EditOrderCommand(INDEX_FIRST_OBJECT,
                new OrderDescriptorBuilder(orderInList).build());

        assertCommandFailure(editOrderCommand, model, MESSAGE_DUPLICATE_ORDER);
    }

    @Test
    public void execute_invalidOrderIndexUnfilteredOrderList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredOrderList().size() + 1);
        OrderDescriptor descriptor = new OrderDescriptorBuilder()
                .withOrderName(VALID_ORDER_NAME_CHOCOLATE_COOKIES).build();
        EditOrderCommand editOrderCommand = new EditOrderCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editOrderCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered order list where index is larger than size of filtered order list,
     * but smaller than size of order list
     */
    @Test
    public void execute_invalidOrderIndexFilteredOrderList_failure() {
        showOrderAtIndex(model, INDEX_FIRST_OBJECT);
        Index outOfBoundIndex = INDEX_SECOND_OBJECT;
        // ensures that outOfBoundIndex is still in bounds of order list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getOrderList().getOrderList().size());

        EditOrderCommand editOrderCommand = new EditOrderCommand(outOfBoundIndex,
                new OrderDescriptorBuilder().withOrderName(VALID_ORDER_NAME_CHOCOLATE_COOKIES).build());

        assertCommandFailure(editOrderCommand, model, Messages.MESSAGE_INVALID_ORDER_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditOrderCommand standardOrderCommand =
                new EditOrderCommand(INDEX_FIRST_OBJECT, DESC_CHOCO_COOKIE);

        // same values -> returns true
        OrderDescriptor copyOrderDescriptor = new OrderDescriptor(DESC_CHOCO_COOKIE);
        EditOrderCommand commandWithSameValues =
                new EditOrderCommand(INDEX_FIRST_OBJECT, copyOrderDescriptor);
        assertTrue(standardOrderCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardOrderCommand.equals(standardOrderCommand));

        // null -> returns false
        assertFalse(standardOrderCommand.equals(null));

        // different types -> returns false
        assertFalse(standardOrderCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardOrderCommand.equals(
                new EditOrderCommand(INDEX_SECOND_OBJECT, DESC_CHOCO_COOKIE)));

        // different descriptor -> returns false
        assertFalse(standardOrderCommand.equals(
                new EditOrderCommand(INDEX_FIRST_OBJECT, DESC_CUPCAKE)));
    }
}



