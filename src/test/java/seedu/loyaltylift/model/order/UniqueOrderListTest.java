package seedu.loyaltylift.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.testutil.Assert.assertThrows;
import static seedu.loyaltylift.testutil.TypicalOrders.ORDER_B;
import static seedu.loyaltylift.testutil.TypicalOrders.ORDER_C;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.loyaltylift.model.order.exceptions.DuplicateOrderException;
import seedu.loyaltylift.model.order.exceptions.OrderNotFoundException;
import seedu.loyaltylift.testutil.OrderBuilder;

public class UniqueOrderListTest {

    private final UniqueOrderList uniqueOrderList = new UniqueOrderList();

    @Test
    public void contains_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.contains(null));
    }

    @Test
    public void contains_orderNotInList_returnsFalse() {
        assertFalse(uniqueOrderList.contains(ORDER_C));
    }

    @Test
    public void contains_orderInList_returnsTrue() {
        uniqueOrderList.add(ORDER_C);
        assertTrue(uniqueOrderList.contains(ORDER_C));
    }

    @Test
    public void contains_orderWithSameIdentityFieldsInList_returnsTrue() {
        uniqueOrderList.add(ORDER_C);
        Order cCopy = new OrderBuilder(ORDER_C).build();
        assertTrue(uniqueOrderList.contains(cCopy));
    }

    @Test
    public void add_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.add(null));
    }

    @Test
    public void add_duplicateOrder_throwsDuplicateOrderException() {
        uniqueOrderList.add(ORDER_C);
        assertThrows(DuplicateOrderException.class, () -> uniqueOrderList.add(ORDER_C));
    }

    @Test
    public void setOrder_nullTargetOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.setOrder(null, ORDER_C));
    }

    @Test
    public void setOrder_nullEditedOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.setOrder(ORDER_C, null));
    }

    @Test
    public void setOrder_targetOrderNotInList_throwsOrderNotFoundException() {
        assertThrows(OrderNotFoundException.class, () -> uniqueOrderList.setOrder(ORDER_C, ORDER_C));
    }

    @Test
    public void setOrder_editedOrderIsSameOrder_success() {
        uniqueOrderList.add(ORDER_C);
        uniqueOrderList.setOrder(ORDER_C, ORDER_C);
        UniqueOrderList expectedUniqueOrderList = new UniqueOrderList();
        expectedUniqueOrderList.add(ORDER_C);
        assertEquals(expectedUniqueOrderList, uniqueOrderList);
    }

    @Test
    public void setCustomer_editedOrderHasSameIdentity_success() {
        uniqueOrderList.add(ORDER_C);
        Order cCopy = new OrderBuilder(ORDER_C).build();
        uniqueOrderList.setOrder(ORDER_C, cCopy);
        UniqueOrderList expectedUniqueOrderList = new UniqueOrderList();
        expectedUniqueOrderList.add(cCopy);
        assertEquals(expectedUniqueOrderList, uniqueOrderList);
    }

    @Test
    public void setOrder_editedOrderHasDifferentIdentity_success() {
        uniqueOrderList.add(ORDER_C);
        uniqueOrderList.setOrder(ORDER_C, ORDER_B);
        UniqueOrderList expectedUniqueOrderList = new UniqueOrderList();
        expectedUniqueOrderList.add(ORDER_B);
        assertEquals(expectedUniqueOrderList, uniqueOrderList);
    }

    @Test
    public void setOrder_editedOrderHasNonUniqueIdentity_throwsDuplicateOrderException() {
        uniqueOrderList.add(ORDER_C);
        uniqueOrderList.add(ORDER_B);
        assertThrows(DuplicateOrderException.class, () -> uniqueOrderList.setOrder(ORDER_C, ORDER_B));
    }

    @Test
    public void remove_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.remove(null));
    }

    @Test
    public void remove_orderDoesNotExist_throwsOrderNotFoundException() {
        assertThrows(OrderNotFoundException.class, () -> uniqueOrderList.remove(ORDER_C));
    }

    @Test
    public void remove_existingOrder_removesOrder() {
        uniqueOrderList.add(ORDER_C);
        uniqueOrderList.remove(ORDER_C);
        UniqueOrderList expectedUniqueOrderList = new UniqueOrderList();
        assertEquals(expectedUniqueOrderList, uniqueOrderList);
    }

    @Test
    public void setOrders_nullUniqueOrderList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.setOrders((UniqueOrderList) null));
    }

    @Test
    public void setOrders_uniqueOrderList_replacesOwnListWithProvidedUniqueOrderList() {
        uniqueOrderList.add(ORDER_C);
        UniqueOrderList expectedUniqueOrderList = new UniqueOrderList();
        expectedUniqueOrderList.add(ORDER_B);
        uniqueOrderList.setOrders(expectedUniqueOrderList);
        assertEquals(expectedUniqueOrderList, uniqueOrderList);
    }

    @Test
    public void setOrders_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.setOrders((List<Order>) null));
    }

    @Test
    public void setOrders_list_replacesOwnListWithProvidedList() {
        uniqueOrderList.add(ORDER_C);
        List<Order> orderList = Collections.singletonList(ORDER_B);
        uniqueOrderList.setOrders(orderList);
        UniqueOrderList expectedUniqueOrderList = new UniqueOrderList();
        expectedUniqueOrderList.add(ORDER_B);
        assertEquals(expectedUniqueOrderList, uniqueOrderList);
    }

    @Test
    public void setOrders_listWithDuplicateOrders_throwsDuplicateOrderException() {
        List<Order> listWithDuplicateOrders = Arrays.asList(ORDER_C, ORDER_C);
        assertThrows(
                DuplicateOrderException.class, () -> uniqueOrderList.setOrders(listWithDuplicateOrders));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueOrderList.asUnmodifiableObservableList().remove(0));
    }
}
