package seedu.address.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.EDIT_MODULE_DESC_CS2040S;
import static seedu.address.logic.commands.CommandTestUtil.EDIT_MODULE_DESC_CS2103;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_2040;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_NAME_2040;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CONTENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_EASY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HARD;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.edit.EditModuleCommand.EditModuleDescriptor;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditModuleDescriptorBuilder;

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
        // same values -> returns true
        EditModuleDescriptor descriptorWithSameValues = new EditModuleDescriptor(EDIT_MODULE_DESC_CS2103);
        assertTrue(EDIT_MODULE_DESC_CS2103.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(EDIT_MODULE_DESC_CS2103.equals(EDIT_MODULE_DESC_CS2103));

        // null -> returns false
        assertFalse(EDIT_MODULE_DESC_CS2103.equals(null));

        // different types -> returns false
        assertFalse(EDIT_MODULE_DESC_CS2103.equals(1));

        // different values -> returns false
        assertFalse(EDIT_MODULE_DESC_CS2103.equals(EDIT_MODULE_DESC_CS2040S));

        // different code -> returns false
        EditModuleDescriptor editedCs2103 = new EditModuleDescriptorBuilder(EDIT_MODULE_DESC_CS2103)
                .withCode(VALID_MODULE_CODE_2040).build();
        assertFalse(EDIT_MODULE_DESC_CS2103.equals(editedCs2103));

        // different name -> returns false
        editedCs2103 = new EditModuleDescriptorBuilder(EDIT_MODULE_DESC_CS2103)
                .withName(VALID_MODULE_NAME_2040).build();
        assertFalse(EDIT_MODULE_DESC_CS2103.equals(editedCs2103));

        // different tags -> returns false
        editedCs2103 = new EditModuleDescriptorBuilder(EDIT_MODULE_DESC_CS2103).withTags(VALID_TAG_EASY).build();
        assertFalse(EDIT_MODULE_DESC_CS2103.equals(editedCs2103));
    }

}
