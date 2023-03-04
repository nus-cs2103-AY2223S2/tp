package trackr.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_NAME_SORT_INVENTORY;
import static trackr.testutil.TypicalTasks.BUY_EGGS_D;
import static trackr.testutil.TypicalTasks.BUY_FLOUR_N;
import static trackr.testutil.TypicalTasks.SORT_INVENTORY_D;
import static trackr.testutil.TypicalTasks.SORT_INVENTORY_DIFF_DATE_D;
import static trackr.testutil.TypicalTasks.SORT_INVENTORY_DIFF_DATE_N;
import static trackr.testutil.TypicalTasks.SORT_INVENTORY_N;

import org.junit.jupiter.api.Test;

import trackr.testutil.TaskBuilder;

public class TaskTest {

    /*@Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Task task = new TaskBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> task.);
    }*/

    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(SORT_INVENTORY_N.isSameTask(SORT_INVENTORY_N));

        // null -> returns false
        assertFalse(SORT_INVENTORY_N.isSameTask(null));

        // same name, same deadline all other attributes different -> returns true
        assertTrue(SORT_INVENTORY_N.isSameTask(SORT_INVENTORY_D));

        // different name, all other attributes same -> returns false
        assertFalse(SORT_INVENTORY_N.isSameTask(SORT_INVENTORY_DIFF_DATE_D));

        // name differs in case, all other attributes same -> returns false
        Task editedSortInventoryN = new TaskBuilder(SORT_INVENTORY_N)
                .withTaskName(VALID_TASK_NAME_SORT_INVENTORY.toLowerCase()).build();
        assertFalse(SORT_INVENTORY_N.isSameTask(editedSortInventoryN));

        // name has trailing spaces, all other attributes same -> returns false
        String taskNameWithTrailingSpaces = VALID_TASK_NAME_SORT_INVENTORY + " ";
        editedSortInventoryN = new TaskBuilder(SORT_INVENTORY_N).withTaskName(taskNameWithTrailingSpaces).build();
        assertFalse(SORT_INVENTORY_N.isSameTask(editedSortInventoryN));
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

        // different person -> returns false
        assertFalse(SORT_INVENTORY_N.equals(BUY_FLOUR_N));

        // different name -> returns false
        Task editedSortInventoryN = new TaskBuilder(BUY_EGGS_D)
                .withTaskName(VALID_TASK_NAME_SORT_INVENTORY).build();
        assertFalse(BUY_EGGS_D.equals(editedSortInventoryN));

        // different deadline -> returns false
        assertFalse(SORT_INVENTORY_N.equals(SORT_INVENTORY_DIFF_DATE_N));

        // different status -> returns false
        assertFalse(SORT_INVENTORY_N.equals(SORT_INVENTORY_D));
    }
}

