package seedu.fitbook.logic.commands.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.fitbook.logic.commands.CommandTestUtil;
import seedu.fitbook.logic.commands.EditCommand.EditClientDescriptor;
import seedu.fitbook.testutil.client.EditClientDescriptorBuilder;

public class EditClientDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditClientDescriptor descriptorWithSameValues = new EditClientDescriptor(CommandTestUtil.DESC_AMY);
        Assertions.assertTrue(CommandTestUtil.DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        Assertions.assertTrue(CommandTestUtil.DESC_AMY.equals(CommandTestUtil.DESC_AMY));

        // null -> returns false
        Assertions.assertFalse(CommandTestUtil.DESC_AMY.equals(null));

        // different types -> returns false
        Assertions.assertFalse(CommandTestUtil.DESC_AMY.equals(5));

        // different values -> returns false
        Assertions.assertFalse(CommandTestUtil.DESC_AMY.equals(CommandTestUtil.DESC_BOB));

        // different name -> returns false
        EditClientDescriptor editedAmy = new EditClientDescriptorBuilder(CommandTestUtil.DESC_AMY)
                .withName(CommandTestUtil.VALID_NAME_BOB).build();
        Assertions.assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditClientDescriptorBuilder(CommandTestUtil.DESC_AMY)
                .withPhone(CommandTestUtil.VALID_PHONE_BOB).build();
        Assertions.assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditClientDescriptorBuilder(CommandTestUtil.DESC_AMY)
                .withEmail(CommandTestUtil.VALID_EMAIL_BOB).build();
        Assertions.assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditClientDescriptorBuilder(CommandTestUtil.DESC_AMY)
                .withAddress(CommandTestUtil.VALID_ADDRESS_BOB).build();
        Assertions.assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));

        // different weight -> returns false
        editedAmy = new EditClientDescriptorBuilder(CommandTestUtil.DESC_AMY)
                .withWeight(CommandTestUtil.VALID_WEIGHT_BOB).build();
        Assertions.assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));

        // different gender -> returns false
        editedAmy = new EditClientDescriptorBuilder(CommandTestUtil.DESC_AMY)
                .withGender(CommandTestUtil.VALID_GENDER_BOB).build();
        Assertions.assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditClientDescriptorBuilder(CommandTestUtil.DESC_AMY)
                .withTags(CommandTestUtil.VALID_TAG_HUSBAND).build();
        Assertions.assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));
    }
}
