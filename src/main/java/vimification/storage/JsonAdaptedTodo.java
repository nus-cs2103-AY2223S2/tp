package vimification.storage;

import vimification.commons.exceptions.IllegalValueException;
import vimification.model.task.Description;
import vimification.model.task.Status;
import vimification.model.task.Task;
import vimification.model.task.Todo;
import vimification.model.task.Type;

public class JsonAdaptedTodo extends JsonAdaptedTask{
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    public JsonAdaptedTodo(Todo task) {
        super(task);
    }

    public Task toModelType() throws IllegalValueException {
        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(description);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName()));
        }
        final Status modelStatus = new Status(status);
        if (type == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Type.class.getSimpleName()));
        }
        if (!Type.isValidType(type)) {
            throw new IllegalValueException(Type.MESSAGE_CONSTRAINTS);
        }
        final Type modelType = new Type(type);

        return new Todo(modelDescription, modelStatus);
    }
}

