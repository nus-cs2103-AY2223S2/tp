package seedu.address.model.util;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.TaskList;
import seedu.address.model.fish.Fish;
import seedu.address.model.task.Description;
import seedu.address.model.task.Task;

public class SampleTaskUtil {
    public static Task[] getSampleTasks() {
        return new Task[] {
                new Task(
                        new Description("clean tank")
                ),
                new Task(
                        new Description("feed fish")
                )
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
