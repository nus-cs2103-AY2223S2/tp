package trackr.model.task;

import trackr.model.commons.Name;

/**
 * Represents a Task's name in the task list.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class TaskName extends Name {

    //@@author HmuuMyatMoe-reused
    //Reused from AB3 with minor modifications
    public static final String MESSAGE_CONSTRAINTS =
            "Task names should only contain alphanumeric characters and spaces, and it should not be blank";

    /**
     * Constructs a {@code TaskName}.
     *
     * @param taskName A valid task name.
     */
    public TaskName(String taskName) {
        super(taskName, "Task");
    }
    //@@author
}

