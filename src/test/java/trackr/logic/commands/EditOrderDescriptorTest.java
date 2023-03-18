package trackr.logic.commands;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.logic.commands.CommandTestUtil.DESC_CHOCO_COOKIE;
import static trackr.logic.commands.CommandTestUtil.DESC_CUPCAKE;
import static trackr.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static trackr.logic.commands.CommandTestUtil.VALID_CUSTOMER_ADDRESS;
import static trackr.logic.commands.CommandTestUtil.VALID_CUSTOMER_NAME;
import static trackr.logic.commands.CommandTestUtil.VALID_CUSTOMER_PHONE;
import static trackr.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_DEADLINE_2023;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_NAME_CUPCAKES;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_QUANTITY_ONE;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_QUANTITY_TWO;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_STATUS_DONE;
import static trackr.logic.commands.CommandTestUtil.VALID_ORDER_STATUS_NOT_DONE;
import static trackr.logic.commands.CommandTestUtil.VALID_PHONE_AMY;

import org.junit.jupiter.api.Test;

import trackr.model.order.OrderDescriptor;
import trackr.testutil.OrderDescriptorBuilder;

public class EditOrderDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        OrderDescriptor descriptorWithSameValues =
                new OrderDescriptor(DESC_CHOCO_COOKIE);
        assertTrue(DESC_CHOCO_COOKIE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_CHOCO_COOKIE.equals(DESC_CHOCO_COOKIE));

        // null -> returns false
        assertFalse(DESC_CHOCO_COOKIE.equals(null));

        // different types -> returns false
        assertFalse(DESC_CHOCO_COOKIE.equals(5));

        // different values -> returns false
        assertFalse(DESC_CHOCO_COOKIE.equals(DESC_CUPCAKE));

        // different order name -> returns false
        OrderDescriptor editedChocoCookie =
                new OrderDescriptorBuilder(DESC_CHOCO_COOKIE)
                        .withOrderName(VALID_ORDER_NAME_CUPCAKES).build();
        assertFalse(DESC_CHOCO_COOKIE.equals(editedChocoCookie));

        // different order deadline -> returns false
        editedChocoCookie = new OrderDescriptorBuilder(DESC_CHOCO_COOKIE)
                .withOrderDeadline(VALID_ORDER_DEADLINE_2023).build();
        assertFalse(DESC_CHOCO_COOKIE.equals(editedChocoCookie));

        // different order status -> returns false
        editedChocoCookie = new OrderDescriptorBuilder(DESC_CHOCO_COOKIE)
                .withOrderStatus(VALID_ORDER_STATUS_DONE).build();
        assertFalse(DESC_CHOCO_COOKIE.equals(editedChocoCookie));

        // different order quantity -> returns false
        editedChocoCookie = new OrderDescriptorBuilder(DESC_CHOCO_COOKIE)
                .withOrderQuantity(VALID_ORDER_QUANTITY_TWO).build();
        assertFalse(DESC_CHOCO_COOKIE.equals(editedChocoCookie));

        // different customer name -> returns false
        editedChocoCookie = new OrderDescriptorBuilder(DESC_CHOCO_COOKIE)
                .withCustomerName(VALID_NAME_AMY).build();
        assertFalse(DESC_CHOCO_COOKIE.equals(editedChocoCookie));

        // different customer phone -> returns false
        editedChocoCookie = new OrderDescriptorBuilder(DESC_CHOCO_COOKIE)
                .withCustomerPhone(VALID_PHONE_AMY).build();
        assertFalse(DESC_CHOCO_COOKIE.equals(editedChocoCookie));

        // different customer address -> returns false
        editedChocoCookie = new OrderDescriptorBuilder(DESC_CHOCO_COOKIE)
                .withCustomerAddress(VALID_ADDRESS_AMY).build();
        assertFalse(DESC_CHOCO_COOKIE.equals(editedChocoCookie));
    }

}
