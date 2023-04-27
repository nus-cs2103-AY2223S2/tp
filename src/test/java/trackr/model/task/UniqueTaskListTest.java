package trackr.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static trackr.logic.commands.CommandTestUtil.VALID_TASK_STATUS_DONE;
import static trackr.testutil.Assert.assertThrows;
import static trackr.testutil.TypicalTasks.BUY_EGGS_D;
import static trackr.testutil.TypicalTasks.BUY_FLOUR_N;
import static trackr.testutil.TypicalTasks.SORT_INVENTORY_N;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import trackr.model.item.exceptions.DuplicateItemException;
import trackr.model.item.exceptions.ItemNotFoundException;
import trackr.testutil.TaskBuilder;

//@@author hmuumyatmoe-reused
public class UniqueTaskListTest {

    //@@author HmuuMyatMoe-reused
    // Reused from AB3 with minor modifications
    private final UniqueTaskList uniqueTaskList = new UniqueTaskList();

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.contains(null));
    }

    @Test
    public void contains_taskNotInList_returnsFalse() {
        assertFalse(uniqueTaskList.contains(SORT_INVENTORY_N));
    }

    @Test
    public void contains_taskInList_returnsTrue() {
        uniqueTaskList.add(SORT_INVENTORY_N);
        assertTrue(uniqueTaskList.contains(SORT_INVENTORY_N));
    }

    @Test
    public void contains_taskWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTaskList.add(SORT_INVENTORY_N);
        Task editedTask = new TaskBuilder(SORT_INVENTORY_N)
                .withTaskStatus(VALID_TASK_STATUS_DONE).build();
        assertTrue(uniqueTaskList.contains(editedTask));
    }

    @Test
    public void add_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.add(null));
    }

    @Test
    public void add_duplicateTask_throwsDuplicateTaskException() {
        uniqueTaskList.add(SORT_INVENTORY_N);
        assertThrows(DuplicateItemException.class, () -> uniqueTaskList.add(SORT_INVENTORY_N));
    }

    @Test
    public void setTask_nullTargetTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setItem(null, SORT_INVENTORY_N));
    }

    @Test
    public void setTask_nullEditedTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setItem(SORT_INVENTORY_N, null));
    }

    @Test
    public void setTask_targetTaskNotInList_throwsTaskNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> uniqueTaskList.setItem(SORT_INVENTORY_N, SORT_INVENTORY_N));
    }

    @Test
    public void setTask_editedTaskIsSameTask_success() {
        uniqueTaskList.add(SORT_INVENTORY_N);
        uniqueTaskList.setItem(SORT_INVENTORY_N, SORT_INVENTORY_N);

        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(SORT_INVENTORY_N);

        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTask_editedTaskHasSameIdentity_success() {
        uniqueTaskList.add(SORT_INVENTORY_N);
        Task editedTask = new TaskBuilder(SORT_INVENTORY_N)
                .withTaskStatus(VALID_TASK_STATUS_DONE).build();
        uniqueTaskList.setItem(SORT_INVENTORY_N, editedTask); //change task status

        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(editedTask);

        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTask_editedTaskHasDifferentIdentity_success() {
        uniqueTaskList.add(SORT_INVENTORY_N);
        uniqueTaskList.setItem(SORT_INVENTORY_N, BUY_FLOUR_N);

        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(BUY_FLOUR_N);

        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTask_editedTaskHasNonUniqueIdentity_throwsDuplicateTaskException() {
        uniqueTaskList.add(SORT_INVENTORY_N);
        uniqueTaskList.add(BUY_EGGS_D);
        assertThrows(DuplicateItemException.class, () -> uniqueTaskList.setItem(SORT_INVENTORY_N, BUY_EGGS_D));
    }

    @Test
    public void remove_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.remove(null));
    }

    @Test
    public void remove_taskDoesNotExist_throwsTaskNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> uniqueTaskList.remove(SORT_INVENTORY_N));
    }

    @Test
    public void remove_existingTask_removesTask() {
        uniqueTaskList.add(SORT_INVENTORY_N);
        uniqueTaskList.remove(SORT_INVENTORY_N);

        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();

        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTasks_nullUniqueTaskList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setItems((UniqueTaskList) null));
    }

    @Test
    public void setTasks_uniqueTaskList_replacesOwnListWithProvidedUniqueTaskList() {
        uniqueTaskList.add(SORT_INVENTORY_N);

        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(BUY_FLOUR_N);

        uniqueTaskList.setItems(expectedUniqueTaskList);

        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTasks_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setItems((List<Task>) null));
    }

    @Test
    public void setTasks_list_replacesOwnListWithProvidedList() {
        uniqueTaskList.add(SORT_INVENTORY_N);
        List<Task> taskList = Collections.singletonList(BUY_EGGS_D);
        uniqueTaskList.setItems(taskList);

        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(BUY_EGGS_D);

        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTasks_listWithDuplicateTasks_throwsDuplicateTaskException() {
        List<Task> listWithDuplicateTasks = Arrays.asList(SORT_INVENTORY_N, SORT_INVENTORY_N);
        assertThrows(DuplicateItemException.class, () -> uniqueTaskList.setItems(listWithDuplicateTasks));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueTaskList.asUnmodifiableObservableList().remove(0));
    }
    //@@author

    //@@author liumc-sg-reused
    @Test
    public void equals() {
        uniqueTaskList.add(SORT_INVENTORY_N);

        UniqueTaskList differentUniqueTaskList = new UniqueTaskList();
        differentUniqueTaskList.add(BUY_FLOUR_N);

        UniqueTaskList sameUniqueTaskList = new UniqueTaskList();
        sameUniqueTaskList.add(SORT_INVENTORY_N);

        assertTrue(uniqueTaskList.equals(uniqueTaskList)); //same object
        assertTrue(uniqueTaskList.equals(sameUniqueTaskList)); //contains the same tasks

        assertFalse(uniqueTaskList.equals(null)); //null
        assertFalse(uniqueTaskList.equals(differentUniqueTaskList)); //different task lists
        assertFalse(uniqueTaskList.equals(1)); //different objects
    }
}
