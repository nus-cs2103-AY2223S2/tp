package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ELDERLY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_PERSON_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_PERSON_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AGE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REGION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SINGLE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.util.EditPersonDescriptor;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditPersonDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditPersonDescriptor descriptorWithSameValues = new EditPersonDescriptor(DESC_PERSON_AMY);
        assertTrue(DESC_PERSON_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_PERSON_AMY.equals(DESC_PERSON_AMY));

        // null -> returns false
        assertFalse(DESC_PERSON_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_PERSON_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_PERSON_AMY.equals(DESC_PERSON_BOB));

        // different name -> returns false
        EditPersonDescriptor editedAmy = new EditPersonDescriptorBuilder(DESC_PERSON_AMY)
                .withName(VALID_NAME_BOB).build();
        assertFalse(DESC_PERSON_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_PERSON_AMY)
                .withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_PERSON_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_PERSON_AMY)
                .withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_PERSON_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_PERSON_AMY)
                .withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_PERSON_AMY.equals(editedAmy));

        // different nric -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_PERSON_AMY)
                .withNric(VALID_NRIC_BOB).build();
        assertFalse(DESC_PERSON_AMY.equals(editedAmy));

        // different age -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_PERSON_AMY)
                .withAge(VALID_AGE_BOB).build();
        assertFalse(DESC_PERSON_AMY.equals(editedAmy));

        // different region -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_PERSON_AMY)
                .withRegion(VALID_REGION_BOB).build();
        assertFalse(DESC_ELDERLY_AMY.equals(editedAmy));


        // different tags -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_PERSON_AMY)
                .withTags(VALID_TAG_SINGLE).build();
        assertFalse(DESC_PERSON_AMY.equals(editedAmy));
    }
}
