package trackr.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.logic.commands.CommandTestUtil.DESC_BUY_FLOUR;
import static trackr.logic.commands.CommandTestUtil.DESC_SORT_INVENTORY;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_2100;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_NAME_BUY_FLOUR;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_STATUS_DONE;

import org.junit.jupiter.api.Test;

import trackr.model.task.TaskDescriptor;
import trackr.testutil.TaskDescriptorBuilder;

public class EditTaskDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        TaskDescriptor descriptorWithSameValues =
                new TaskDescriptor(DESC_SORT_INVENTORY);
        assertTrue(DESC_SORT_INVENTORY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_SORT_INVENTORY.equals(DESC_SORT_INVENTORY));

        // null -> returns false
        assertFalse(DESC_SORT_INVENTORY.equals(null));

        // different types -> returns false
        assertFalse(DESC_SORT_INVENTORY.equals(5));

        // different values -> returns false
        assertFalse(DESC_SORT_INVENTORY.equals(DESC_BUY_FLOUR));

        // different task name -> returns false
        TaskDescriptor editedSortInventory =
                new TaskDescriptorBuilder(DESC_SORT_INVENTORY)
                        .withTaskName(VALID_TASK_NAME_BUY_FLOUR).build();
        assertFalse(DESC_SORT_INVENTORY.equals(editedSortInventory));

        // different task deadline -> returns false
        editedSortInventory = new TaskDescriptorBuilder(DESC_SORT_INVENTORY)
                .withTaskDeadline(VALID_TASK_DEADLINE_2100).build();
        assertFalse(DESC_SORT_INVENTORY.equals(editedSortInventory));

        // different task status -> returns false
        editedSortInventory = new TaskDescriptorBuilder(DESC_SORT_INVENTORY)
                .withTaskStatus(VALID_TASK_STATUS_DONE).build();
        assertFalse(DESC_SORT_INVENTORY.equals(editedSortInventory));
    }
}
