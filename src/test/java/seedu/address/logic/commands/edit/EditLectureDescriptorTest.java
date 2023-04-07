package seedu.address.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LECTURE_NAME_L1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LECTURE_NAME_L2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CONTENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_EASY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HARD;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.edit.EditLectureCommand.EditLectureDescriptor;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditLectureDescriptorBuilder;
import seedu.address.testutil.ObjectUtil;

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
    public void isAnyFieldEdited_tagsIsEdited_returnsTrue() {
        // single tag
        EditLectureDescriptor descriptor = new EditLectureDescriptorBuilder().withTags(VALID_TAG_CONTENT).build();
        assertTrue(descriptor.isAnyFieldEdited());

        // multiple tags
        descriptor = new EditLectureDescriptorBuilder().withTags(VALID_TAG_CONTENT, VALID_TAG_HARD).build();
        assertTrue(descriptor.isAnyFieldEdited());
    }

    @Test
    public void getTags_modifySet_throwsUnsupportedOperationException() {
        EditLectureDescriptor descriptor = new EditLectureDescriptorBuilder().withTags(VALID_TAG_CONTENT).build();
        assertThrows(UnsupportedOperationException.class, () ->
                descriptor.getTags().get().remove(new Tag(VALID_TAG_CONTENT)));
    }

    @Test
    public void equals() {
        EditLectureDescriptor descriptor = CommandTestUtil.getEditLectureDescriptorL1();

        EditLectureDescriptor descriptorWithSameValues = new EditLectureDescriptor(descriptor);

        EditLectureDescriptor descriptorWithDiffName = new EditLectureDescriptorBuilder(descriptor)
                .withName(VALID_LECTURE_NAME_L2).build();
        EditLectureDescriptor descriptorWithDiffTags = new EditLectureDescriptorBuilder(descriptor)
                .withTags(VALID_TAG_EASY).build();

        ObjectUtil.testEquals(descriptor, descriptorWithSameValues, 1,
                descriptorWithDiffName, descriptorWithDiffTags);
    }

}
