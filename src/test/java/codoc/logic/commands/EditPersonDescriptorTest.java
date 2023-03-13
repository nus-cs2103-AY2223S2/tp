package codoc.logic.commands;

import static codoc.logic.commands.CommandTestUtil.DESC_AMY;
import static codoc.logic.commands.CommandTestUtil.DESC_BOB;
import static codoc.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static codoc.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static codoc.logic.commands.CommandTestUtil.VALID_GITHUB_BOB;
import static codoc.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static codoc.logic.commands.CommandTestUtil.VALID_SKILL_JAVA;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import codoc.logic.commands.EditCommand.EditPersonDescriptor;
import codoc.testutil.EditPersonDescriptorBuilder;

public class EditPersonDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditPersonDescriptor descriptorWithSameValues = new EditPersonDescriptor(DESC_AMY);
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
        EditPersonDescriptor editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different github -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withGithub(VALID_GITHUB_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different skills -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withSkills(VALID_SKILL_JAVA).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
