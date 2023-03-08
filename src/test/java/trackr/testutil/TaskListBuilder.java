package trackr.testutil;

import trackr.model.AddressBook;
import trackr.model.TaskList;
import trackr.model.person.Person;
import trackr.model.task.Task;

/**
 * A utility class to help with building TaskList objects.
 * Example usage: <br>
 *     {@code TaskList tl = new TaskListBuilder().withTask("Sort Inventory", "").build();}
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
