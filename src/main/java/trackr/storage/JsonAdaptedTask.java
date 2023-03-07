package trackr.storage;

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

    private final String name;
    private final String deadline;
    private final String status;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("name") String name, @JsonProperty("deadline") String deadline,
                             @JsonProperty("status") String status) {
        this.name = name;
        this.deadline = deadline;
        this.status = status;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        name = source.getTaskName().fullTaskName;
        deadline = source.getTaskDeadline().toJsonString();
        status = source.getTaskStatus().toJsonString();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TaskName.class.getSimpleName()));
        }
        if (!TaskName.isValidTaskName(name)) {
            throw new IllegalValueException(TaskName.MESSAGE_CONSTRAINTS);
        }
        final TaskName modelName = new TaskName(name);

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TaskDeadline.class.getSimpleName()));
        }
        if (!TaskDeadline.isValidTaskDeadline(deadline)) {
            throw new IllegalValueException(TaskDeadline.MESSAGE_CONSTRAINTS);
        }
        final TaskDeadline modelDeadline = new TaskDeadline(deadline);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TaskStatus.class.getSimpleName()));
        }
        if (!TaskStatus.isValidTaskStatus(status)) {
            throw new IllegalValueException(TaskStatus.MESSAGE_CONSTRAINTS);
        }
        final TaskStatus modelStatus = new TaskStatus(status);

        return new Task(modelName, modelDeadline, modelStatus);
    }

}
