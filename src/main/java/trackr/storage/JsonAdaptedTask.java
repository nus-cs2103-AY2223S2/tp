package trackr.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import trackr.commons.exceptions.IllegalValueException;
import trackr.model.task.Task;
import trackr.model.task.TaskDeadline;
import trackr.model.task.TaskName;
import trackr.model.task.TaskStatus;

import java.time.LocalDate;

/**
 * Jackson-friendly version of {@link Task}.
 */
public class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String taskName;
    private final LocalDate taskDeadline;
    private final String taskStatus;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("task name") String taskName,
                           @JsonProperty("task deadline") LocalDate taskDeadline,
                             @JsonProperty("task status") String taskStatus) {
        this.taskName = taskName;
        this.taskDeadline = taskDeadline;
        this.taskStatus = taskStatus;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        taskName = source.getTaskName().fullTaskName;
        taskDeadline = source.getTaskDeadline().taskDeadline;
        taskStatus = source.getTaskStatus().toString();
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        if (taskName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskName.class.getSimpleName()));
        }
        if (!TaskName.isValidTaskName(taskName)) {
            throw new IllegalValueException(TaskName.MESSAGE_CONSTRAINTS);
        }
        final TaskName modelTaskName = new TaskName(taskName);

        if (taskDeadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskDeadline.class.getSimpleName()));
        }
        if (!TaskDeadline.isValidTaskDeadline(taskDeadline)) {
            throw new IllegalValueException("invalid taskDeadline"); //taskDeadline.MESSAGE_CONSTRAINTS
        }
        final TaskDeadline modelTaskDeadline = new TaskDeadline(taskDeadline);

        if (taskStatus == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, TaskStatus.class.getSimpleName()));
        }
        if (!TaskStatus.isValidTaskStatus(taskStatus)) {
            throw new IllegalValueException(TaskStatus.MESSAGE_CONSTRAINTS);
        }
        final TaskStatus modelTaskStatus = new TaskStatus(taskStatus);

        return new Task(modelTaskName, modelTaskDeadline, modelTaskStatus);
    }

}
