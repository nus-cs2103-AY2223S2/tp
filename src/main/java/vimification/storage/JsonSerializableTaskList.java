package vimification.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonRootName;

import vimification.commons.exceptions.IllegalValueException;
import vimification.model.ReadOnlyTaskPlanner;
import vimification.model.TaskPlanner;
import vimification.model.task.Task;
import vimification.model.task.Todo;
import vimification.model.task.Deadline;
import vimification.model.task.Event;

/**
 * An Immutable TaskPlanner that is serializable to JSON format.
 */
@JsonRootName(value = "taskList")
public class JsonSerializableTaskList {

    public static final String MESSAGE_DUPLICATE_PERSON = "Task list contains duplicate task(s).";

    private final List<JsonAdaptedTask> tasks = new ArrayList<>();


    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableTaskList(ReadOnlyTaskPlanner source) {
        tasks.addAll(source.getTaskList().stream().map(this::toJsonAdaptedTask).collect(Collectors.toList()));
    }

    private JsonAdaptedTask toJsonAdaptedTask (Task task) {
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
    public TaskPlanner toModelType() throws IllegalValueException {
        TaskPlanner taskList = new TaskPlanner();
        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            Task t =  jsonAdaptedTask.toModelType();
            if (taskList.hasTask(t)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            taskList.addTask(t);
        }
        return taskList;
    }

}
