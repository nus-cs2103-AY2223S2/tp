package vimification.storage;

import java.util.List;
// import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

// import vimification.commons.core.LogsCenter;
import vimification.commons.exceptions.IllegalValueException;
import vimification.model.LogicTaskList;
import vimification.model.task.Deadline;
import vimification.model.task.Event;
import vimification.model.task.Task;
import vimification.model.task.Todo;

/**
 * An Immutable TaskPlanner that is serializable to JSON format.
 */
@JsonRootName(value = "logicTaskList")
public class JsonAdaptedLogicTaskList {

    // private static final Logger LOGGER = LogsCenter.getLogger(JsonAdaptedLogicTaskList.class);

    private final List<JsonAdaptedTask> tasks;

    @JsonCreator
    public JsonAdaptedLogicTaskList(@JsonProperty("tasks") List<JsonAdaptedTask> tasks) {
        this.tasks = tasks == null ? List.of() : tasks;
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created
     *        {@code JsonSerializableAddressBook}.
     */
    public JsonAdaptedLogicTaskList(LogicTaskList source) {
        tasks = source.stream().map(this::toJsonAdaptedTask).collect(Collectors.toList());
    }

    private JsonAdaptedTask toJsonAdaptedTask(Task task) {
        if (task instanceof Todo) {
            return new JsonAdaptedTodo((Todo) task);
        } else if (task instanceof Deadline) {
            return new JsonAdaptedDeadline((Deadline) task);
        } else {
            return new JsonAdaptedEvent((Event) task);
        }
    }

    /**
     * Converts this address book into the model's {@code TaskPlanner} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public LogicTaskList toModelType() throws IllegalValueException {
        LogicTaskList taskList = new LogicTaskList();
        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            Task task = jsonAdaptedTask.toModelType();
            taskList.add(task);
        }
        return taskList;
    }

    @Override
    public String toString() {
        return "JsonAdpatedLogicTaskList: [" + tasks + "]";
    }
}
