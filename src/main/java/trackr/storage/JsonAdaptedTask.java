package trackr.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import trackr.commons.exceptions.IllegalValueException;
import trackr.model.task.Task;
import trackr.model.task.TaskDeadline;
import trackr.model.task.TaskName;
import trackr.model.task.TaskStatus;

/**
 * Jackson-friendly version of {@link Task}.
 */
class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";
    public static final String MESSAGE_PARSE_TIME_ADDED_ERROR =
            "Unexpected error encountered when parsing Task's `timeAdded` "
                    + "field that was read from storage file";

    private final String taskName;
    private final String taskDeadline;
    private final String taskStatus;
    private final String timeAdded;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("taskName") String taskName,
                           @JsonProperty("taskDeadline") String taskDeadline,
                           @JsonProperty("taskStatus") String taskStatus,
                           @JsonProperty("timeAdded") String timeAdded) {
        this.taskName = taskName;
        this.taskDeadline = taskDeadline;
        this.taskStatus = taskStatus;
        this.timeAdded = timeAdded;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        taskName = source.getTaskName().getName();
        taskDeadline = source.getTaskDeadline().toJsonString();
        taskStatus = source.getTaskStatus().toJsonString();
        timeAdded = source.getTimeAdded().toString();
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        if (taskName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TaskName.class.getSimpleName()));
        }
        if (!TaskName.isValidName(taskName)) {
            throw new IllegalValueException(TaskName.MESSAGE_CONSTRAINTS);
        }
        final TaskName modelTaskName = new TaskName(taskName);

        if (taskDeadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TaskDeadline.class.getSimpleName()));
        }
        if (!TaskDeadline.isValidDeadline(taskDeadline)) {
            throw new IllegalValueException(TaskDeadline.MESSAGE_CONSTRAINTS);
        }
        final TaskDeadline modelTaskDeadline = new TaskDeadline(taskDeadline);

        if (taskStatus == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TaskStatus.class.getSimpleName()));
        }
        if (!TaskStatus.isValidStatus(taskStatus, TaskStatus.STATUSES)) {
            throw new IllegalValueException(TaskStatus.MESSAGE_CONSTRAINTS);
        }
        final TaskStatus modelTaskStatus = new TaskStatus(taskStatus);

        final LocalDateTime modelTimeAdded;
        try {
            modelTimeAdded = LocalDateTime.parse(timeAdded);
        } catch (DateTimeParseException | NullPointerException e) {
            throw new IllegalValueException(MESSAGE_PARSE_TIME_ADDED_ERROR);
        }

        return new Task(modelTaskName, modelTaskDeadline, modelTaskStatus, modelTimeAdded);
    }

}
