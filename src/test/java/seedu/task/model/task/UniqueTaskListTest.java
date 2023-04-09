package seedu.task.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.task.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.task.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.task.testutil.Assert.assertThrows;
import static seedu.task.testutil.TypicalTasks.ALICE;
import static seedu.task.testutil.TypicalTasks.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.task.model.task.exceptions.DuplicateTaskException;
import seedu.task.model.task.exceptions.TaskNotFoundException;
import seedu.task.testutil.SimpleTaskBuilder;

//@@author
public class UniqueTaskListTest {

    private final UniqueTaskList uniqueTaskList = new UniqueTaskList();

    @Test
    public void contains_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.contains(null));
    }

    @Test
    public void contains_taskNotInList_returnsFalse() {
        assertFalse(uniqueTaskList.contains(ALICE));
    }

    @Test
    public void contains_taskInList_returnsTrue() {
        uniqueTaskList.add(ALICE);
        assertTrue(uniqueTaskList.contains(ALICE));
    }

    //@@author Huggenguggen
    @Test
    public void contains_taskWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTaskList.add(ALICE);
        Task editedAlice = new SimpleTaskBuilder(ALICE).withDescription(VALID_DESCRIPTION_BOB)
                .withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueTaskList.contains(editedAlice));
    }

    //@@author
    @Test
    public void add_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.add(null));
    }

    @Test
    public void add_duplicateTask_throwsDuplicateTaskException() {
        uniqueTaskList.add(ALICE);
        assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.add(ALICE));
    }

    @Test
    public void setTask_nullTargetTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTask(null, ALICE));
    }

    @Test
    public void setTask_nullEditedTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTask(ALICE, null));
    }

    @Test
    public void setTask_targetTaskNotInList_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> uniqueTaskList.setTask(ALICE, ALICE));
    }

    @Test
    public void setTask_editedTaskIsSameTask_success() {
        uniqueTaskList.add(ALICE);
        uniqueTaskList.setTask(ALICE, ALICE);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(ALICE);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    //@@author Huggenguggen
    @Test
    public void setTask_editedTaskHasSameIdentity_success() {
        uniqueTaskList.add(ALICE);
        Task editedAlice = new SimpleTaskBuilder(ALICE).withDescription(VALID_DESCRIPTION_BOB)
                .withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueTaskList.setTask(ALICE, editedAlice);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(editedAlice);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    //@@author
    @Test
    public void setTask_editedTaskHasDifferentIdentity_success() {
        uniqueTaskList.add(ALICE);
        uniqueTaskList.setTask(ALICE, BOB);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(BOB);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTask_editedTaskHasNonUniqueIdentity_throwsDuplicateTaskException() {
        uniqueTaskList.add(ALICE);
        uniqueTaskList.add(BOB);
        assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.setTask(ALICE, BOB));
    }

    @Test
    public void remove_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.remove(null));
    }

    @Test
    public void remove_taskDoesNotExist_throwsTaskNotFoundException() {
        assertThrows(TaskNotFoundException.class, () -> uniqueTaskList.remove(ALICE));
    }

    @Test
    public void remove_existingTask_removesTask() {
        uniqueTaskList.add(ALICE);
        uniqueTaskList.remove(ALICE);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTasks_nullUniqueTaskList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTasks((UniqueTaskList) null));
    }

    @Test
    public void setTasks_uniqueTaskList_replacesOwnListWithProvidedUniqueTaskList() {
        uniqueTaskList.add(ALICE);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(BOB);
        uniqueTaskList.setTasks(expectedUniqueTaskList);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTasks_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTaskList.setTasks((List<Task>) null));
    }

    @Test
    public void setTasks_list_replacesOwnListWithProvidedList() {
        uniqueTaskList.add(ALICE);
        List<Task> taskList = Collections.singletonList(BOB);
        uniqueTaskList.setTasks(taskList);
        UniqueTaskList expectedUniqueTaskList = new UniqueTaskList();
        expectedUniqueTaskList.add(BOB);
        assertEquals(expectedUniqueTaskList, uniqueTaskList);
    }

    @Test
    public void setTasks_listWithDuplicateTasks_throwsDuplicateTaskException() {
        List<Task> listWithDuplicateTasks = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateTaskException.class, () -> uniqueTaskList.setTasks(listWithDuplicateTasks));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueTaskList.asUnmodifiableObservableList().remove(0));
    }
}
