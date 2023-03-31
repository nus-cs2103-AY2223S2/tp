package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalTasks.VALID_TASK_1;
import static seedu.address.testutil.TypicalTasks.VALID_TASK_2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.model.task.exceptions.TaskNotFoundException;

public class TaskListTest {

    private final TaskList taskList = new TaskList();

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.contains(null));
    }

    @Test
    public void contains_taskNotInList_returnsFalse() {
        assertFalse(taskList.contains(VALID_TASK_1));
    }

    @Test
    public void contains_taskInList_returnsFalse() {
        taskList.add(VALID_TASK_1);
        assertTrue(taskList.contains(VALID_TASK_1));
    }

    @Test
    public void add_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.add(null));
    }

    @Test
    public void add_duplicateTask_throwsDuplicateTaskException() {
        taskList.add(VALID_TASK_1);
        assertThrows(DuplicateTaskException.class, () -> taskList.add(VALID_TASK_1));
    }

    @Test
    public void setTask_targetTaskNotInList_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> taskList.setTask(VALID_TASK_1, VALID_TASK_1));
    }

    @Test
    public void setTask_editedTaskHasDifferentName_success() {
        taskList.add(VALID_TASK_1);
        taskList.setTask(VALID_TASK_1, VALID_TASK_2);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(VALID_TASK_2);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTask_editedTaskHasNonUniqueName_throwsDuplicateTaskException() {
        taskList.add(VALID_TASK_1);
        taskList.add(VALID_TASK_2);
        assertThrows(DuplicateTaskException.class, () -> taskList.setTask(VALID_TASK_1, VALID_TASK_2));
    }

    @Test
    public void remove_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.remove(null));
    }

    @Test
    public void remove_taskDoesNotExist_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> taskList.remove(VALID_TASK_1));
    }

    @Test
    public void remove_existingTask_removesTask() {
        taskList.add(VALID_TASK_1);
        taskList.remove(VALID_TASK_1);
        TaskList expectedTaskList = new TaskList();
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTasks_nullTaskList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setTasks((TaskList) null));
    }

    @Test
    public void setTasks_uniqueTaskList_replacesOwnListWithProvidedTaskList() {
        taskList.add(VALID_TASK_1);
        TaskList expectedUniqueTaskList = new TaskList();
        expectedUniqueTaskList.add(VALID_TASK_2);
        taskList.setTasks(expectedUniqueTaskList);
        assertEquals(expectedUniqueTaskList, taskList);
    }

    @Test
    public void setTasks_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskList.setTasks((List<Task>) null));
    }

    @Test
    public void setTasks_list_replacesOwnListWithProvidedList() {
        taskList.add(VALID_TASK_1);
        List<Task> tasks = Collections.singletonList(VALID_TASK_2);
        taskList.setTasks(tasks);
        TaskList expectedTaskList = new TaskList();
        expectedTaskList.add(VALID_TASK_2);
        assertEquals(expectedTaskList, taskList);
    }

    @Test
    public void setTasks_listWithDuplicateTasks_throwsDuplicateTaskException() {
        List<Task> listWithDuplicateTasks = Arrays.asList(VALID_TASK_1, VALID_TASK_1);
        assertThrows(DuplicateTaskException.class, () -> taskList.setTasks(listWithDuplicateTasks));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> taskList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void add_task_increasesSize() {
        int sizeBefore = taskList.size();
        taskList.add(VALID_TASK_1);
        int sizeAfter = taskList.size();
        assertTrue(sizeBefore + 1 == sizeAfter);
    }

    @Test
    public void remove_task_decreasesSize() {
        taskList.add(VALID_TASK_1);
        int sizeBefore = taskList.size();
        taskList.remove(VALID_TASK_1);
        int sizeAfter = taskList.size();
        assertTrue(sizeBefore - 1 == sizeAfter);
    }

    @Test
    public void get_score_returnsScore() {
        taskList.add(VALID_TASK_1);
        assertTrue(taskList.get(INDEX_FIRST_PERSON.getZeroBased())
                .equals(taskList.getInternalList().get(INDEX_FIRST_PERSON.getZeroBased())));
    }

    @Test
    public void get_invalidScoreIndex_throwsIndexOutofBoundsException() {
        taskList.add(VALID_TASK_1);
        Assertions.assertThrows(IndexOutOfBoundsException.class, ()
                -> taskList.get(INDEX_SECOND_PERSON.getZeroBased()));
    }
}
