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

    /**
     * The constructor used by Jackson.
     *
     * @param tasks a list of tasks
     */
    @JsonCreator
    public JsonAdaptedTaskList(@JsonProperty("tasks") List<JsonAdaptedTask> tasks) {
        if (tasks == null) {
            tasks = List.of();
        }
        this.tasks = tasks;
    }

    /**
     * Converts a given {@code TaskList} for Jackson use.
     *
     * @param source the given {@code TaskList}
     */
    public JsonAdaptedTaskList(TaskList source) {
        tasks = source.getLogicSource()
                .stream()
                .map(JsonAdaptedTask::new)
                .collect(Collectors.toList());
    }

    /**
     * Converts this instance into an actual {@code TaskList}.
     *
     * @return a {@code TaskList}, as a result of the conversion
     * @throws DataConversionException if there is any error occured during the conversion
     */
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
