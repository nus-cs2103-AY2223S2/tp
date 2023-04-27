package trackr.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_STATUS_DONE;
import static trackr.testutil.Assert.assertThrows;
import static trackr.testutil.TypicalTasks.BUY_FLOUR_N;
import static trackr.testutil.TypicalTasks.SORT_INVENTORY_N;
import static trackr.testutil.TypicalTasks.getTypicalTaskList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import trackr.model.item.exceptions.DuplicateItemException;
import trackr.model.task.Task;
import trackr.testutil.TaskBuilder;

public class TaskListTest {

    //@@author HmuuMyatMoe-reused
    //Reused from AB3 with minor modifications
    private final TaskList taskList = new TaskList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), taskList.getItemList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTaskList_replacesData() {
        TaskList newData = getTypicalTaskList();
        taskList.resetData(newData);
        assertEquals(newData, taskList);
    }

    @Test
    public void resetData_withDuplicateTasks_throwsDuplicateTaskException() {
        // Two tasks with the same identity fields
        Task editedTask = new TaskBuilder(SORT_INVENTORY_N)
                .withTaskStatus(VALID_TASK_STATUS_DONE).build();
        List<Task> newTasks = Arrays.asList(SORT_INVENTORY_N, editedTask);
        TaskListStub newData = new TaskListStub(newTasks);

        assertThrows(DuplicateItemException.class, () -> taskList.resetData(newData));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.hasItem(null));
    }
    //@@author

    @Test
    public void hasTask_taskNotInTaskList_returnsFalse() {
        assertFalse(taskList.hasItem(SORT_INVENTORY_N));
    }

    //@@author liumc-sg-reused
    @Test
    public void hasTask_taskInTaskList_returnsTrue() {
        taskList.addItem(SORT_INVENTORY_N);
        assertTrue(taskList.hasItem(SORT_INVENTORY_N));
    }
    //@@author

    //@@author HmuuMyatMoe-reused
    //Reused from AB3 with minor modifications
    @Test
    public void hasTask_taskWithSameIdentityFieldsInTaskList_returnsTrue() {
        taskList.addItem(SORT_INVENTORY_N);
        Task editedTask = new TaskBuilder(SORT_INVENTORY_N)
                .withTaskStatus(VALID_TASK_STATUS_DONE).build();
        assertTrue(taskList.hasItem(editedTask)); //different status
    }
    //@@author

    //@@author liumc-sg-reused
    @Test
    public void setTaskList_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setItem(null, SORT_INVENTORY_N));
    }

    @Test
    public void setTaskList_nullEditedTask_throwsNullPointerException() {
        taskList.addItem(SORT_INVENTORY_N);
        assertThrows(NullPointerException.class, () -> taskList.setItem(SORT_INVENTORY_N, null));
    }

    @Test
    public void setTaskList_withDuplicateTasks_throwsDuplicateTaskException() {
        taskList.addItem(SORT_INVENTORY_N);
        taskList.addItem(BUY_FLOUR_N);
        assertThrows(DuplicateItemException.class, () -> taskList.setItem(BUY_FLOUR_N, SORT_INVENTORY_N));
    }
    //@@author

    //@@author HmuuMyatMoe-reused
    //Reused from AB3 with minor modifications
    @Test
    public void setTaskList_withDifferentTask_success() {
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.addItem(BUY_FLOUR_N);

        taskList.addItem(SORT_INVENTORY_N);
        taskList.setItem(SORT_INVENTORY_N, BUY_FLOUR_N);

        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void getTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> taskList.getItemList().remove(0));
    }
    //@@author

    @Test
    public void equals() {
        taskList.addItem(SORT_INVENTORY_N);

        TaskList differentTaskList = new TaskList();
        differentTaskList.addItem(BUY_FLOUR_N);

        TaskList sameTaskList = new TaskList();
        sameTaskList.addItem(SORT_INVENTORY_N);

        assertTrue(taskList.equals(taskList)); //same object
        assertTrue(taskList.equals(sameTaskList)); //contains the same tasks

        assertFalse(taskList.equals(null)); //null
        assertFalse(taskList.equals(differentTaskList)); //different task lists
        assertFalse(taskList.equals(1)); //different objects
    }

    //@@author HmuuMyatMoe-reused
    //Reused from AB3 with minor modifications
    /**
     * A stub ReadOnlyTaskList whose tasks list can violate interface constraints.
     */
    private static class TaskListStub implements ReadOnlyTaskList {
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        TaskListStub(Collection<Task> tasks) {
            this.tasks.setAll(tasks);
        }

        @Override
        public ObservableList<Task> getItemList() {
            return tasks;
        }
    }
    //@@author
}
