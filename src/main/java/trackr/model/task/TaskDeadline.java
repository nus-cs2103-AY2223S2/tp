package trackr.model.task;

import trackr.model.commons.Deadline;

/**
 * Represents a Task's deadline in the task list.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(String)}
 */
public class TaskDeadline extends Deadline {

    /**
     * Constructs a {@code TaskDeadline}.
     *
     * @param deadline A valid deadline.
     */
    public TaskDeadline(String deadline) {
        super(deadline, "Task");
    }
}

