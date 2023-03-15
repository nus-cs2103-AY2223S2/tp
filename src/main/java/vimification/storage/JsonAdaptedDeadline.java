package vimification.storage;

import vimification.commons.exceptions.IllegalValueException;
import vimification.model.task.Description;
import vimification.model.task.Status;
import vimification.model.task.Task;
import vimification.model.task.Date;
import vimification.model.task.Deadline;

public class JsonAdaptedDeadline extends JsonAdaptedTask {

    private final String deadline;

    public JsonAdaptedDeadline(Deadline task) {
        super(task);
        deadline = task.getDeadline().value;
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

        if (deadline == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(deadline)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Date modelDate = new Date(deadline);

        return new Deadline(modelDescription, modelStatus, modelDate);
    }
}

