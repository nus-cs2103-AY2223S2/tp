package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_DESCRIPTION;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.ONE;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.task.Task;
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.testutil.TaskBuilder;

public class TaskBookTest {

    private final TaskBook taskBook = new TaskBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), taskBook.getTaskList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyTaskBook_replacesData() {
        TaskBook newData = getTypicalTaskBook();
        taskBook.resetData(newData);
        assertEquals(newData, taskBook);
    }

    @Test
    public void resetData_withDuplicateTasks_throwsDuplicateTaskException() {
        // Two persons with the same identity fields
        Task editedOne = new TaskBuilder(ONE).withTaskDescription(VALID_TASK_DESCRIPTION).build();
        List<Task> newTasks = Arrays.asList(ONE, editedOne);
        TaskBookStub newData = new TaskBookStub(newTasks);

        assertThrows(DuplicateTaskException.class, () -> taskBook.resetData(newData));
    }

    @Test
    public void hasTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> taskBook.hasTask(null));
    }

    @Test
    public void hasTask_personNotInTaskBook_returnsFalse() {
        assertFalse(taskBook.hasTask(ONE));
    }

    @Test
    public void hasTask_personInTaskBook_returnsTrue() {
        taskBook.addTask(ONE);
        assertTrue(taskBook.hasTask(ONE));
    }

    @Test
    public void hasTaskWithSameIdentityFieldsInTaskBook_returnsTrue() {
        taskBook.addTask(ONE);
        Task editedTask = new TaskBuilder(ONE).withTaskDescription(VALID_TASK_DESCRIPTION)
                .build();
        System.out.println(editedTask.toString());
        System.out.println(ONE.toString());
        assertTrue(taskBook.hasTask(editedTask));
    }

    @Test
    public void getTaskList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> taskBook.getTaskList().remove(0));
    }

    /**
     * A stub ReadOnlyTaskBook whose persons list can violate interface constraints.
     */
    private static class TaskBookStub implements ReadOnlyTaskBook {
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        TaskBookStub(Collection<Task> tasks) {
            this.tasks.setAll(tasks);
        }

        @Override
        public ObservableList<Task> getTaskList() {
            return tasks;
        }
    }

}
