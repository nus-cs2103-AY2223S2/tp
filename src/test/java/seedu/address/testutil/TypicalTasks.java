package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME_2;

import seedu.address.model.task.Task;
import seedu.address.model.task.TaskStatus;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {
    public static final Task VALID_TASK_1 = new TaskBuilder()
            .withName(VALID_TASK_NAME_1).build();
    public static final Task VALID_TASK_2 = new TaskBuilder()
            .withName(VALID_TASK_NAME_2).build();
    public static final Task VALID_LATE_TASK = new TaskBuilder()
            .withName(VALID_TASK_NAME_1)
            .withStatus(TaskStatus.LATE).build();
    public static final Task VALID_COMPLETE_TASK = new TaskBuilder()
            .withName(VALID_TASK_NAME_1)
            .withStatus(TaskStatus.COMPLETE).build();
    public static final Task VALID_INPROGRESS_TASK = new TaskBuilder()
            .withName(VALID_TASK_NAME_1)
            .withStatus(TaskStatus.INPROGRESS).build();
    public static final Task VALID_TASK_3 = new TaskBuilder()
            .withName(VALID_TASK_NAME_1).build();

    private TypicalTasks() {} // prevents instantiation
}
