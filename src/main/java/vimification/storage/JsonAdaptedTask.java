package vimification.storage;

import vimification.commons.exceptions.IllegalValueException;
import vimification.model.task.Task;

/**
 * Jackson-friendly version of {@link Task}.
 */
public abstract class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";
    public final String description;
    public final String status;
    public final String type;


    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task task) {
        description = task.getDescription().description;
        status = task.getStatus().value;
        type = task.getType().value;
    }
    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public abstract Task toModelType() throws IllegalValueException;
}
