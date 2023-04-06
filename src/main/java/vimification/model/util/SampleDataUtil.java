package vimification.model.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import vimification.model.LogicTaskList;
import vimification.model.TaskList;
import vimification.model.task.Priority;
import vimification.model.task.Status;
import vimification.model.task.Task;

public class SampleDataUtil {

    public static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static LogicTaskList getSampleData() {
        LogicTaskList sampleTaskList = new TaskList();
        sampleTaskList.add(new Task("quiz", LocalDateTime.parse("2023-01-01 23:59", FORMATTER),
                Status.IN_PROGRESS, Priority.UNKNOWN));
        sampleTaskList.add(new Task("tutorial", null, Status.NOT_DONE, Priority.NOT_URGENT));
        sampleTaskList.add(new Task("watch lecture", null, Status.COMPLETED, Priority.URGENT));
        sampleTaskList
                .add(new Task("do assignment", LocalDateTime.parse("2023-02-02 23:59", FORMATTER),
                        Status.OVERDUE, Priority.VERY_URGENT));
        return sampleTaskList;
    }

}

