package trackr.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_2100;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_NAME_BUY_FLOUR;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_NAME_SORT_INVENTORY;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_STATUS_DONE;
import static trackr.testutil.TypicalTasks.BUY_EGGS_D;
import static trackr.testutil.TypicalTasks.BUY_FLOUR_N;
import static trackr.testutil.TypicalTasks.SORT_INVENTORY_N;

import org.junit.jupiter.api.Test;

import trackr.testutil.TaskBuilder;

public class TaskTest {

    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(SORT_INVENTORY_N.isSameItem(SORT_INVENTORY_N));

        // null -> returns false
        assertFalse(SORT_INVENTORY_N.isSameItem(null));

        // same name, same deadline all other attributes different -> returns true
        Task editedTask = new TaskBuilder(SORT_INVENTORY_N)
                .withTaskStatus(VALID_TASK_STATUS_DONE).build();
        assertTrue(SORT_INVENTORY_N.isSameItem(editedTask));

        // different name, all other attributes same -> returns false
        editedTask = new TaskBuilder(SORT_INVENTORY_N)
                .withTaskName(VALID_TASK_NAME_BUY_FLOUR).build();
        assertFalse(SORT_INVENTORY_N.isSameItem(editedTask));

        // different deadline, all other attributes same -> returns false
        editedTask = new TaskBuilder(SORT_INVENTORY_N)
                .withTaskDeadline(VALID_TASK_DEADLINE_2100).build();
        assertFalse(SORT_INVENTORY_N.isSameItem(editedTask));

        // name differs in case, all other attributes same -> returns false
        Task editedSortInventoryN = new TaskBuilder(SORT_INVENTORY_N)
                .withTaskName(VALID_TASK_NAME_SORT_INVENTORY.toLowerCase()).build();
        assertFalse(SORT_INVENTORY_N.isSameItem(editedSortInventoryN));

        // name has trailing spaces, all other attributes same -> returns false
        String taskNameWithTrailingSpaces = VALID_TASK_NAME_SORT_INVENTORY + " ";
        editedSortInventoryN = new TaskBuilder(SORT_INVENTORY_N)
                .withTaskName(taskNameWithTrailingSpaces).build();
        assertFalse(SORT_INVENTORY_N.isSameItem(editedSortInventoryN));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task sortInventoryNCopy = new TaskBuilder(SORT_INVENTORY_N).build();
        assertTrue(SORT_INVENTORY_N.equals(sortInventoryNCopy));

        // same object -> returns true
        assertTrue(SORT_INVENTORY_N.equals(SORT_INVENTORY_N));

        // null -> returns false
        assertFalse(SORT_INVENTORY_N.equals(null));

        // different type -> returns false
        assertFalse(SORT_INVENTORY_N.equals(5));

        // different task -> returns false
        assertFalse(SORT_INVENTORY_N.equals(BUY_FLOUR_N));

        // different name -> returns false
        Task editedTask = new TaskBuilder(BUY_EGGS_D)
                .withTaskName(VALID_TASK_NAME_SORT_INVENTORY).build();
        assertFalse(BUY_EGGS_D.equals(editedTask));

        // different deadline -> returns false
        editedTask = new TaskBuilder(SORT_INVENTORY_N)
                .withTaskDeadline(VALID_TASK_DEADLINE_2100).build();
        assertFalse(SORT_INVENTORY_N.equals(editedTask));

        // different status -> returns false
        editedTask = new TaskBuilder(SORT_INVENTORY_N)
                .withTaskStatus(VALID_TASK_STATUS_DONE).build();
        assertFalse(SORT_INVENTORY_N.equals(editedTask));
    }
}

