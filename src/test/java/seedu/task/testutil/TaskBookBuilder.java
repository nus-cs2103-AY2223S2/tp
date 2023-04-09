package seedu.task.testutil;

import seedu.task.model.TaskBook;
import seedu.task.model.task.Task;

//@@author
/**
 * A utility class to help with building Taskbook objects.
 * Example usage: <br>
 *     {@code TaskBook ab = new TaskBookBuilder().withTask("John", "Doe").build();}
 */
public class TaskBookBuilder {

    private TaskBook taskBook;

    public TaskBookBuilder() {
        taskBook = new TaskBook();
    }

    public TaskBookBuilder(TaskBook taskBook) {
        this.taskBook = taskBook;
    }

    /**
     * Adds a new {@code Task} to the {@code TaskBook} that we are building.
     */
    public TaskBookBuilder withTask(Task task) {
        taskBook.addTask(task);
        return this;
    }

    public TaskBook build() {
        return taskBook;
    }
}
