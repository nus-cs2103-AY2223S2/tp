package vimification.model.task;

/**
 * Represents a Task in the task list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Task {

    private String taskDescription;

    public Task(String... taskComponents) {
        this.taskDescription = taskComponents[0];
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Task // instanceof handles nulls
                && taskDescription.equals(((Task) other).taskDescription)); // state check
    }

    @Override
    public String toString() {
        return this.taskDescription;
    }
}
