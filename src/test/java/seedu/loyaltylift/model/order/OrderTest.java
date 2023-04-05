package seedu.loyaltylift.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_ADDRESS_B;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_CREATED_DATE_A;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_NAME_B;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_NOTE_A;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_QUANTITY_B;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_STATUS_B_PENDING_DATE;
import static seedu.loyaltylift.testutil.TypicalCustomers.BENSON;
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

        // different name -> returns false
        Order editedCBenson = new OrderBuilder(ORDER_C).withCustomer(BENSON).build();
        assertFalse(ORDER_C.isSameOrder(editedCBenson));

        // different name -> returns false
        Order editedC = new OrderBuilder(ORDER_C).withName(VALID_NAME_B).build();
        assertFalse(ORDER_C.isSameOrder(editedC));

        // different quantity -> returns false
        editedC = new OrderBuilder(ORDER_C).withQuantity(VALID_QUANTITY_B).build();
        assertFalse(ORDER_C.isSameOrder(editedC));

        // different status -> returns true
        editedC = new OrderBuilder(ORDER_C).withInitialStatus(VALID_STATUS_B_PENDING_DATE).build();
        assertTrue(ORDER_C.isSameOrder(editedC));

        // different address -> returns true
        editedC = new OrderBuilder(ORDER_C).withAddress(VALID_ADDRESS_B).build();
        assertTrue(ORDER_C.isSameOrder(editedC));

        // different created date -> returns false
        editedC = new OrderBuilder(ORDER_C).withCreatedDate(VALID_CREATED_DATE_A).build();
        assertFalse(ORDER_C.isSameOrder(editedC));

        // different note -> returns true
        editedC = new OrderBuilder(ORDER_C).withNote(VALID_NOTE_A).build();
        assertTrue(ORDER_C.isSameOrder(editedC));
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
        editedC = new OrderBuilder(ORDER_C).withInitialStatus(VALID_STATUS_B_PENDING_DATE).build();
        assertFalse(ORDER_C.equals(editedC));

        // different address -> returns false
        editedC = new OrderBuilder(ORDER_C).withAddress(VALID_ADDRESS_B).build();
        assertFalse(ORDER_C.equals(editedC));

        // different created date -> returns false
        editedC = new OrderBuilder(ORDER_C).withCreatedDate(VALID_CREATED_DATE_A).build();
        assertFalse(ORDER_C.equals(editedC));

        // different note -> returns false
        editedC = new OrderBuilder(ORDER_C).withNote(VALID_NOTE_A).build();
        assertFalse(ORDER_C.equals(editedC));
    }
}
