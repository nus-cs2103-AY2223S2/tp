package seedu.task.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.task.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.task.logic.commands.CommandTestUtil.DESC_AMY_DEADLINE;
import static seedu.task.logic.commands.CommandTestUtil.DESC_AMY_EVENT;
import static seedu.task.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.task.logic.commands.CommandTestUtil.VALID_DATE;
import static seedu.task.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.task.logic.commands.CommandTestUtil.VALID_EFFORT;
import static seedu.task.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.task.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.task.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.task.testutil.EditTaskDescriptorBuilder;

public class EditTaskDescriptorTest {

    @Test
    public void isAnyFieldEdited_none_returnsFalse() {
        EditTaskDescriptor emptyDesc = new EditTaskDescriptor();
        assertFalse(emptyDesc.isAnyFieldEdited());
    }

    @Test
    public void isAnyFieldEdited_changed_returnsTrue() {
        EditTaskDescriptor bobDesc = new EditTaskDescriptor(DESC_BOB);
        assertTrue(bobDesc.isAnyFieldEdited());

        EditTaskDescriptor amyDesc = new EditTaskDescriptor(DESC_AMY);
        assertTrue(amyDesc.isAnyFieldEdited());

        // edit only name -> return True
        EditTaskDescriptor customDesc = new EditTaskDescriptorBuilder()
                .withName("TAN")
                .build();
        assertTrue(customDesc.isAnyFieldEdited());

        // edit only description -> return True
        customDesc = new EditTaskDescriptorBuilder()
                .withDescription("owes money")
                .build();
        assertTrue(customDesc.isAnyFieldEdited());

        // edit only tags -> return True
        customDesc = new EditTaskDescriptorBuilder()
                .withTags("friend")
                .build();
        assertTrue(customDesc.isAnyFieldEdited());

        // edit only effort -> return True
        customDesc = new EditTaskDescriptorBuilder()
                .withEffort(5)
                .build();
        assertTrue(customDesc.isAnyFieldEdited());

        // edit only to date -> return True
        customDesc = new EditTaskDescriptorBuilder()
                .withFrom("2023-03-30 2359")
                .build();
        assertTrue(customDesc.isAnyFieldEdited());

        // edit only from date -> return True
        customDesc = new EditTaskDescriptorBuilder()
                .withTo("2023-03-30 2359")
                .build();
        assertTrue(customDesc.isAnyFieldEdited());

        // edit only deadline -> return True
        customDesc = new EditTaskDescriptorBuilder()
                .withDeadline("2023-03-30 2359")
                .build();
        assertTrue(customDesc.isAnyFieldEdited());
    }

    @Test
    public void equals() {
        // same values -> returns true
        EditTaskDescriptor descriptorWithSameValues = new EditTaskDescriptor(DESC_AMY);
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
        EditTaskDescriptor editedAmy = new EditTaskDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different description -> returns false
        editedAmy = new EditTaskDescriptorBuilder(DESC_AMY).withDescription(VALID_DESCRIPTION_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditTaskDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different effort -> returns false
        editedAmy = new EditTaskDescriptorBuilder(DESC_AMY).withEffort(VALID_EFFORT).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different from date -> returns false
        editedAmy = new EditTaskDescriptorBuilder(DESC_AMY_EVENT).withFrom(VALID_DATE).build();
        assertFalse(DESC_AMY_EVENT.equals(editedAmy));

        // different to date -> returns false
        editedAmy = new EditTaskDescriptorBuilder(DESC_AMY_EVENT).withTo(VALID_DATE).build();
        assertFalse(DESC_AMY_EVENT.equals(editedAmy));

        // different deadline -> returns false
        editedAmy = new EditTaskDescriptorBuilder(DESC_AMY_DEADLINE).withDeadline(VALID_DATE).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }
}
