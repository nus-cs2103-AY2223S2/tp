package seedu.address.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.EDIT_VIDEO_DESC_V1;
import static seedu.address.logic.commands.CommandTestUtil.EDIT_VIDEO_DESC_V2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VIDEO_NAME_V1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VIDEO_NAME_V2;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.edit.EditVideoCommand.EditVideoDescriptor;
import seedu.address.testutil.EditVideoDescriptorBuilder;

public class EditVideoDescriptorTest {

    @Test
    public void isAnyFieldEdited_noEdits_returnsFalse() {
        EditVideoDescriptor descriptor = new EditVideoDescriptor();
        assertFalse(descriptor.isAnyFieldEdited());
    }

    @Test
    public void isAnyFieldEdited_nameIsEdited_returnsTrue() {
        EditVideoDescriptor descriptor = new EditVideoDescriptorBuilder().withName(VALID_VIDEO_NAME_V1).build();
        assertTrue(descriptor.isAnyFieldEdited());
    }

    @Test
    public void equals() {
        // same values -> returns true
        EditVideoDescriptor descriptorWithSameValues = new EditVideoDescriptor(EDIT_VIDEO_DESC_V1);
        assertTrue(EDIT_VIDEO_DESC_V1.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(EDIT_VIDEO_DESC_V1.equals(EDIT_VIDEO_DESC_V1));

        // null -> returns false
        assertFalse(EDIT_VIDEO_DESC_V1.equals(null));

        // different types -> returns false
        assertFalse(EDIT_VIDEO_DESC_V1.equals(1));

        // different values -> returns false
        assertFalse(EDIT_VIDEO_DESC_V1.equals(EDIT_VIDEO_DESC_V2));

        // different name -> returns false
        EditVideoDescriptor editedV1 = new EditVideoDescriptorBuilder(EDIT_VIDEO_DESC_V1)
                .withName(VALID_VIDEO_NAME_V2).build();
        assertFalse(EDIT_VIDEO_DESC_V1.equals(editedV1));
    }

}
