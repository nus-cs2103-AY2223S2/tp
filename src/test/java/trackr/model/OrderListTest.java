package trackr.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_STATUS_DONE;
import static trackr.testutil.Assert.assertThrows;
import static trackr.testutil.TypicalOrders.CHEESE_CAKES;
import static trackr.testutil.TypicalOrders.CHOCOLATE_COOKIES;
import static trackr.testutil.TypicalOrders.getTypicalOrderList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import trackr.model.order.Order;
import trackr.model.order.exceptions.DuplicateOrderException;
import trackr.testutil.OrderBuilder;

public class OrderListTest {

    private final OrderList orderList = new OrderList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), orderList.getOrderList());
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
        Order editedOrder = new OrderBuilder(CHOCOLATE_COOKIES)
                .withOrderStatus(VALID_ORDER_STATUS_DONE).build();
        List<Order> newOrders = Arrays.asList(CHOCOLATE_COOKIES, editedOrder);
        OrderListStub newData = new OrderListStub(newOrders);

        assertThrows(DuplicateOrderException.class, () -> orderList.resetData(newData));
    }

    @Test
    public void hasOrder_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> orderList.hasOrder(null));
    }

    @Test
    public void hasOrder_orderNotInOrderList_returnsFalse() {
        assertFalse(orderList.hasOrder(CHOCOLATE_COOKIES));
    }

    @Test
    public void hasOrder_orderInOrderList_returnsTrue() {
        orderList.addOrder(CHOCOLATE_COOKIES);
        assertTrue(orderList.hasOrder(CHOCOLATE_COOKIES));
    }

    @Test
    public void hasOrder_orderWithSameIdentityFieldsInOrderList_returnsTrue() {
        orderList.addOrder(CHOCOLATE_COOKIES);
        Order editedOrder = new OrderBuilder(CHOCOLATE_COOKIES)
                .withOrderStatus(VALID_ORDER_STATUS_DONE).build();
        assertTrue(orderList.hasOrder(editedOrder));
    }

    @Test
    public void setOrderList_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> orderList.setOrder(null, CHOCOLATE_COOKIES));
    }

    @Test
    public void setOrderList_nullEditedOrder_throwsNullPointerException() {
        orderList.addOrder(CHOCOLATE_COOKIES);
        assertThrows(NullPointerException.class, () -> orderList.setOrder(CHOCOLATE_COOKIES, null));
    }

    @Test
    public void setOrderList_withDuplicateOrders_throwsDuplicateOrderException() {
        orderList.addOrder(CHOCOLATE_COOKIES);
        orderList.addOrder(CHEESE_CAKES);
        assertThrows(DuplicateOrderException.class, () ->
                orderList.setOrder(CHEESE_CAKES, CHOCOLATE_COOKIES));
    }

    @Test
    public void setOrderList_withDifferentOrder_success() {
        OrderList expectedOrderList = new OrderList();
        expectedOrderList.addOrder(CHEESE_CAKES);

        orderList.addOrder(CHOCOLATE_COOKIES);
        orderList.setOrder(CHOCOLATE_COOKIES, CHEESE_CAKES);

        assertEquals(expectedOrderList, orderList);
    }

    @Test
    public void getOrderList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> orderList.getOrderList().remove(0));
    }

    @Test
    public void equals() {
        orderList.addOrder(CHOCOLATE_COOKIES);

        OrderList differentOrderList = new OrderList();
        differentOrderList.addOrder(CHEESE_CAKES);

        OrderList sameOrderList = new OrderList();
        sameOrderList.addOrder(CHOCOLATE_COOKIES);

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
        public ObservableList<Order> getOrderList() {
            return orders;
        }
    }

}
