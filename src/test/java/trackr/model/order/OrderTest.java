package trackr.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_NAME_CHOCOLATE_COOKIES;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_STATUS_NOT_DONE;
import static trackr.testutil.TypicalMenuItems.CUPCAKE_M;
import static trackr.testutil.TypicalOrders.CHOCOLATE_COOKIES_O;
import static trackr.testutil.TypicalOrders.CUPCAKE_O;

import org.junit.jupiter.api.Test;

import trackr.testutil.MenuItemBuilder;
import trackr.testutil.OrderBuilder;

public class OrderTest {

    @Test
    public void isSameOrder() {
        // same object -> returns true
        assertTrue(CHOCOLATE_COOKIES_O.isSameItem(CHOCOLATE_COOKIES_O));

        // null -> returns false
        assertFalse(CHOCOLATE_COOKIES_O.isSameItem(null));

        // same name, same deadline all other attributes different -> returns true
        Order editedOrder = new OrderBuilder(CHOCOLATE_COOKIES_O)
                .withOrderStatus(VALID_ORDER_STATUS_NOT_DONE).build();
        assertTrue(CHOCOLATE_COOKIES_O.isSameItem(editedOrder));

        // different order item, all other attributes same -> returns false
        editedOrder = new OrderBuilder(CHOCOLATE_COOKIES_O)
                .withOrderItem(CUPCAKE_M).build();
        assertFalse(CHOCOLATE_COOKIES_O.isSameItem(editedOrder));

        // different deadline, all other attributes same -> returns false
        editedOrder = new OrderBuilder(CHOCOLATE_COOKIES_O)
                .withOrderDeadline("02/01/2024").build();
        assertFalse(CHOCOLATE_COOKIES_O.isSameItem(editedOrder));

        // name differs in case, all other attributes same -> returns false
        Order editedChocolateCookies = new OrderBuilder(CHOCOLATE_COOKIES_O)
                .withOrderItem(new MenuItemBuilder().withItemName("chocolate cookies").build()).build();
        assertFalse(CHOCOLATE_COOKIES_O.isSameItem(editedChocolateCookies));

        // Order item name has trailing spaces, all other attributes same -> returns false
        String orderItemWithTrailingSpaces = VALID_ORDER_NAME_CHOCOLATE_COOKIES + " ";
        editedChocolateCookies = new OrderBuilder(CHOCOLATE_COOKIES_O)
                .withOrderItem(new MenuItemBuilder().withItemName(orderItemWithTrailingSpaces).build())
                .build();
        assertFalse(CHOCOLATE_COOKIES_O.isSameItem(editedChocolateCookies));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Order aliceCopy = new OrderBuilder(CHOCOLATE_COOKIES_O).build();
        assertTrue(CHOCOLATE_COOKIES_O.equals(aliceCopy));

        // same object -> returns true
        assertTrue(CHOCOLATE_COOKIES_O.equals(CHOCOLATE_COOKIES_O));

        // null -> returns false
        assertFalse(CHOCOLATE_COOKIES_O.equals(null));

        // different type -> returns false
        assertFalse(CHOCOLATE_COOKIES_O.equals(5));

        // different person -> returns false
        assertFalse(CHOCOLATE_COOKIES_O.equals(CUPCAKE_O));

        // different item -> returns false
        Order editedAlice = new OrderBuilder(CHOCOLATE_COOKIES_O).withOrderItem(CUPCAKE_M).build();
        assertFalse(CHOCOLATE_COOKIES_O.equals(editedAlice));

        // different deadline -> returns false
        editedAlice = new OrderBuilder(CHOCOLATE_COOKIES_O).withOrderDeadline("02/02/2025").build();
        assertFalse(CHOCOLATE_COOKIES_O.equals(editedAlice));

        // different email -> returns false
        editedAlice = new OrderBuilder(CHOCOLATE_COOKIES_O).withOrderQuantity("444").build();
        assertFalse(CHOCOLATE_COOKIES_O.equals(editedAlice));

        // different address -> returns false
        editedAlice = new OrderBuilder(CHOCOLATE_COOKIES_O).withOrderStatus("I").build();
        assertFalse(CHOCOLATE_COOKIES_O.equals(editedAlice));

        assertTrue(CHOCOLATE_COOKIES_O.toString().equals(CHOCOLATE_COOKIES_O.toString()));
    }

}
