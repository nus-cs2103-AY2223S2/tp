package trackr.model.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_STATUS_DONE;
import static trackr.testutil.Assert.assertThrows;
import static trackr.testutil.TypicalOrders.CHOCOLATE_COOKIES_O;
import static trackr.testutil.TypicalOrders.CUPCAKE_O;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import trackr.model.item.exceptions.DuplicateItemException;
import trackr.model.item.exceptions.ItemNotFoundException;
import trackr.testutil.OrderBuilder;

public class UniqueOrderListTest {

    private final UniqueOrderList uniqueOrderList = new UniqueOrderList();

    @Test
    public void contains_nullorder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.contains(null));
    }

    @Test
    public void contains_orderNotInList_returnsFalse() {
        assertFalse(uniqueOrderList.contains(CHOCOLATE_COOKIES_O));
    }

    @Test
    public void contains_orderInList_returnsTrue() {
        uniqueOrderList.add(CHOCOLATE_COOKIES_O);
        assertTrue(uniqueOrderList.contains(CHOCOLATE_COOKIES_O));
    }

    //    @Test
    //    public void contains_orderWithSameIdentityFieldsInList_returnsTrue() {
    //        uniqueOrderList.add(CHOCOLATE_COOKIES_O);
    //        Order editedOrder = new OrderBuilder(CHOCOLATE_COOKIES_O)
    //                .withOrderStatus(VALID_ORDER_STATUS_DONE).build();
    //        assertTrue(uniqueOrderList.contains(editedOrder));
    //    }

    @Test
    public void add_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.add(null));
    }

    @Test
    public void add_duplicateOrder_throwsDuplicateOrderException() {
        uniqueOrderList.add(CHOCOLATE_COOKIES_O);
        assertThrows(DuplicateItemException.class, () -> uniqueOrderList.add(CHOCOLATE_COOKIES_O));
    }

    @Test
    public void setOrder_nullTargetOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.setItem(null, CHOCOLATE_COOKIES_O));
    }

    @Test
    public void setOrder_nullEditedOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.setItem(CHOCOLATE_COOKIES_O, null));
    }

    @Test
    public void setOrder_targetOrderNotInList_throwsOrderNotFoundException() {
        assertThrows(ItemNotFoundException.class, () ->
                                                          uniqueOrderList.setItem(CHOCOLATE_COOKIES_O,
                                                                  CHOCOLATE_COOKIES_O));
    }

    @Test
    public void setOrder_editedOrderIsSameOrder_success() {
        uniqueOrderList.add(CHOCOLATE_COOKIES_O);
        uniqueOrderList.setItem(CHOCOLATE_COOKIES_O, CHOCOLATE_COOKIES_O);

        UniqueOrderList expectedUniqueOrderList = new UniqueOrderList();
        expectedUniqueOrderList.add(CHOCOLATE_COOKIES_O);

        assertEquals(expectedUniqueOrderList, uniqueOrderList);
    }

    @Test
    public void setOrder_editedOrderHasSameIdentity_success() {
        uniqueOrderList.add(CHOCOLATE_COOKIES_O);
        Order editedOrder = new OrderBuilder(CHOCOLATE_COOKIES_O)
                                    .withOrderStatus(VALID_ORDER_STATUS_DONE).build();
        uniqueOrderList.setItem(CHOCOLATE_COOKIES_O, editedOrder); //change task status

        UniqueOrderList expectedUniqueTaskList = new UniqueOrderList();
        expectedUniqueTaskList.add(editedOrder);

        assertEquals(expectedUniqueTaskList, uniqueOrderList);
    }

    @Test
    public void setOrder_editedOrderHasDifferentIdentity_success() {
        uniqueOrderList.add(CHOCOLATE_COOKIES_O);
        uniqueOrderList.setItem(CHOCOLATE_COOKIES_O, CUPCAKE_O);

        UniqueOrderList expectedUniqueOrderList = new UniqueOrderList();
        expectedUniqueOrderList.add(CUPCAKE_O);

        assertEquals(expectedUniqueOrderList, uniqueOrderList);
    }

    @Test
    public void setOrder_editedOrderHasNonUniqueIdentity_throwsDuplicateOrderException() {
        uniqueOrderList.add(CHOCOLATE_COOKIES_O);
        uniqueOrderList.add(CUPCAKE_O);
        assertThrows(DuplicateItemException.class, () -> uniqueOrderList.setItem(CHOCOLATE_COOKIES_O, CUPCAKE_O));
    }

    @Test
    public void remove_nullOrder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.remove(null));
    }

    @Test
    public void remove_orderDoesNotExist_throwsOrderNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> uniqueOrderList.remove(CHOCOLATE_COOKIES_O));
    }

    @Test
    public void remove_existingOrder_removesOrder() {
        uniqueOrderList.add(CHOCOLATE_COOKIES_O);
        uniqueOrderList.remove(CHOCOLATE_COOKIES_O);

        UniqueOrderList expectedUniqueOrderList = new UniqueOrderList();

        assertEquals(expectedUniqueOrderList, uniqueOrderList);
    }

    @Test
    public void setOrders_nullUniqueOrderList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.setItems((UniqueOrderList) null));
    }

    @Test
    public void setOrders_uniqueOrderList_replacesOwnListWithProvidedUniqueOrderList() {
        uniqueOrderList.add(CHOCOLATE_COOKIES_O);

        UniqueOrderList expectedUniqueTaskList = new UniqueOrderList();
        expectedUniqueTaskList.add(CUPCAKE_O);

        uniqueOrderList.setItems(expectedUniqueTaskList);

        assertEquals(expectedUniqueTaskList, uniqueOrderList);
    }

    @Test
    public void setOrders_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueOrderList.setItems((List<Order>) null));
    }

    @Test
    public void setOrders_list_replacesOwnListWithProvidedList() {
        uniqueOrderList.add(CHOCOLATE_COOKIES_O);
        List<Order> orderList = Collections.singletonList(CUPCAKE_O);
        uniqueOrderList.setItems(orderList);

        UniqueOrderList expectedUniqueOrderList = new UniqueOrderList();
        expectedUniqueOrderList.add(CUPCAKE_O);

        assertEquals(expectedUniqueOrderList, uniqueOrderList);
    }

    @Test
    public void setOrders_listWithDuplicateOrders_throwsDuplicateTaskException() {
        List<Order> listWithDuplicateOrders = Arrays.asList(CHOCOLATE_COOKIES_O, CHOCOLATE_COOKIES_O);
        assertThrows(DuplicateItemException.class, () -> uniqueOrderList.setItems(listWithDuplicateOrders));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                        uniqueOrderList.asUnmodifiableObservableList().remove(0));
    }

}
