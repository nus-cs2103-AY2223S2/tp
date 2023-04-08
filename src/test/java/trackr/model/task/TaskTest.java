package trackr.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_DEADLINE_2100;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_NAME_BUY_FLOUR;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_NAME_SORT_INVENTORY;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_STATUS_DONE;
import static trackr.testutil.TypicalTasks.BUY_EGGS_D;
import static trackr.testutil.TypicalTasks.BUY_FLOUR_N;
import static trackr.testutil.TypicalTasks.SORT_INVENTORY_N;

import java.util.Objects;

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
        assertEquals(SORT_INVENTORY_N, sortInventoryNCopy);

        // same object -> returns true
        assertEquals(SORT_INVENTORY_N, SORT_INVENTORY_N);

        // null -> returns false
        assertNotEquals(null, SORT_INVENTORY_N);

        // different type -> returns false
        assertNotEquals(5, SORT_INVENTORY_N);

        // different task -> returns false
        assertNotEquals(SORT_INVENTORY_N, BUY_FLOUR_N);

        // different name -> returns false
        Task editedTask = new TaskBuilder(BUY_EGGS_D)
                                  .withTaskName(VALID_TASK_NAME_SORT_INVENTORY).build();
        assertNotEquals(BUY_EGGS_D, editedTask);

        // different deadline -> returns false
        editedTask = new TaskBuilder(SORT_INVENTORY_N)
                             .withTaskDeadline(VALID_TASK_DEADLINE_2100).build();
        assertNotEquals(SORT_INVENTORY_N, editedTask);

        // different status -> returns false
        editedTask = new TaskBuilder(SORT_INVENTORY_N)
                             .withTaskStatus(VALID_TASK_STATUS_DONE).build();
        assertNotEquals(SORT_INVENTORY_N, editedTask);
    }

    @Test
    public void hashCode_success() {
        TaskName taskName = new TaskName("Task");
        TaskDeadline taskDeadline = new TaskDeadline("01/01/2023");
        TaskStatus taskStatus = new TaskStatus("D");

        int hashCode = Objects.hash(taskName, taskDeadline, taskStatus);

        assertEquals(hashCode, new Task(taskName, taskDeadline, taskStatus).hashCode());
    }

}

