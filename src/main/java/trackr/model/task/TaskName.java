package trackr.model.task;

import trackr.model.commons.Name;

/**
 * Represents a Task's name in the task list.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class TaskName extends Name {

    /**
     * Constructs a {@code TaskName}.
     *
     * @param taskName A valid task name.
     */
    public TaskName(String taskName) {
        super(taskName, "Task");
    }
}

