package t154.CLIpboard.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static t154.CLIpboard.logic.commands.CommandTestUtil.DESC_AMY;
import static t154.CLIpboard.logic.commands.CommandTestUtil.DESC_BOB;
import static t154.CLIpboard.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static t154.CLIpboard.logic.commands.CommandTestUtil.VALID_MODULE_CS2105;
import static t154.CLIpboard.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static t154.CLIpboard.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static t154.CLIpboard.logic.commands.CommandTestUtil.VALID_STUDENTID_BOB;

import org.junit.jupiter.api.Test;

import t154.CLIpboard.logic.commands.EditCommand.EditStudentDescriptor;
import t154.CLIpboard.testutil.EditStudentDescriptorBuilder;

public class EditStudentDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditStudentDescriptor descriptorWithSameValues = new EditStudentDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditStudentDescriptor editedAmy = new EditStudentDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditStudentDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditStudentDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditStudentDescriptorBuilder(DESC_AMY).withStudentId(VALID_STUDENTID_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditStudentDescriptorBuilder(DESC_AMY).withTags(VALID_MODULE_CS2105).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
