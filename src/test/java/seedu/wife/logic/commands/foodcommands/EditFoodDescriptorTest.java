package seedu.wife.logic.commands.foodcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.wife.logic.commands.CommandTestUtil.DESC_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.DESC_MEIJI;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_NAME_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_QUANTITY_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_TAG_CHOCOLATE;
import static seedu.wife.logic.commands.CommandTestUtil.VALID_UNIT_CHOCOLATE;

import org.junit.jupiter.api.Test;

import seedu.wife.logic.commands.foodcommands.EditCommand.EditFoodDescriptor;
import seedu.wife.testutil.EditFoodDescriptorBuilder;

public class EditFoodDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditFoodDescriptor descriptorWithSameValues = new EditFoodDescriptor(DESC_MEIJI);
        assertTrue(DESC_MEIJI.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_MEIJI.equals(DESC_MEIJI));

        // null -> returns false
        assertFalse(DESC_MEIJI.equals(null));

        // different types -> returns false
        assertFalse(DESC_MEIJI.equals(5));

        // different values -> returns false
        assertFalse(DESC_MEIJI.equals(DESC_CHOCOLATE));

        // different name -> returns false
        EditFoodDescriptor editedAmy = new EditFoodDescriptorBuilder(DESC_MEIJI).withName(VALID_NAME_CHOCOLATE).build();
        assertFalse(DESC_MEIJI.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditFoodDescriptorBuilder(DESC_MEIJI).withUnit(VALID_UNIT_CHOCOLATE).build();
        assertFalse(DESC_MEIJI.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditFoodDescriptorBuilder(DESC_MEIJI).withQuantity(VALID_QUANTITY_CHOCOLATE).build();
        assertFalse(DESC_MEIJI.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditFoodDescriptorBuilder(DESC_MEIJI).withExpiryDate(VALID_EXPIRY_DATE_CHOCOLATE).build();
        assertFalse(DESC_MEIJI.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditFoodDescriptorBuilder(DESC_MEIJI).withTags(VALID_TAG_CHOCOLATE).build();
        assertFalse(DESC_MEIJI.equals(editedAmy));
    }
}
