package trackr.model.task;

import trackr.model.commons.Deadline;

/**
 * Represents a Task's deadline in the task list.
 * Guarantees: immutable; is valid as declared in {@link #isValidDeadline(String)}
 */
public class TaskDeadline extends Deadline {
    //@@author HmuuMyatMoe-reused
    //Reused from AB3 with minor modifications
    public static final String MESSAGE_CONSTRAINTS =
            "Task deadline should only contain numeric values in the format \"DD/MM/YYYY\" and it should not be blank.";


    /**
     * Constructs a {@code TaskDeadline}.
     *
     * @param deadline A valid deadline.
     */
    public TaskDeadline(String deadline) {
        super(deadline, "Task");
    }
    //@@author
}

