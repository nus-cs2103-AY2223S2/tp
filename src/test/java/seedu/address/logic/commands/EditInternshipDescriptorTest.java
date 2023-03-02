package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_APPLE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRONT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditInternshipDescriptor;
import seedu.address.testutil.EditInternshipDescriptorBuilder;

public class EditInternshipDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditInternshipDescriptor descriptorWithSameValues = new EditInternshipDescriptor(DESC_APPLE);
        assertTrue(DESC_APPLE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_APPLE.equals(DESC_APPLE));

        // null -> returns false
        assertFalse(DESC_APPLE.equals(null));

        // different types -> returns false
        assertFalse(DESC_APPLE.equals(5));

        // different values -> returns false
        assertFalse(DESC_APPLE.equals(DESC_GOOGLE));

        // different company name -> returns false
        EditInternshipDescriptor editedApple = new EditInternshipDescriptorBuilder(DESC_APPLE)
                .withCompanyName(VALID_COMPANY_NAME_GOOGLE).build();
        assertFalse(DESC_APPLE.equals(editedApple));

        // different roles -> returns false
        editedApple = new EditInternshipDescriptorBuilder(DESC_APPLE).withRole(VALID_ROLE_GOOGLE).build();
        assertFalse(DESC_APPLE.equals(editedApple));

        // different status -> returns false
        editedApple = new EditInternshipDescriptorBuilder(DESC_APPLE).withStatus(VALID_STATUS_GOOGLE).build();
        assertFalse(DESC_APPLE.equals(editedApple));

        // different date -> returns false
        editedApple = new EditInternshipDescriptorBuilder(DESC_APPLE).withDate(VALID_DATE_GOOGLE).build();
        assertFalse(DESC_APPLE.equals(editedApple));

        // different tags -> returns false
        editedApple = new EditInternshipDescriptorBuilder(DESC_APPLE).withTags(VALID_TAG_FRONT).build();
        assertFalse(DESC_APPLE.equals(editedApple));
    }
}
