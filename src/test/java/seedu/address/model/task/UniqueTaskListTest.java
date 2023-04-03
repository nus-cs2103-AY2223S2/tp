package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalTasks.VALID_TASK_1;
import static seedu.address.testutil.TypicalTasks.VALID_TASK_2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.model.task.exceptions.TaskNotFoundException;

public class UniqueTaskListTest {

    private final UniqueTaskList uniqueTaskList = new UniqueTaskList();

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.contains(null));
    }

    @Test
    public void contains_taskNotInList_returnsFalse() {
        assertFalse(uniqueTaskList.contains(VALID_TASK_1));
    }

    @Test
    public void contains_taskInList_returnsFalse() {
        uniqueTaskList.add(VALID_TASK_1);
        assertTrue(uniqueTaskList.contains(VALID_TASK_1));
    }

    @Test
    public void add_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.add(null));
    }

    @Test
    public void add_duplicateTask_throwsDuplicateTaskException() {
        uniqueTaskList.add(VALID_TASK_1);
        assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.add(VALID_TASK_1));
    }

    @Test
    public void setTask_targetTaskNotInList_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> uniqueTaskList.setTask(VALID_TASK_1, VALID_TASK_1));
    }

    @Test
    public void setTask_editedTaskHasDifferentName_success() {
        uniqueTaskList.add(VALID_TASK_1);
        uniqueTaskList.setTask(VALID_TASK_1, VALID_TASK_2);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(VALID_TASK_2);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTask_editedTaskHasNonUniqueName_throwsDuplicateTaskException() {
        uniqueTaskList.add(VALID_TASK_1);
        uniqueTaskList.add(VALID_TASK_2);
        assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.setTask(VALID_TASK_1, VALID_TASK_2));
    }

    @Test
    public void remove_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.remove(null));
    }

    @Test
    public void remove_taskDoesNotExist_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> uniqueTaskList.remove(VALID_TASK_1));
    }

    @Test
    public void remove_existingTask_removesTask() {
        uniqueTaskList.add(VALID_TASK_1);
        uniqueTaskList.remove(VALID_TASK_1);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTasks_nullTaskList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTasks((UniqueTaskList) null));
    }

    @Test
    public void setTasks_uniqueTaskList_replacesOwnListWithProvidedTaskList() {
        uniqueTaskList.add(VALID_TASK_1);
        UniqueTaskList expectedUniqueUniqueTaskList = new UniqueTaskList();
        expectedUniqueUniqueTaskList.add(VALID_TASK_2);
        uniqueTaskList.setTasks(expectedUniqueUniqueTaskList);
        assertEquals(expectedUniqueUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTasks_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTasks((List<Task>) null));
    }

    @Test
    public void setTasks_list_replacesOwnListWithProvidedList() {
        uniqueTaskList.add(VALID_TASK_1);
        List<Task> tasks = Collections.singletonList(VALID_TASK_2);
        uniqueTaskList.setTasks(tasks);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(VALID_TASK_2);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTasks_listWithDuplicateTasks_throwsDuplicateTaskException() {
        List<Task> listWithDuplicateTasks = Arrays.asList(VALID_TASK_1, VALID_TASK_1);
        assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.setTasks(listWithDuplicateTasks));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueTaskList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void add_task_increasesSize() {
        int sizeBefore = uniqueTaskList.size();
        uniqueTaskList.add(VALID_TASK_1);
        int sizeAfter = uniqueTaskList.size();
        assertTrue(sizeBefore + 1 == sizeAfter);
    }

    @Test
    public void remove_task_decreasesSize() {
        uniqueTaskList.add(VALID_TASK_1);
        int sizeBefore = uniqueTaskList.size();
        uniqueTaskList.remove(VALID_TASK_1);
        int sizeAfter = uniqueTaskList.size();
        assertTrue(sizeBefore - 1 == sizeAfter);
    }

    @Test
    public void get_task_returnsTask() {
        uniqueTaskList.add(VALID_TASK_1);
        assertTrue(uniqueTaskList.get(INDEX_FIRST_STUDENT.getZeroBased())
                .equals(uniqueTaskList.getInternalList().get(INDEX_FIRST_STUDENT.getZeroBased())));
    }

    @Test
    public void get_invalidTaskIndex_throwsIndexOutofBoundsException() {
        uniqueTaskList.add(VALID_TASK_1);
        org.junit.jupiter.api.Assertions.assertThrows(IndexOutOfBoundsException.class, ()
                -> uniqueTaskList.get(INDEX_SECOND_STUDENT.getZeroBased()));
    }
}
