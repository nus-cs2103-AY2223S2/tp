package seedu.loyaltylift.model.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_CUMULATIVE_POINTS_BOB;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_NOTE_BOB;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_POINTS_BOB;
import static seedu.loyaltylift.testutil.TypicalCustomers.ALICE;
import static seedu.loyaltylift.testutil.TypicalCustomers.BOB;

import org.junit.jupiter.api.Test;

import seedu.loyaltylift.testutil.CustomerBuilder;

public class CustomerTest {

    @Test
    public void isSameCustomer() {
        // same object -> returns true
        assertTrue(ALICE.isSameCustomer(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameCustomer(null));

        // same name, all other attributes different -> returns true
        Customer editedAlice = new CustomerBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        assertTrue(ALICE.isSameCustomer(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new CustomerBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameCustomer(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Customer editedBob = new CustomerBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameCustomer(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new CustomerBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameCustomer(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Customer aliceCopy = new CustomerBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different customer -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Customer editedAlice = new CustomerBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new CustomerBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new CustomerBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new CustomerBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different note -> returns false
        editedAlice = new CustomerBuilder(ALICE).withNote(VALID_NOTE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different customer type -> returns false
        editedAlice = new CustomerBuilder(ALICE).withCustomerType(CustomerType.ENTERPRISE).build();
        assertNotEquals(ALICE, editedAlice);

        // different points -> returns false
        editedAlice = new CustomerBuilder(ALICE).withPoints(VALID_POINTS_BOB, VALID_CUMULATIVE_POINTS_BOB).build();
        assertNotEquals(ALICE, editedAlice);
    }
}
