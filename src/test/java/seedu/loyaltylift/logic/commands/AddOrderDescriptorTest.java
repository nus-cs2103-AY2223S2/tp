package seedu.loyaltylift.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.DESC_ADD_ORDER_A;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.DESC_ADD_ORDER_B;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_ADDRESS_A;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_NAME_B;
import static seedu.loyaltylift.logic.commands.CommandTestUtil.VALID_QUANTITY_B;
import org.junit.jupiter.api.Test;

import seedu.loyaltylift.logic.commands.AddOrderCommand.AddOrderDescriptor;
import seedu.loyaltylift.testutil.AddOrderDescriptorBuilder;

public class AddOrderDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        AddOrderDescriptor descriptorWithSameValues = new AddOrderDescriptor(DESC_ADD_ORDER_A);
        assertTrue(DESC_ADD_ORDER_A.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_ADD_ORDER_A.equals(DESC_ADD_ORDER_A));

        // null -> returns false
        assertFalse(DESC_ADD_ORDER_A.equals(null));

        // different types -> returns false
        assertFalse(DESC_ADD_ORDER_A.equals(5));

        // different values -> returns false
        assertFalse(DESC_ADD_ORDER_A.equals(DESC_ADD_ORDER_B));

        // different name -> returns false
        AddOrderDescriptor editedOrder = new AddOrderDescriptorBuilder(DESC_ADD_ORDER_A).withName(VALID_NAME_B).build();
        assertFalse(DESC_ADD_ORDER_A.equals(editedOrder));

        // different quantity -> returns false
        editedOrder = new AddOrderDescriptorBuilder(DESC_ADD_ORDER_A).withQuantity(VALID_QUANTITY_B).build();
        assertFalse(DESC_ADD_ORDER_A.equals(editedOrder));

        // different address -> returns false
        editedOrder = new AddOrderDescriptorBuilder(DESC_ADD_ORDER_A).withAddress(VALID_ADDRESS_A).build();
        assertFalse(DESC_ADD_ORDER_A.equals(editedOrder));
    }
}
