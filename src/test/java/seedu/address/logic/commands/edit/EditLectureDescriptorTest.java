package seedu.address.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.EDIT_LECTURE_DESC_L1;
import static seedu.address.logic.commands.CommandTestUtil.EDIT_LECTURE_DESC_L2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LECTURE_NAME_L1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LECTURE_NAME_L2;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.edit.EditLectureCommand.EditLectureDescriptor;
import seedu.address.testutil.EditLectureDescriptorBuilder;

public class EditLectureDescriptorTest {

    @Test
    public void isAnyFieldEdited_noEdits_returnsFalse() {
        EditLectureDescriptor descriptor = new EditLectureDescriptor();
        assertFalse(descriptor.isAnyFieldEdited());
    }

    @Test
    public void isAnyFieldEdited_nameIsEdited_returnsTrue() {
        EditLectureDescriptor descriptor = new EditLectureDescriptorBuilder().withName(VALID_LECTURE_NAME_L1).build();
        assertTrue(descriptor.isAnyFieldEdited());
    }

    @Test
    public void equals() {
        // same values -> returns true
        EditLectureDescriptor descriptorWithSameValues = new EditLectureDescriptor(EDIT_LECTURE_DESC_L1);
        assertTrue(EDIT_LECTURE_DESC_L1.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(EDIT_LECTURE_DESC_L1.equals(EDIT_LECTURE_DESC_L1));

        // null -> returns false
        assertFalse(EDIT_LECTURE_DESC_L1.equals(null));

        // different types -> returns false
        assertFalse(EDIT_LECTURE_DESC_L1.equals(1));

        // different values -> returns false
        assertFalse(EDIT_LECTURE_DESC_L1.equals(EDIT_LECTURE_DESC_L2));

        // different name -> returns false
        EditLectureDescriptor editedL1 = new EditLectureDescriptorBuilder(EDIT_LECTURE_DESC_L1)
                .withName(VALID_LECTURE_NAME_L2).build();
        assertFalse(EDIT_LECTURE_DESC_L1.equals(editedL1));
    }

}
