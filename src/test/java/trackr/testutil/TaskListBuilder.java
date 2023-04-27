package trackr.testutil;

import trackr.model.TaskList;
import trackr.model.task.Task;

/**
 * A utility class to help with building TaskList objects.
 * Example usage: <br>
 *     {@code TaskList tb = new TaskListBuilder().withTask("Buy X", "Buy Y").build();}
 */
//@@author liumc-sg-reused
public class TaskListBuilder {

    private TaskList taskList;

    public TaskListBuilder() {
        taskList = new TaskList();
    }

    public TaskListBuilder(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a new {@code Task} to the {@code TaskList} that we are building.
     */
    public TaskListBuilder withTask(Task task) {
        taskList.addItem(task);
        return this;
    }

    public TaskList build() {
        return taskList;
    }
}
