package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Task;

/**
 * Jackson-friendly version of {@link Task}.
 */
public class JsonAdaptedTask {
    private final String taskDescription;

    /**
     * Constructs a {@code JsonAdaptedTask} with the given {@code taskDescription}.
     */
    @JsonCreator
    public JsonAdaptedTask(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     * @param source
     */
    public JsonAdaptedTask(Task source) {
        taskDescription = source.value;
    }

    @JsonValue
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Converts this Jackson-friendly adapted Task into the Model's {@code Task} object.
     * @throws IllegalValueException if taskDescription is null
     */
    public Task toModelType() throws IllegalValueException {
        requireNonNull(taskDescription);
        return new Task(taskDescription);
    }
}
