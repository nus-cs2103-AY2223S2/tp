package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_SHOPEE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_SHOPEE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditOpeningDescriptorBuilder;
import seedu.ultron.logic.commands.EditCommand.EditOpeningDescriptor;

public class EditOpeningDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditOpeningDescriptor descriptorWithSameValues = new EditOpeningDescriptor(DESC_GOOGLE);
        assertTrue(DESC_GOOGLE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_GOOGLE.equals(DESC_GOOGLE));

        // null -> returns false
        assertFalse(DESC_GOOGLE.equals(null));

        // different types -> returns false
        assertFalse(DESC_GOOGLE.equals(5));

        // different values -> returns false
        assertFalse(DESC_GOOGLE.equals(DESC_SHOPEE));

        // different position -> returns false
        EditOpeningDescriptor editedGoogle = new EditOpeningDescriptorBuilder(DESC_GOOGLE)
                .withPosition(VALID_POSITION_SHOPEE).build();
        assertFalse(DESC_GOOGLE.equals(editedGoogle));

        // different company -> returns false
        editedGoogle = new EditOpeningDescriptorBuilder(DESC_GOOGLE).withCompany(VALID_COMPANY_SHOPEE).build();
        assertFalse(DESC_GOOGLE.equals(editedGoogle));

        // different email -> returns false
        editedGoogle = new EditOpeningDescriptorBuilder(DESC_GOOGLE).withEmail(VALID_EMAIL_SHOPEE).build();
        assertFalse(DESC_GOOGLE.equals(editedGoogle));

        // different status -> returns false
        editedGoogle = new EditOpeningDescriptorBuilder(DESC_GOOGLE).withStatus(VALID_STATUS_SHOPEE).build();
        assertFalse(DESC_GOOGLE.equals(editedGoogle));
    }
}
