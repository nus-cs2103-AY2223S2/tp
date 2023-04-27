package trackr.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_STATUS_DONE;
import static trackr.testutil.Assert.assertThrows;
import static trackr.testutil.TypicalOrders.CHOCOLATE_COOKIES_O;
import static trackr.testutil.TypicalOrders.CUPCAKE_O;
import static trackr.testutil.TypicalOrders.getTypicalOrderList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import trackr.model.item.exceptions.DuplicateItemException;
import trackr.model.order.Order;
import trackr.testutil.OrderBuilder;

public class OrderListTest {

    private final OrderList orderList = new OrderList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), orderList.getItemList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> orderList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyOrderList_replacesData() {
        OrderList newData = getTypicalOrderList();
        orderList.resetData(newData);
        assertEquals(newData, orderList);
    }

    @Test
    public void resetData_withDuplicateOrders_throwsDuplicateOrderException() {
        Order editedOrder = new OrderBuilder(CHOCOLATE_COOKIES_O)
                .withOrderStatus(VALID_ORDER_STATUS_DONE).build();
        List<Order> newOrders = Arrays.asList(CHOCOLATE_COOKIES_O, editedOrder);
        OrderListStub newData = new OrderListStub(newOrders);
        assertThrows(DuplicateItemException.class, () -> orderList.resetData(newData));
    }

    @Test
    public void hasOrder_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> orderList.hasItem(null));
    }

    @Test
    public void hasOrder_orderNotInOrderList_returnsFalse() {
        assertFalse(orderList.hasItem(CHOCOLATE_COOKIES_O));
    }

    @Test
    public void hasOrder_orderInOrderList_returnsTrue() {
        orderList.addItem(CHOCOLATE_COOKIES_O);
        assertTrue(orderList.hasItem(CHOCOLATE_COOKIES_O));
    }

    @Test
    public void hasOrder_orderWithSameIdentityFieldsInOrderList_returnsTrue() {
        orderList.addItem(CHOCOLATE_COOKIES_O);
        Order editedOrder = new OrderBuilder(CHOCOLATE_COOKIES_O)
                .withOrderStatus(VALID_ORDER_STATUS_DONE).build();
        assertTrue(orderList.hasItem(editedOrder));
    }

    @Test
    public void setOrderList_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> orderList.setItem(null, CHOCOLATE_COOKIES_O));
    }

    @Test
    public void setOrderList_nullEditedOrder_throwsNullPointerException() {
        orderList.addItem(CHOCOLATE_COOKIES_O);
        assertThrows(NullPointerException.class, () -> orderList.setItem(CHOCOLATE_COOKIES_O, null));
    }

    @Test
    public void setOrderList_withDuplicateOrders_throwsDuplicateOrderException() {
        orderList.addItem(CHOCOLATE_COOKIES_O);
        orderList.addItem(CUPCAKE_O);
        assertThrows(DuplicateItemException.class, () ->
                orderList.setItem(CUPCAKE_O, CHOCOLATE_COOKIES_O));
    }

    @Test
    public void setOrderList_withDifferentOrder_success() {
        OrderList expectedOrderList = new OrderList();
        expectedOrderList.addItem(CUPCAKE_O);

        orderList.addItem(CHOCOLATE_COOKIES_O);
        orderList.setItem(CHOCOLATE_COOKIES_O, CUPCAKE_O);

        assertEquals(expectedOrderList, orderList);
    }

    @Test
    public void getOrderList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> orderList.getItemList().remove(0));
    }

    //@@author chongweiguan-reused
    @Test
    public void equals() {
        orderList.addItem(CHOCOLATE_COOKIES_O);

        OrderList differentOrderList = new OrderList();
        differentOrderList.addItem(CUPCAKE_O);

        OrderList sameOrderList = new OrderList();
        sameOrderList.addItem(CHOCOLATE_COOKIES_O);

        assertTrue(orderList.equals(orderList)); //same object
        assertTrue(orderList.equals(sameOrderList)); //contains the same tasks

        assertFalse(orderList.equals(null)); //null
        assertFalse(orderList.equals(differentOrderList)); //different task lists
        assertFalse(orderList.equals(1)); //different objects
    }

    private static class OrderListStub implements ReadOnlyOrderList {
        private final ObservableList<Order> orders = FXCollections.observableArrayList();
        OrderListStub(Collection<Order> orders) {
            this.orders.setAll(orders);
        }

        @Override
        public ObservableList<Order> getItemList() {
            return orders;
        }
    }
}
