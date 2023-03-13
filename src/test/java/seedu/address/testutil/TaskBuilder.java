package seedu.address.testutil;

import seedu.address.model.person.Name;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_TASK_NAME = "Complete A Math Exercise 1";

    private Name taskName;

    /**
     * Creates a {@code ScoreBuilder} with the default details.
     */
    public TaskBuilder() {
        taskName = new Name(DEFAULT_TASK_NAME);
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        taskName = taskToCopy.getName();
    }

    /**
     * Sets the {@code Name} of the {@code Score} that we are building.
     */
    public TaskBuilder withName(String name) {
        this.taskName = new Name(name);
        return this;
    }

    public Task build() {
        return new Task(taskName);
    }
}
