package trackr.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.testutil.Assert.assertThrows;
import static trackr.testutil.TypicalTasks.SORT_INVENTORY_D;
import static trackr.testutil.TypicalTasks.SORT_INVENTORY_N;
import static trackr.testutil.TypicalTasks.getTypicalTaskList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import trackr.model.task.Task;
import trackr.model.task.exceptions.DuplicateTaskException;

public class TaskListTest {

    private final TaskList taskList = new TaskList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), taskList.getTaskList());
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
        List<Task> newTasks = Arrays.asList(SORT_INVENTORY_N, SORT_INVENTORY_D);
        TaskListTest.TaskListStub newData = new TaskListTest.TaskListStub(newTasks);

        assertThrows(DuplicateTaskException.class, () -> taskList.resetData(newData));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.hasTask(null));
    }

    @Test
    public void hasTask_taskNotInTaskList_returnsFalse() {
        assertFalse(taskList.hasTask(SORT_INVENTORY_N));
    }

    @Test
    public void hasTask_taskInTaskList_returnsTrue() {
        taskList.addTask(SORT_INVENTORY_N);
        assertTrue(taskList.hasTask(SORT_INVENTORY_N));
    }

    @Test
    public void hasTask_taskWithSameIdentityFieldsInTaskList_returnsTrue() {
        taskList.addTask(SORT_INVENTORY_N);
        assertTrue(taskList.hasTask(SORT_INVENTORY_D)); //different status
    }

    @Test
    public void getTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> taskList.getTaskList().remove(0));
    }

    /**
     * A stub ReadOnlyTaskList whose tasks list can violate interface constraints.
     */
    private static class TaskListStub implements ReadOnlyTaskList {
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        TaskListStub(Collection<Task> tasks) {
            this.tasks.setAll(tasks);
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }
    }

}
