package seedu.socket.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.socket.logic.commands.CommandTestUtil.EDIT_DESC_AMY;
import static seedu.socket.logic.commands.CommandTestUtil.EDIT_DESC_BOB;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.socket.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.socket.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.socket.testutil.EditPersonDescriptorBuilder;

public class EditPersonDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditPersonDescriptor descriptorWithSameValues = new EditPersonDescriptor(EDIT_DESC_AMY);
        assertTrue(EDIT_DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(EDIT_DESC_AMY.equals(EDIT_DESC_AMY));

        // null -> returns false
        assertFalse(EDIT_DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(EDIT_DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(EDIT_DESC_AMY.equals(EDIT_DESC_BOB));

        // different name -> returns false
        EditPersonDescriptor editedAmy = new EditPersonDescriptorBuilder(EDIT_DESC_AMY)
                .withName(VALID_NAME_BOB).build();
        assertFalse(EDIT_DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditPersonDescriptorBuilder(EDIT_DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(EDIT_DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditPersonDescriptorBuilder(EDIT_DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(EDIT_DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditPersonDescriptorBuilder(EDIT_DESC_AMY).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(EDIT_DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditPersonDescriptorBuilder(EDIT_DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(EDIT_DESC_AMY.equals(editedAmy));
    }
}
