package seedu.medinfo.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.medinfo.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.medinfo.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.medinfo.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.medinfo.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.medinfo.logic.commands.CommandTestUtil.VALID_STATUS_BOB;

import org.junit.jupiter.api.Test;

import seedu.medinfo.logic.commands.EditCommand.EditPatientDescriptor;
import seedu.medinfo.testutil.EditPatientDescriptorBuilder;

public class EditPatientDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditPatientDescriptor descriptorWithSameValues = new EditPatientDescriptor(DESC_AMY);
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
        EditPatientDescriptor editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different nric -> returns false
        editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withNric(VALID_NRIC_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different status -> returns false
        editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withStatus(VALID_STATUS_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
