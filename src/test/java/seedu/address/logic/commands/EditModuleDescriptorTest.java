package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS3219;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS3230;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_CS3219;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CS3219;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RESOURCE_CS3219;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CS3219;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMESLOT_CS3219;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditModuleDescriptor;
import seedu.address.testutil.EditModuleDescriptorBuilder;

public class EditModuleDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditModuleDescriptor descriptorWithSameValues = new EditModuleDescriptor(DESC_CS3230);
        assertTrue(DESC_CS3230.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_CS3230.equals(DESC_CS3230));

        // null -> returns false
        assertFalse(DESC_CS3230.equals(null));

        // different types -> returns false
        assertFalse(DESC_CS3230.equals(5));

        // different values -> returns false
        assertFalse(DESC_CS3230.equals(DESC_CS3219));

        // different name -> returns false
        EditModuleDescriptor editedAmy = new EditModuleDescriptorBuilder(DESC_CS3230)
                .withName(VALID_NAME_CS3219).build();
        assertFalse(DESC_CS3230.equals(editedAmy));

        // different type -> returns false
        editedAmy = new EditModuleDescriptorBuilder(DESC_CS3230).withResource(VALID_RESOURCE_CS3219).build();
        assertFalse(DESC_CS3230.equals(editedAmy));

        // different timeSlot -> returns false
        editedAmy = new EditModuleDescriptorBuilder(DESC_CS3230).withTimeSlot(VALID_TIMESLOT_CS3219).build();
        assertFalse(DESC_CS3230.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditModuleDescriptorBuilder(DESC_CS3230).withAddress(VALID_ADDRESS_CS3219).build();
        assertFalse(DESC_CS3230.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditModuleDescriptorBuilder(DESC_CS3230).withTags(VALID_TAG_CS3219).build();
        assertFalse(DESC_CS3230.equals(editedAmy));
    }
}
