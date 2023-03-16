package vimification.model.util;

import vimification.model.task.*;
import vimification.model.TaskPlanner;
import vimification.model.ReadOnlyTaskPlanner;

public class SampleDataUtil {

    public static Task[] getSampleTasks() {
        return new Task[] {
                new Deadline(new Description("Do quiz"), new Status(false), new DateTime("2023-03-18 23:59")),
                new Todo(new Description("Watch lecture"), new Status(true)),
                new Todo(new Description("Do tutorial"), new Status(false)),
                new Deadline(new Description("Submit reflection"), new Status(true), new DateTime("2023-03-14 23:59")),
                new Event(new Description("Attend tutorial"), new Status(true), new DateTime("2023-03-15 14:00"),
                        new DateTime("2023-03-15 15:00")),
                new Event(new Description("Group meeting"), new Status(false), new DateTime("2023-03-19 16:00"),
                        new DateTime("2023-03-19 18:00")),
        };
    }

    public static ReadOnlyTaskPlanner getSampleTaskList() {
        TaskPlanner sampleTp = new TaskPlanner();
        for (Task sampleTask : getSampleTasks()) {
            sampleTp.addTask(sampleTask);
        }
        return sampleTp;
    }
}