package trackr.testutil;

import static trackr.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static trackr.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static trackr.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static trackr.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static trackr.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static trackr.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import trackr.model.order.customer.Customer;

/**
 * Represents a typical customer
 */
public class TypicalCustomer {
    public static final Customer AMY = new CustomerBuilder()
            .withCustomerName(VALID_NAME_AMY)
            .withCustomerPhone(VALID_PHONE_AMY)
            .withCustomerAddress(VALID_ADDRESS_AMY).build();

    public static final Customer BOB = new CustomerBuilder()
            .withCustomerName(VALID_NAME_BOB)
            .withCustomerPhone(VALID_PHONE_BOB)
            .withCustomerAddress(VALID_ADDRESS_BOB).build();

    private TypicalCustomer() {} // prevents instantiation

}
