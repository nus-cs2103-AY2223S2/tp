package seedu.address.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CONTENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_EASY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HARD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VIDEO_NAME_V1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VIDEO_NAME_V2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VIDEO_TIMESTAMP_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VIDEO_TIMESTAMP_2;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.edit.EditVideoCommand.EditVideoDescriptor;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditVideoDescriptorBuilder;
import seedu.address.testutil.ObjectUtil;

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
    public void isAnyFieldEdited_timestampIsEdited_returnsTrue() {
        EditVideoDescriptor descriptor = new EditVideoDescriptorBuilder()
                .withTimestamp(VALID_VIDEO_TIMESTAMP_1).build();
        assertTrue(descriptor.isAnyFieldEdited());
    }

    @Test
    public void isAnyFieldEdited_watchedIsEdited_returnsTrue() {
        // watched is edited to true
        EditVideoDescriptor descriptor = new EditVideoDescriptorBuilder().withWatched(true).build();
        assertTrue(descriptor.isAnyFieldEdited());

        // watched is edited to false
        descriptor = new EditVideoDescriptorBuilder().withWatched(false).build();
        assertTrue(descriptor.isAnyFieldEdited());
    }

    @Test
    public void isAnyFieldEdited_tagsIsEdited_returnsTrue() {
        // single tag
        EditVideoDescriptor descriptor = new EditVideoDescriptorBuilder().withTags(VALID_TAG_CONTENT).build();
        assertTrue(descriptor.isAnyFieldEdited());

        // multiple tags
        descriptor = new EditVideoDescriptorBuilder().withTags(VALID_TAG_CONTENT, VALID_TAG_HARD).build();
        assertTrue(descriptor.isAnyFieldEdited());
    }

    @Test
    public void getTags_modifySet_throwsUnsupportedOperationException() {
        EditVideoDescriptor descriptor = new EditVideoDescriptorBuilder().withTags(VALID_TAG_CONTENT).build();
        assertThrows(UnsupportedOperationException.class, () ->
                descriptor.getTags().get().remove(new Tag(VALID_TAG_CONTENT)));
    }

    @Test
    public void equals() {
        EditVideoDescriptor descriptor = CommandTestUtil.getEditVideoDescriptorV1();

        EditVideoDescriptor descriptorWithSameValues = new EditVideoDescriptor(descriptor);

        EditVideoDescriptor descriptorWithDiffName = new EditVideoDescriptorBuilder(descriptor)
                .withName(VALID_VIDEO_NAME_V2).build();
        EditVideoDescriptor descriptorWithDiffTimestamp = new EditVideoDescriptorBuilder(descriptor)
                .withTimestamp(VALID_VIDEO_TIMESTAMP_2).build();
        EditVideoDescriptor descriptorWithDiffWatched = new EditVideoDescriptorBuilder(descriptor)
                .withWatched(!descriptor.hasWatched().get()).build();
        EditVideoDescriptor descriptorWithDiffTags = new EditVideoDescriptorBuilder(descriptor)
                .withTags(VALID_TAG_EASY).build();

        ObjectUtil.testEquals(descriptor, descriptorWithSameValues, 1,
                descriptorWithDiffName, descriptorWithDiffTimestamp, descriptorWithDiffWatched, descriptorWithDiffTags);
    }

}
