package seedu.vms.logic.commands.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.vms.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.vms.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_ALLERGY_SEAFOOD;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_BLOODTYPE_BOB;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_DOB_BOB;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.vms.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import org.junit.jupiter.api.Test;

import seedu.vms.logic.commands.patient.EditCommand.EditPatientDescriptor;
import seedu.vms.testutil.EditPatientDescriptorBuilder;

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

        // different phone -> returns false
        editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different bloodType -> returns false
        editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withBloodType(VALID_BLOODTYPE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different dob -> returns false
        editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withDob(VALID_DOB_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different allergies -> returns false
        editedAmy = new EditPatientDescriptorBuilder(DESC_AMY).withAllergies(VALID_ALLERGY_SEAFOOD).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
