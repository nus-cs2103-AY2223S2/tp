package trackr.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_NAME_CHOCOLATE_COOKIES;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_STATUS_NOT_DONE;
import static trackr.testutil.TypicalOrders.CHOCOLATE_COOKIES;

import org.junit.jupiter.api.Test;

import trackr.testutil.OrderBuilder;

public class OrderTest {

    @Test
    public void isSameOrder() {
        // same object -> returns true
        assertTrue(CHOCOLATE_COOKIES.isSameOrder(CHOCOLATE_COOKIES));

        // null -> returns false
        assertFalse(CHOCOLATE_COOKIES.isSameOrder(null));

        // same name, same deadline all other attributes different -> returns true
        Order editedTask = new OrderBuilder(CHOCOLATE_COOKIES)
                .withOrderStatus(VALID_ORDER_STATUS_NOT_DONE).build();
        assertTrue(CHOCOLATE_COOKIES.isSameOrder(editedTask));

        // different name, all other attributes same -> returns false
        editedTask = new OrderBuilder(CHOCOLATE_COOKIES)
                .withOrderName("CHEESE CAKE").build();
        assertFalse(CHOCOLATE_COOKIES.isSameOrder(editedTask));

        // different deadline, all other attributes same -> returns false
        editedTask = new OrderBuilder(CHOCOLATE_COOKIES)
                .withOrderDeadline("02/01/2024").build();
        assertFalse(CHOCOLATE_COOKIES.isSameOrder(editedTask));

        // name differs in case, all other attributes same -> returns false
        Order editedChocolateCookies = new OrderBuilder(CHOCOLATE_COOKIES)
                .withOrderName(VALID_ORDER_NAME_CHOCOLATE_COOKIES.toLowerCase()).build();
        assertFalse(CHOCOLATE_COOKIES.isSameOrder(editedChocolateCookies));

        // name has trailing spaces, all other attributes same -> returns false
        String taskNameWithTrailingSpaces = VALID_ORDER_NAME_CHOCOLATE_COOKIES + " ";
        editedChocolateCookies = new OrderBuilder(CHOCOLATE_COOKIES)
                .withOrderName(taskNameWithTrailingSpaces).build();
        assertFalse(CHOCOLATE_COOKIES.isSameOrder(editedChocolateCookies));
    }

}
