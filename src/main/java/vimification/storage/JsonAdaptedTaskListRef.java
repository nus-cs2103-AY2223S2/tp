package vimification.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import vimification.commons.exceptions.IllegalValueException;
import vimification.model.TaskListRef;
import vimification.model.task.Task;

/**
 * Jackson-friendly version of {@link TaskListRef}.
 */
public class JsonAdaptedTaskListRef {

    private final List<JsonAdaptedTask> taskList;

    @JsonCreator
    public JsonAdaptedTaskListRef(@JsonProperty("taskList") List<JsonAdaptedTask> taskList) {
        if (taskList == null) {
            taskList = List.of();
        }
        this.taskList = taskList;
    }

    public JsonAdaptedTaskListRef(TaskListRef source) {
        taskList = source.getTaskList()
                .stream()
                .map(JsonAdaptedTask::new)
                .collect(Collectors.toList());
    }

    public TaskListRef toModelType() throws IllegalValueException {
        List<Task> list = new ArrayList<>();
        for (JsonAdaptedTask jsonAdaptedTask : taskList) {
            Task task = jsonAdaptedTask.toModelType();
            list.add(task);
        }
        return new TaskListRef(list);
    }

    @Override
    public String toString() {
        return "JsonAdaptedTaskListRef [taskList=" + taskList + "]";
    }
}
