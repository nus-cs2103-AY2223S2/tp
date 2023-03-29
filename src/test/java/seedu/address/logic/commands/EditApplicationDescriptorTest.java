package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.DESC_ONE;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.DESC_TWO;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.VALID_COMPANY_EMAIL_GRAB;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.VALID_COMPANY_NAME_GRAB;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.VALID_ROLE_GRAB;
import static seedu.address.logic.commands.ApplicationCommandTestUtil.VALID_STATUS_GRAB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditApplicationCommand.EditApplicationDescriptor;
import seedu.address.testutil.EditApplicationDescriptorBuilder;

public class EditApplicationDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        EditApplicationDescriptor descriptorWithSameValues = new EditApplicationDescriptor(DESC_ONE);
        assertTrue(DESC_ONE.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_ONE.equals(DESC_ONE));

        // null -> returns false
        assertFalse(DESC_ONE.equals(null));

        // different types -> returns false
        assertFalse(DESC_ONE.equals(5));

        // different values -> returns false
        assertFalse(DESC_ONE.equals(DESC_TWO));

        // different name -> returns false
        EditApplicationDescriptor editedOne = new EditApplicationDescriptorBuilder(DESC_ONE)
                .withCompanyName(VALID_COMPANY_NAME_GRAB).build();
        assertFalse(DESC_ONE.equals(editedOne));

        // different role -> returns false
        editedOne = new EditApplicationDescriptorBuilder(DESC_ONE).withRole(VALID_ROLE_GRAB).build();
        assertFalse(DESC_ONE.equals(editedOne));

        // different email -> returns false
        editedOne = new EditApplicationDescriptorBuilder(DESC_ONE).withCompanyEmail(VALID_COMPANY_EMAIL_GRAB).build();
        assertFalse(DESC_ONE.equals(editedOne));

        // different status -> returns false
        editedOne = new EditApplicationDescriptorBuilder(DESC_ONE).withStatus(VALID_STATUS_GRAB).build();
        assertFalse(DESC_ONE.equals(editedOne));

    }
}
