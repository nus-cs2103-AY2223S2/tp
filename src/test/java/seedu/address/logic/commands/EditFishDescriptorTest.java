package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FEEDING_INTERVAL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LAST_FED_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SPECIES_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TANK_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.fish.FishEditCommand.EditFishDescriptor;
import seedu.address.testutil.EditFishDescriptorBuilder;

public class EditFishDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditFishDescriptor descriptorWithSameValues = new EditFishDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditFishDescriptor editedAmy = new EditFishDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different lastFedDate -> returns false
        editedAmy = new EditFishDescriptorBuilder(DESC_AMY).withLastFedDate(VALID_LAST_FED_DATE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different species -> returns false
        editedAmy = new EditFishDescriptorBuilder(DESC_AMY).withSpecies(VALID_SPECIES_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different feeding interval -> returns false
        editedAmy = new EditFishDescriptorBuilder(DESC_AMY).withFeedingInterval(VALID_FEEDING_INTERVAL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tank -> returns false
        editedAmy = new EditFishDescriptorBuilder(DESC_AMY).withTank(VALID_TANK_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditFishDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
