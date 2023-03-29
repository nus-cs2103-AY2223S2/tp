package vimification.storage.oldcode;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import vimification.commons.exceptions.IllegalValueException;
import vimification.model.oldcode.Deadline;
import vimification.model.task.Priority;
import vimification.model.task.Status;
import vimification.model.task.Task;
import vimification.storage.JsonAdaptedTask;

public class JsonAdaptedDeadline {

    private final LocalDateTime deadline;

    @JsonCreator
    public JsonAdaptedDeadline(
            @JsonProperty("description") String description,
            @JsonProperty("status") Status status,
            @JsonProperty("priority") Priority priority,
            @JsonProperty("deadline") LocalDateTime deadline) {
        //super(description, status, priority);
        this.deadline = deadline;
    }

    public JsonAdaptedDeadline(Deadline deadline) {
        //super(deadline);
        this.deadline = deadline.getDeadline();
    }


}
