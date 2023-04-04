package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDeadlineTasks.FIRST;
import static seedu.address.testutil.TypicalDeadlineTasks.SECOND;
import static seedu.address.testutil.TypicalTasks.ONE;
import static seedu.address.testutil.TypicalTasks.TWO;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.model.task.exceptions.TaskNotFoundException;
import seedu.address.testutil.DeadlineTaskBuilder;

public class UniqueTaskListTest {

    private final UniqueTaskList uniqueTaskList = new UniqueTaskList();

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.contains(null));
    }

    @Test
    public void contains_taskNotInList_returnsFalse() {
        assertFalse(uniqueTaskList.contains(ONE));
    }

    @Test
    public void contains_taskInList_returnsTrue() {
        uniqueTaskList.add(ONE);
        assertTrue(uniqueTaskList.contains(ONE));
    }

    @Test
    public void contains_deadlineTaskWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTaskList.add(FIRST);
        DeadlineTask editedFirst = new DeadlineTaskBuilder(FIRST).withDate("04/12/1089").build();
        assertTrue(uniqueTaskList.contains(editedFirst));
    }

    @Test
    public void add_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.add(null));
    }

    @Test
    public void add_duplicateTask_throwsDuplicateTaskException() {
        uniqueTaskList.add(ONE);
        assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.add(ONE));
    }

    @Test
    public void add_duplicateDeadlineTask_throwsDuplicateTaskException() {
        uniqueTaskList.add(SECOND);
        assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.add(SECOND));
    }

    @Test
    public void setTask_nullTargetTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTask(null, ONE));
    }

    @Test
    public void setTask_nullEditedTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTask(ONE, null));
    }

    @Test
    public void setTask_targetTaskNotInList_throwsPersonNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> uniqueTaskList.setTask(ONE, ONE));
    }

    @Test
    public void setTask_editedTaskIsSameTask_success() {
        uniqueTaskList.add(SECOND);
        uniqueTaskList.setTask(SECOND, SECOND);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(SECOND);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setDeadlineTask_editedDeadlineTaskHasSameIdentity_success() {
        uniqueTaskList.add(FIRST);
        DeadlineTask editedFirst = new DeadlineTaskBuilder(FIRST).withDate("31/12/2021").build();
        uniqueTaskList.setTask(FIRST, editedFirst);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(editedFirst);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTask_editedTaskHasDifferentIdentity_success() {
        uniqueTaskList.add(ONE);
        uniqueTaskList.setTask(ONE, TWO);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(TWO);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTask_editedTaskHasNonUniqueIdentity_throwsDuplicateTaskException() {
        uniqueTaskList.add(ONE);
        uniqueTaskList.add(TWO);
        assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.setTask(ONE, TWO));
    }

    @Test
    public void remove_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.remove(null));
    }

    @Test
    public void remove_taskDoesNotExist_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> uniqueTaskList.remove(ONE));
    }

    @Test
    public void remove_existingTask_removesTask() {
        uniqueTaskList.add(ONE);
        uniqueTaskList.remove(ONE);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTasks_nullUniqueTaskList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTasks((UniqueTaskList) null));
    }

    @Test
    public void setTasks_uniqueTaskList_replacesOwnListWithProvidedUniqueTaskList() {
        uniqueTaskList.add(ONE);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(TWO);
        uniqueTaskList.setTasks(expectedUniqueTaskList);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTasks_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTasks((List<Task>) null));
    }

    @Test
    public void setTasks_list_replacesOwnListWithProvidedList() {
        uniqueTaskList.add(ONE);
        List<Task> taskList = Collections.singletonList(TWO);
        uniqueTaskList.setTasks(taskList);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(TWO);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTasks_listWithDuplicateTasks_throwsDuplicateTaskException() {
        List<Task> listWithDuplicateTasks = Arrays.asList(ONE, ONE);
        assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.setTasks(listWithDuplicateTasks));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueTaskList.asUnmodifiableObservableList().remove(0));
    }
}

