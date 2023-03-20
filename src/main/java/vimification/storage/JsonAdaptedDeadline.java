package vimification.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import vimification.commons.exceptions.IllegalValueException;
import vimification.model.task.Status;
import vimification.model.task.Task;
import vimification.model.task.components.DateTime;
import vimification.model.task.components.Description;
import vimification.model.task.Deadline;

public class JsonAdaptedDeadline extends JsonAdaptedTask {

    private final LocalDateTime deadline;

    @JsonCreator
    public JsonAdaptedDeadline(
            @JsonProperty("description") String description,
            @JsonProperty("isDone") boolean isDone,
            @JsonProperty("deadline") LocalDateTime deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    public JsonAdaptedDeadline(Deadline task) {
        super(task);
        deadline = task.getDeadline();
    }

    public Task toModelType() throws IllegalValueException {
        /*
         * if (description == null) {
         * throw new IllegalValueException(
         * String.format(MISSING_FIELD_MESSAGE_FORMAT,
         * Description.class.getSimpleName()));
         * }
         * if (!Description.isValidDescription(description)) {
         * throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
         * }
         * final Description modelDescription = new Description(description);
         *
         * if (status == null) {
         * throw new IllegalValueException(
         * String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName()));
         * }
         * final Status modelStatus = new Status(status);
         *
         * if (deadline == null) {
         * throw new IllegalValueException(
         * String.format(MISSING_FIELD_MESSAGE_FORMAT, DateTime.class.getSimpleName()));
         * }
         * if (!DateTime.isValidDate(deadline)) {
         * throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
         * }
         * final DateTime modelDate = new DateTime(deadline);
         */
        return new Deadline(description, isDone, deadline);
    }
}
