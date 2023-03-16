package seedu.loyaltylift.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_ADDRESS_B;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_CREATED_DATE_A;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_NAME_B;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_QUANTITY_B;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_STATUS_B;
import static seedu.loyaltylift.testutil.TypicalOrders.ORDER_B;
import static seedu.loyaltylift.testutil.TypicalOrders.ORDER_C;

import org.junit.jupiter.api.Test;

import seedu.loyaltylift.testutil.OrderBuilder;

public class OrderTest {

    @Test
    public void isSameOrder() {
        // same values -> returns true
        Order cCopy = new OrderBuilder(ORDER_C).build();
        assertTrue(ORDER_C.equals(cCopy));

        // same object -> returns true
        assertTrue(ORDER_C.isSameOrder(ORDER_C));

        // null -> returns false
        assertFalse(ORDER_C.isSameOrder(null));

        // different type -> returns false
        assertFalse(ORDER_C.equals(5));

        // different order -> returns false
        assertFalse(ORDER_C.equals(ORDER_B));

        // different name -> returns false
        Order editedC = new OrderBuilder(ORDER_C).withName(VALID_NAME_B).build();
        assertFalse(ORDER_C.equals(editedC));

        // different quantity -> returns false
        editedC = new OrderBuilder(ORDER_C).withQuantity(VALID_QUANTITY_B).build();
        assertFalse(ORDER_C.equals(editedC));

        // different status -> returns false
        editedC = new OrderBuilder(ORDER_C).withStatus(VALID_STATUS_B).build();
        assertFalse(ORDER_C.equals(editedC));

        // different address -> returns false
        editedC = new OrderBuilder(ORDER_C).withAddress(VALID_ADDRESS_B).build();
        assertFalse(ORDER_C.equals(editedC));

        // different created date -> returns false
        editedC = new OrderBuilder(ORDER_C).withCreatedDate(VALID_CREATED_DATE_A).build();
        assertFalse(ORDER_C.equals(editedC));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Order cCopy = new OrderBuilder(ORDER_C).build();
        assertTrue(ORDER_C.equals(cCopy));

        // same object -> returns true
        assertTrue(ORDER_C.equals(ORDER_C));

        // null -> returns false
        assertFalse(ORDER_C.equals(null));

        // different type -> returns false
        assertFalse(ORDER_C.equals(5));

        // different order -> returns false
        assertFalse(ORDER_C.equals(ORDER_B));

        // different name -> returns false
        Order editedC = new OrderBuilder(ORDER_C).withName(VALID_NAME_B).build();
        assertFalse(ORDER_C.equals(editedC));

        // different quantity -> returns false
        editedC = new OrderBuilder(ORDER_C).withQuantity(VALID_QUANTITY_B).build();
        assertFalse(ORDER_C.equals(editedC));

        // different status -> returns false
        editedC = new OrderBuilder(ORDER_C).withStatus(VALID_STATUS_B).build();
        assertFalse(ORDER_C.equals(editedC));

        // different address -> returns false
        editedC = new OrderBuilder(ORDER_C).withAddress(VALID_ADDRESS_B).build();
        assertFalse(ORDER_C.equals(editedC));

        // different created date -> returns false
        editedC = new OrderBuilder(ORDER_C).withCreatedDate(VALID_CREATED_DATE_A).build();
        assertFalse(ORDER_C.equals(editedC));
    }
}
