package seedu.address.testutil;


import seedu.address.model.task.Task;

import static seedu.address.logic.commands.CommandTestUtil.VALID_TASK_NAME;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {
    public static final Task VALID_TASK_1 = new TaskBuilder().withName(VALID_TASK_NAME).build();

    private TypicalTasks() {} // prevents instantiation
}
