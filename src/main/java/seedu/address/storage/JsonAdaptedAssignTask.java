package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.mapping.AssignTask;
import seedu.address.model.shared.Id;

/**
 * Jackson-friendly version of {@link AssignTask}.
 */
public class JsonAdaptedAssignTask {

    private final String personId;

    private final String taskId;

    /**
     * Constructs a {@code JsonAdaptedAssignTask} with the given {@code assignTask}.
     */
    @JsonCreator
    public JsonAdaptedAssignTask(@JsonProperty("personId") String personId, @JsonProperty("taskId") String taskId) {

        this.personId = personId;
        this.taskId = taskId;
    }

    /**
     * Converts a given {@code AssignTask} into this class for Jackson use.
     */
    public JsonAdaptedAssignTask(AssignTask source) {
        personId = source.getPersonId().getValue().toString();
        taskId = source.getTaskId().getValue().toString();
    }


    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code AssignTask} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted assignTask.
     */
    public AssignTask toModelType() throws IllegalValueException {
        if (Id.isInValidId(personId)) {
            throw new IllegalValueException(Id.MESSAGE_CONSTRAINTS);
        }

        if (Id.isInValidId(taskId)) {
            throw new IllegalValueException(Id.MESSAGE_CONSTRAINTS);
        }

        Id modePersonId = new Id(personId);
        Id modeTaskId = new Id(taskId);

        return new AssignTask(modePersonId, modeTaskId);
    }
}
