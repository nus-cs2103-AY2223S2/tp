package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TXN_DESC_COFFEE_MACHINES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TXN_OWNER_COFFEE_MACHINES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TXN_STATUS_COFFEE_MACHINES;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TXN_VALUE_COFFEE_MACHINES;
import static seedu.address.testutil.TypicalTransactions.COFFEE_BEANS;
import static seedu.address.testutil.TypicalTransactions.COFFEE_MACHINES_A;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TransactionBuilder;

class TransactionTest {

    @Test
    void isSameTransaction() {
        assertTrue(COFFEE_MACHINES_A.isSameTransaction(COFFEE_MACHINES_A));

        assertFalse(COFFEE_MACHINES_A.isSameTransaction(null));

        // any field edited = not the same transaction
        Transaction editedTxn = new TransactionBuilder(COFFEE_MACHINES_A).withDesc(VALID_TXN_DESC_COFFEE_MACHINES)
                .build();
        assertFalse(COFFEE_MACHINES_A.isSameTransaction(editedTxn));

        // desc with trailing spaces = return false
        String descWithTrailingSpaces = COFFEE_MACHINES_A.getDescription().toString() + " ";
        Transaction editedCoffeeMachines = new TransactionBuilder(COFFEE_MACHINES_A).withDesc(descWithTrailingSpaces)
                .build();
        assertFalse(COFFEE_MACHINES_A.isSameTransaction(editedCoffeeMachines));
    }

    @Test
    void equals() {
        // same values -> returns true
        Transaction coffeeMachinesACopy = new TransactionBuilder(COFFEE_MACHINES_A).build();
        assertTrue(COFFEE_MACHINES_A.equals(coffeeMachinesACopy));

        // same object -> returns true
        assertTrue(COFFEE_MACHINES_A.equals(COFFEE_MACHINES_A));

        // null -> returns false
        assertFalse(COFFEE_MACHINES_A.equals(null));

        // different type -> returns false
        assertFalse(COFFEE_MACHINES_A.equals(5));

        // different person -> returns false
        assertFalse(COFFEE_MACHINES_A.equals(COFFEE_BEANS));

        // different desc -> returns false
        Transaction editedCoffeeMachinesA = new TransactionBuilder(COFFEE_MACHINES_A)
                .withDesc(VALID_TXN_DESC_COFFEE_MACHINES).build();
        assertFalse(COFFEE_MACHINES_A.equals(editedCoffeeMachinesA));

        // different owner -> returns false
        editedCoffeeMachinesA = new TransactionBuilder(COFFEE_MACHINES_A).withOwner(VALID_TXN_OWNER_COFFEE_MACHINES)
                .build();
        assertFalse(COFFEE_MACHINES_A.equals(editedCoffeeMachinesA));

        // different value -> returns false
        editedCoffeeMachinesA = new TransactionBuilder(COFFEE_MACHINES_A).withValue(VALID_TXN_VALUE_COFFEE_MACHINES)
                .build();
        assertFalse(COFFEE_MACHINES_A.equals(editedCoffeeMachinesA));

        // different status -> returns false
        editedCoffeeMachinesA = new TransactionBuilder(COFFEE_MACHINES_A).withStatus(VALID_TXN_STATUS_COFFEE_MACHINES)
                .build();
        assertFalse(COFFEE_MACHINES_A.equals(editedCoffeeMachinesA));

    }
}
