package vimification.storage;

import vimification.commons.exceptions.IllegalValueException;
import vimification.model.task.Date;
import vimification.model.task.Description;
import vimification.model.task.Event;
import vimification.model.task.Status;
import vimification.model.task.Task;
import vimification.model.task.Type;

public class JsonAdaptedEvent extends JsonAdaptedTask {

    private final String startDate;
    private final String endDate;

    public JsonAdaptedEvent (Event task) {
        super(task);
        startDate = task.getStartDate().value;
        endDate = task.getEndDate().value;
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
        if (startDate == null || endDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(startDate) || !Date.isValidDate(endDate)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Date modelStartDate = new Date(startDate);
        final Date modelEndDate = new Date(endDate);

        return new Event(modelDescription, modelStatus, modelStartDate, modelEndDate);
    }
}
