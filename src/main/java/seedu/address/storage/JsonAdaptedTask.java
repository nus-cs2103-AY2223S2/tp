package seedu.address.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.Name;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskStatus;

/**
 * Jackson-friendly version of {@link Task}.
 */
public class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE_TIME;
    public static final String DATE_MESSAGE_CONSTRAINTS = "Wrong format of date.";

    private final String taskName;
    private final String status;
    private final String creationDate;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("taskName") String taskName, @JsonProperty("status") String status,
                           @JsonProperty("creationDate") String creationDate) {
        this.taskName = taskName;
        this.status = status;
        this.creationDate = creationDate;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        taskName = source.getName().fullName;
        status = source.getStatus().toString();
        creationDate = source.getCreationDate().toString();
    }

    /**
     * Converts this Jackson-friendly adapted task object into the model's {@code Task} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted task.
     */
    public Task toModelType() throws IllegalValueException {
        if (taskName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Task.isValidTaskName(taskName)) {
            throw new IllegalValueException(Task.MESSAGE_CONSTRAINTS);
        }
        final Name modelTaskName = new Name(taskName);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TaskStatus.class.getSimpleName()));
        }
        if (!TaskStatus.isValidTaskStatus(status)) {
            throw new IllegalValueException(TaskStatus.MESSAGE_CONSTRAINTS);
        }
        final TaskStatus modelTaskStatus = TaskStatus.valueOf(status);

        if (creationDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LocalDateTime.class.getSimpleName()));
        }
        try {
            LocalDateTime.parse(creationDate, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(DATE_MESSAGE_CONSTRAINTS);
        }
        LocalDateTime modelCreationDate = LocalDateTime.parse(creationDate, FORMATTER);

        return new Task(modelTaskName, modelTaskStatus, modelCreationDate);
    }
}
