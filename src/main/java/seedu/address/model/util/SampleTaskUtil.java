package seedu.address.model.util;

import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.TaskList;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;

/**
 * Contains utility methods for populating Fish Ahoy! with sample data.
 */
public class SampleTaskUtil {
    public static Task[] getSampleTasks() {
        return new Task[] {
            new Task(new Description("clean tank")),
            new Task(new Description("feed fish"))
        };
    }

    public static ReadOnlyTaskList getSampleTaskList() {
        TaskList sampleTaskList = new TaskList();
        for (Task sampleTask : getSampleTasks()) {
            sampleTaskList.addTask(sampleTask);
        }
        return sampleTaskList;
    }
}
