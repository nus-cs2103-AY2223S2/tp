package vimification.storage;

import java.util.ArrayList;
import java.util.List;
// import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

// import vimification.commons.core.LogsCenter;
import vimification.commons.exceptions.IllegalValueException;
import vimification.model.TaskListRef;
import vimification.model.task.Task;

/**
 * An Immutable TaskPlanner that is serializable to JSON format.
 */
public class JsonAdaptedTaskListRef {

    // private static final Logger LOGGER = LogsCenter.getLogger(JsonAdaptedLogicTaskList.class);

    private final List<JsonAdaptedTask> taskList;

    @JsonCreator
    public JsonAdaptedTaskListRef(@JsonProperty("taskList") List<JsonAdaptedTask> taskList) {
        if (taskList == null) {
            taskList = List.of();
        }
        this.taskList = taskList;
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created
     *        {@code JsonSerializableAddressBook}.
     */
    public JsonAdaptedTaskListRef(TaskListRef source) {
        taskList = source.getTaskList()
                .stream()
                .map(JsonAdaptedTask::new)
                .collect(Collectors.toList());
    }


    /**
     * Converts this address book into the model's {@code TaskPlanner} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TaskListRef toModelType() throws IllegalValueException {
        List<Task> taskList = new ArrayList<>();
        for (JsonAdaptedTask jsonAdaptedTask : this.taskList) {
            Task task = jsonAdaptedTask.toModelType();
            taskList.add(task);
        }
        return new TaskListRef(taskList);
    }

    @Override
    public String toString() {
        return "JsonAdaptedTaskListRef [taskList=" + taskList + "]";
    }
}
