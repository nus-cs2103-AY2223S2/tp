package trackr.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_STATUS_DONE;
import static trackr.testutil.Assert.assertThrows;
import static trackr.testutil.TypicalOrders.CHEESE_CAKES;
import static trackr.testutil.TypicalOrders.CHOCOLATE_COOKIES;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import trackr.model.order.exceptions.DuplicateOrderException;
import trackr.model.order.exceptions.OrderNotFoundException;
import trackr.testutil.OrderBuilder;

public class UniqueOrderListTest {

    private final UniqueOrderList uniqueOrderList = new UniqueOrderList();

    @Test
    public void contains_nullorder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.contains(null));
    }

    @Test
    public void contains_orderNotInList_returnsFalse() {
        assertFalse(uniqueOrderList.contains(CHOCOLATE_COOKIES));
    }

    @Test
    public void contains_orderInList_returnsTrue() {
        uniqueOrderList.add(CHOCOLATE_COOKIES);
        assertTrue(uniqueOrderList.contains(CHOCOLATE_COOKIES));
    }

    @Test
    public void contains_orderWithSameIdentityFieldsInList_returnsTrue() {
        uniqueOrderList.add(CHOCOLATE_COOKIES);
        Order editedOrder = new OrderBuilder(CHOCOLATE_COOKIES)
                .withOrderStatus(VALID_ORDER_STATUS_DONE).build();
        assertTrue(uniqueOrderList.contains(editedOrder));
    }

    @Test
    public void add_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.add(null));
    }

    @Test
    public void add_duplicateOrder_throwsDuplicateOrderException() {
        uniqueOrderList.add(CHOCOLATE_COOKIES);
        assertThrows(DuplicateOrderException.class, () -> uniqueOrderList.add(CHOCOLATE_COOKIES));
    }

    @Test
    public void setOrder_nullTargetOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.setOrder(null, CHOCOLATE_COOKIES));
    }

    @Test
    public void setOrder_nullEditedOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.setOrder(CHOCOLATE_COOKIES, null));
    }

    @Test
    public void setOrder_targetOrderNotInList_throwsOrderNotFoundException() {
        assertThrows(OrderNotFoundException.class, () ->
                uniqueOrderList.setOrder(CHOCOLATE_COOKIES, CHOCOLATE_COOKIES));
    }

    @Test
    public void setOrder_editedOrderIsSameOrder_success() {
        uniqueOrderList.add(CHOCOLATE_COOKIES);
        uniqueOrderList.setOrder(CHOCOLATE_COOKIES, CHOCOLATE_COOKIES);

        UniqueOrderList expectedUniqueOrderList = new UniqueOrderList();
        expectedUniqueOrderList.add(CHOCOLATE_COOKIES);

        assertEquals(expectedUniqueOrderList, uniqueOrderList);
    }

    @Test
    public void setOrder_editedOrderHasSameIdentity_success() {
        uniqueOrderList.add(CHOCOLATE_COOKIES);
        Order editedOrder = new OrderBuilder(CHOCOLATE_COOKIES)
                .withOrderStatus(VALID_ORDER_STATUS_DONE).build();
        uniqueOrderList.setOrder(CHOCOLATE_COOKIES, editedOrder); //change task status

        UniqueOrderList expectedUniqueTaskList = new UniqueOrderList();
        expectedUniqueTaskList.add(editedOrder);

        assertEquals(expectedUniqueTaskList, uniqueOrderList);
    }

    @Test
    public void setOrder_editedOrderHasDifferentIdentity_success() {
        uniqueOrderList.add(CHOCOLATE_COOKIES);
        uniqueOrderList.setOrder(CHOCOLATE_COOKIES, CHEESE_CAKES);

        UniqueOrderList expectedUniqueOrderList = new UniqueOrderList();
        expectedUniqueOrderList.add(CHEESE_CAKES);

        assertEquals(expectedUniqueOrderList, uniqueOrderList);
    }

    @Test
    public void setOrder_editedOrderHasNonUniqueIdentity_throwsDuplicateOrderException() {
        uniqueOrderList.add(CHOCOLATE_COOKIES);
        uniqueOrderList.add(CHEESE_CAKES);
        assertThrows(DuplicateOrderException.class, () -> uniqueOrderList.setOrder(CHOCOLATE_COOKIES, CHEESE_CAKES));
    }

    @Test
    public void remove_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.remove(null));
    }

    @Test
    public void remove_orderDoesNotExist_throwsOrderNotFoundException() {
        assertThrows(OrderNotFoundException.class, () -> uniqueOrderList.remove(CHOCOLATE_COOKIES));
    }

    @Test
    public void remove_existingOrder_removesOrder() {
        uniqueOrderList.add(CHOCOLATE_COOKIES);
        uniqueOrderList.remove(CHOCOLATE_COOKIES);

        UniqueOrderList expectedUniqueOrderList = new UniqueOrderList();

        assertEquals(expectedUniqueOrderList, uniqueOrderList);
    }

    @Test
    public void setOrders_nullUniqueOrderList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.setOrders((UniqueOrderList) null));
    }

    @Test
    public void setOrders_uniqueOrderList_replacesOwnListWithProvidedUniqueOrderList() {
        uniqueOrderList.add(CHOCOLATE_COOKIES);

        UniqueOrderList expectedUniqueTaskList = new UniqueOrderList();
        expectedUniqueTaskList.add(CHEESE_CAKES);

        uniqueOrderList.setOrders(expectedUniqueTaskList);

        assertEquals(expectedUniqueTaskList, uniqueOrderList);
    }

    @Test
    public void setOrders_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.setOrders((List<Order>) null));
    }

    @Test
    public void setOrders_list_replacesOwnListWithProvidedList() {
        uniqueOrderList.add(CHOCOLATE_COOKIES);
        List<Order> orderList = Collections.singletonList(CHEESE_CAKES);
        uniqueOrderList.setOrders(orderList);

        UniqueOrderList expectedUniqueOrderList = new UniqueOrderList();
        expectedUniqueOrderList.add(CHEESE_CAKES);

        assertEquals(expectedUniqueOrderList, uniqueOrderList);
    }

    @Test
    public void setOrders_listWithDuplicateOrders_throwsDuplicateTaskException() {
        List<Order> listWithDuplicateOrders = Arrays.asList(CHOCOLATE_COOKIES, CHOCOLATE_COOKIES);
        assertThrows(DuplicateOrderException.class, () -> uniqueOrderList.setOrders(listWithDuplicateOrders));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueOrderList.asUnmodifiableObservableList().remove(0));
    }

}
