package vimification.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import vimification.commons.exceptions.IllegalValueException;
import vimification.model.task.Event;
import vimification.model.task.Task;


public class JsonAdaptedEvent extends JsonAdaptedTask {

    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    @JsonCreator
    public JsonAdaptedEvent(
            @JsonProperty("description") String description,
            @JsonProperty("isDone") boolean isDone,
            @JsonProperty("startDateTime") LocalDateTime startDateTime,
            @JsonProperty("startEndTime") LocalDateTime endDateTime) {
        super(description, isDone);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public JsonAdaptedEvent(Event task) {
        super(task);
        startDateTime = task.getStartDateTime();
        endDateTime = task.getEndDateTime();
    }

    /**
     * Returns Model Type.
     */
    public Task toModelType() throws IllegalValueException {
        // TODO: Fix or remove this
        /*
         * if (description == null) { throw new IllegalValueException(
         * String.format(MISSING_FIELD_MESSAGE_FORMAT, Description.class.getSimpleName())); } if
         * (!Description.isValidDescription(description)) { throw new
         * IllegalValueException(Description.MESSAGE_CONSTRAINTS); } final Description
         * modelDescription = new Description(description);
         *
         * if (status == null) { throw new IllegalValueException(
         * String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName())); } final
         * Status modelStatus = new Status(status); if (type == null) { throw new
         * IllegalValueException( String.format(MISSING_FIELD_MESSAGE_FORMAT,
         * Type.class.getSimpleName())); } if (!Type.isValidType(type)) { throw new
         * IllegalValueException(Type.MESSAGE_CONSTRAINTS); } final Type modelType = new Type(type);
         * if (startDate == null || endDate == null) { throw new IllegalValueException(
         * String.format(MISSING_FIELD_MESSAGE_FORMAT, DateTime.class.getSimpleName())); } if
         * (!DateTime.isValidDate(startDate) || !DateTime.isValidDate(endDate)) { throw new
         * IllegalValueException(Description.MESSAGE_CONSTRAINTS); } final DateTime modelStartDate =
         * new DateTime(startDate); final DateTime modelEndDate = new DateTime(endDate);
         */
        return new Event(description, isDone, startDateTime, endDateTime);
    }
}
