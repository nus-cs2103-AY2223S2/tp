package vimification.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import vimification.common.exceptions.DataConversionException;
import vimification.model.TaskList;
import vimification.model.task.Task;

/**
 * Jackson-friendly version of {@link TaskList}.
 */
public class JsonAdaptedTaskList {

    private final List<JsonAdaptedTask> tasks;

    @JsonCreator
    public JsonAdaptedTaskList(@JsonProperty("tasks") List<JsonAdaptedTask> taskList) {
        if (taskList == null) {
            taskList = List.of();
        }
        this.tasks = taskList;
    }

    public JsonAdaptedTaskList(TaskList source) {
        tasks = source.getLogicSource()
                .stream()
                .map(JsonAdaptedTask::new)
                .collect(Collectors.toList());
    }

    public TaskList toModelType() throws DataConversionException {
        List<Task> modelTasks = new ArrayList<>();
        for (JsonAdaptedTask task : tasks) {
            Task modelTask = task.toModelType();
            modelTasks.add(modelTask);
        }
        return new TaskList(modelTasks);
    }

    @Override
    public String toString() {
        return "JsonAdaptedTaskList [tasks=" + tasks + "]";
    }
}
