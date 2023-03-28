package vimification.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import vimification.commons.exceptions.IllegalValueException;
import vimification.model.task.Priority;
import vimification.model.task.Status;
import vimification.model.task.Task;
import vimification.model.task.Todo;


public class JsonAdaptedTodo extends JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Todo's %s field is missing!";

    @JsonCreator
    public JsonAdaptedTodo(
            @JsonProperty("description") String description,
            @JsonProperty("status") Status status,
            @JsonProperty("priority") Priority priority) {
        super(description, status, priority);
    }

    @JsonCreator
    public JsonAdaptedTodo(Todo todo) {
        super(todo);
    }


    public Task toModelType() throws IllegalValueException {
        // TODO: add input validation
        /*
         * if (description == null) { throw new IllegalValueException(
         * String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName())); } if
         * (!Description.isValidDescription(description)) { throw new
         * IllegalValueException(Description.MESSAGE_CONSTRAINTS); } // final Description
         * modelDescription = new Description(description); // final Status modelStatus = new
         * Status(status); // if (type == null) { // throw new IllegalValueException( //
         * String.format(MISSING_FIELD_MESSAGE_FORMAT, Type.class.getSimpleName())); // } // if
         * (!Type.isValidType(type)) { // throw new IllegalValueException(Type.MESSAGE_CONSTRAINTS);
         * // } // final Type modelType = new Type(type);
         */
        return new Todo(description, status, priority);
    }
}

