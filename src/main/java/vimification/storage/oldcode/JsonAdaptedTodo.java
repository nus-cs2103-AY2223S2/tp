package vimification.storage.oldcode;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import vimification.commons.exceptions.IllegalValueException;
import vimification.model.task.Priority;
import vimification.model.task.Status;
import vimification.model.task.Task;
import vimification.model.oldcode.Todo;
import vimification.storage.JsonAdaptedTask;


public class JsonAdaptedTodo {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Todo's %s field is missing!";

    @JsonCreator
    public JsonAdaptedTodo(
            @JsonProperty("description") String description,
            @JsonProperty("status") Status status,
            @JsonProperty("priority") Priority priority) {
        //super(description, status, priority);
    }

    //public JsonAdaptedTodo(Todo todo) {
      //  super(todo);

}

