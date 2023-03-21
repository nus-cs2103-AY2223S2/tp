package seedu.internship.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internship.logic.commands.CommandTestUtil.DESC_ML1;
import static seedu.internship.logic.commands.CommandTestUtil.DESC_SE1;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_COMPANY_SE1;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_DESCRIPTION_SE1;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_POSITION_SE1;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_STATUS_SE1;
import static seedu.internship.logic.commands.CommandTestUtil.VALID_TAG_FUN;

import org.junit.jupiter.api.Test;

import seedu.internship.logic.commands.EditCommand.EditInternshipDescriptor;
import seedu.internship.testutil.EditInternshipDescriptorBuilder;

public class EditPersonDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditInternshipDescriptor descriptorWithSameValues = new EditInternshipDescriptor(DESC_ML1);
        assertTrue(DESC_ML1.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_ML1.equals(DESC_ML1));

        // null -> returns false
        assertFalse(DESC_ML1.equals(null));

        // different types -> returns false
        assertFalse(DESC_ML1.equals(5));

        // different values -> returns false
        assertFalse(DESC_ML1.equals(DESC_SE1));

        // different name -> returns false
        EditInternshipDescriptor editedML1 = new EditInternshipDescriptorBuilder(DESC_ML1)
                .withPosition(VALID_POSITION_SE1).build();
        assertFalse(DESC_ML1.equals(editedML1));

        // different phone -> returns false
        editedML1 = new EditInternshipDescriptorBuilder(DESC_ML1).withCompany(VALID_COMPANY_SE1).build();
        assertFalse(DESC_ML1.equals(editedML1));

        // different email -> returns false
        editedML1 = new EditInternshipDescriptorBuilder(DESC_ML1).withStatus(VALID_STATUS_SE1).build();
        assertFalse(DESC_ML1.equals(editedML1));

        // different address -> returns false
        editedML1 = new EditInternshipDescriptorBuilder(DESC_ML1).withDescription(VALID_DESCRIPTION_SE1).build();
        assertFalse(DESC_ML1.equals(editedML1));

        // different tags -> returns false
        editedML1 = new EditInternshipDescriptorBuilder(DESC_ML1).withTags(VALID_TAG_FUN).build();
        assertFalse(DESC_ML1.equals(editedML1));
    }
}
