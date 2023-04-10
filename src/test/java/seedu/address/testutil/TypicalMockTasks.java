package seedu.address.testutil;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.student.Name;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskStatus;
import seedu.address.model.task.UniqueTaskList;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalMockTasks {

    public static final TaskStatus STATUS_IN_PROGRESS = spy(TaskStatus.INPROGRESS);
    public static final TaskStatus STATUS_COMPLETE = spy(TaskStatus.COMPLETE);
    public static final TaskStatus STATUS_LATE = spy(TaskStatus.LATE);
    public static final Task VALID_TASK_1 = mock(Task.class);

    public static final Name VALID_TASK_NAME_1_MOCK = spy(new Name(VALID_TASK_NAME_1));
    public static final Task VALID_TASK_2 = mock(Task.class);

    public static final Name VALID_TASK_NAME_2_MOCK = spy(new Name(VALID_TASK_NAME_2));

    public static final Task VALID_LATE_TASK = mock(Task.class);

    public static final Name VALID_LATE_TASK_NAME_MOCK = spy(new Name(VALID_TASK_NAME_1));

    public static final Task VALID_COMPLETE_TASK = mock(Task.class);

    public static final Name VALID_COMPLETE_TASK_NAME_MOCK = spy(new Name(VALID_TASK_NAME_1));

    public static final Task VALID_TASK_3 = mock(Task.class);

    public static final Name VALID_TASK_NAME_3_MOCK = spy(new Name(VALID_TASK_NAME_1));

    private TypicalMockTasks() {} // prevents instantiation

    public static void setTask1() {
        assert(VALID_TASK_NAME_1_MOCK.fullName.equals(VALID_TASK_NAME_1));
        doReturn(VALID_TASK_NAME_1_MOCK).when(VALID_TASK_1).getName();
        doReturn(STATUS_IN_PROGRESS).when(VALID_TASK_1).getStatus();
    }

    public static void setTask2() {
        assert(VALID_TASK_NAME_2_MOCK.fullName.equals(VALID_TASK_NAME_2));
        doReturn(VALID_TASK_NAME_2_MOCK).when(VALID_TASK_2).getName();
        doReturn(STATUS_IN_PROGRESS).when(VALID_TASK_2).getStatus();
    }

    public static void setTask3() {
        assert(VALID_TASK_NAME_3_MOCK.fullName.equals(VALID_TASK_NAME_1));
        doReturn(VALID_TASK_NAME_3_MOCK).when(VALID_TASK_3).getName();
        doReturn(STATUS_IN_PROGRESS).when(VALID_TASK_3).getStatus();

    }

    public static void setTaskComplete() {
        assert(VALID_COMPLETE_TASK_NAME_MOCK.fullName.equals(VALID_TASK_NAME_1));
        doReturn(VALID_COMPLETE_TASK_NAME_MOCK).when(VALID_COMPLETE_TASK).getName();
        doReturn(STATUS_COMPLETE).when(VALID_TASK_1).getStatus();
    }

    public static void setTaskLate() {
        assert(VALID_LATE_TASK_NAME_MOCK.fullName.equals(VALID_TASK_NAME_1));
        doReturn(VALID_LATE_TASK_NAME_MOCK).when(VALID_LATE_TASK).getName();
        doReturn(STATUS_LATE).when(VALID_TASK_1).getStatus();
    }
    public static UniqueTaskList getTypicalTasks() {
        setTask1();
        setTask2();
        setTask3();
        setTaskLate();
        setTaskComplete();
        UniqueTaskList uniqueTaskList = mock(UniqueTaskList.class);
        uniqueTaskList.add(VALID_TASK_1);
        uniqueTaskList.add(VALID_TASK_2);
        uniqueTaskList.add(VALID_TASK_3);
        uniqueTaskList.add(VALID_COMPLETE_TASK);
        uniqueTaskList.add(VALID_LATE_TASK);
        ObservableList<Task> observableList = FXCollections.observableArrayList();
        observableList.add(VALID_TASK_1);
        observableList.add(VALID_TASK_2);
        observableList.add(VALID_TASK_3);
        observableList.add(VALID_COMPLETE_TASK);
        observableList.add(VALID_LATE_TASK);
        doReturn(observableList).when(uniqueTaskList).getInternalList();
        return uniqueTaskList;
    }
}
