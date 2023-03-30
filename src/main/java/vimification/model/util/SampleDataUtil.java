package vimification.model.util;

import vimification.model.LogicTaskList;
import vimification.model.task.Priority;
import vimification.model.task.Status;
import vimification.model.task.Task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SampleDataUtil {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static LogicTaskList getSampleData() {
        LogicTaskList sampleTaskList = new LogicTaskList();
        sampleTaskList.add(new Task("quiz", LocalDateTime.parse("2023-01-01 23:59", formatter), Status.IN_PROGRESS, Priority.UNKNOWN));
        sampleTaskList.add(new Task("tutorial", null, Status.NOT_DONE, Priority.NOT_URGENT));
        sampleTaskList.add(new Task("watch lecture", null, Status.COMPLETED, Priority.URGENT));
        sampleTaskList.add(new Task("do assignment", LocalDateTime.parse("2023-02-02 23:59", formatter), Status.OVERDUE, Priority.VERY_URGENT));
        return sampleTaskList;
    }

}

