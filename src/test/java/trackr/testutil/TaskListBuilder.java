package trackr.testutil;

import trackr.model.TaskList;
import trackr.model.task.Task;

/**
 * A utility class to help with building TaskList objects.
 * Example usage: <br>
<<<<<<< HEAD
 *     {@code TaskList tl = new TaskListBuilder().withTask("Sort Inventory", "").build();}
=======
 *     {@code TaskList tb = new TaskListBuilder().withTask("Buy X", "Buy Y").build();}
>>>>>>> master
 */
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
        taskList.addTask(task);
        return this;
    }

    public TaskList build() {
        return taskList;
    }
}
