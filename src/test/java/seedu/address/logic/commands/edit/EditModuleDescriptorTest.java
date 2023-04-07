package seedu.address.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_2040;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_NAME_2040;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CONTENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_EASY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HARD;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.edit.EditModuleCommand.EditModuleDescriptor;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditModuleDescriptorBuilder;
import seedu.address.testutil.ObjectUtil;

public class EditModuleDescriptorTest {

    @Test
    public void isAnyFieldEdited_noEdits_returnsFalse() {
        EditModuleDescriptor descriptor = new EditModuleDescriptor();
        assertFalse(descriptor.isAnyFieldEdited());
    }

    @Test
    public void isAnyFieldEdited_codeIsEdited_returnsTrue() {
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withCode(VALID_MODULE_CODE_2040).build();
        assertTrue(descriptor.isAnyFieldEdited());
    }

    @Test
    public void isAnyFieldEdited_nameIsEdited_returnsTrue() {
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withName(VALID_MODULE_NAME_2040).build();
        assertTrue(descriptor.isAnyFieldEdited());
    }

    @Test
    public void isAnyFieldEdited_tagsIsEdited_returnsTrue() {
        // single tag
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withTags(VALID_TAG_CONTENT).build();
        assertTrue(descriptor.isAnyFieldEdited());

        // multiple tags
        descriptor = new EditModuleDescriptorBuilder().withTags(VALID_TAG_CONTENT, VALID_TAG_HARD).build();
        assertTrue(descriptor.isAnyFieldEdited());
    }

    @Test
    public void getTags_modifySet_throwsUnsupportedOperationException() {
        EditModuleDescriptor descriptor = new EditModuleDescriptorBuilder().withTags(VALID_TAG_CONTENT).build();
        assertThrows(UnsupportedOperationException.class, () ->
                descriptor.getTags().get().remove(new Tag(VALID_TAG_CONTENT)));
    }

    @Test
    public void equals() {
        EditModuleDescriptor descriptor = CommandTestUtil.getEditModuleDescriptorCs2103();

        EditModuleDescriptor descriptorWithSameValues = new EditModuleDescriptor(descriptor);

        EditModuleDescriptor descriptorWithDifferentCode = new EditModuleDescriptorBuilder(descriptor)
                .withCode(VALID_MODULE_CODE_2040).build();
        EditModuleDescriptor descriptorWithDifferentName = new EditModuleDescriptorBuilder(descriptor)
                .withName(VALID_MODULE_NAME_2040).build();
        EditModuleDescriptor descriptorWithDifferentTags = new EditModuleDescriptorBuilder(descriptor)
                .withTags(VALID_TAG_EASY).build();

        ObjectUtil.testEquals(descriptor, descriptorWithSameValues, 1,
                descriptorWithDifferentCode, descriptorWithDifferentName, descriptorWithDifferentTags);
    }

}
