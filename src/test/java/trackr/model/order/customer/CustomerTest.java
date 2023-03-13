package trackr.model.order.customer;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static trackr.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static trackr.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static trackr.testutil.TypicalCustomer.AMY;
import static trackr.testutil.TypicalCustomer.BOB;

import org.junit.jupiter.api.Test;

import trackr.testutil.CustomerBuilder;

public class CustomerTest {

    @Test
    public void isSameCustomer() {
        // same object -> returns true
        assertTrue(AMY.isSameCustomer(AMY));

        // null -> returns false
        assertFalse(AMY.isSameCustomer(null));

        Customer editedAmy = new CustomerBuilder(AMY)
                .withCustomerPhone(VALID_PHONE_BOB)
                .withCustomerAddress(VALID_ADDRESS_BOB).build();
        assertTrue(AMY.isSameCustomer(editedAmy));

        // different name, all other attributes same -> returns false
        editedAmy = new CustomerBuilder(AMY).withCustomerName(VALID_NAME_BOB).build();
        assertFalse(AMY.isSameCustomer(editedAmy));

        // name differs in case, all other attributes same -> returns false
        Customer editedBob = new CustomerBuilder(BOB).withCustomerName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameCustomer(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new CustomerBuilder(BOB).withCustomerName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameCustomer(editedBob));
    }

    @Test
    public void equals() {
        Customer aliceCopy = new CustomerBuilder(AMY).build();
        assertTrue(AMY.equals(aliceCopy));

        // same object -> returns true
        assertTrue(AMY.equals(AMY));

        // null -> returns false
        assertFalse(AMY.equals(null));

        // different type -> returns false
        assertFalse(AMY.equals(5));

        // different person -> returns false
        assertFalse(AMY.equals(BOB));

        // different name -> returns false
        Customer editedAlice = new CustomerBuilder(AMY).withCustomerName(VALID_NAME_BOB).build();
        assertFalse(AMY.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new CustomerBuilder(AMY).withCustomerPhone(VALID_PHONE_BOB).build();
        assertFalse(AMY.equals(editedAlice));

        // different address -> returns false
        editedAlice = new CustomerBuilder(AMY).withCustomerAddress(VALID_ADDRESS_BOB).build();
        assertFalse(AMY.equals(editedAlice));
    }

}
