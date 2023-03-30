package seedu.patientist.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.patientist.commons.exceptions.IllegalValueException;
import seedu.patientist.model.person.patient.PatientToDo;

/**
 * Jackson-friendly version of {@link PatientToDo}.
 */
public class JsonAdaptedToDo {
    private final String toDoDescription;

    /**
     * Constructs a {@code JsonAdaptedToDo} with the given {@code toDoDescription}.
     */
    @JsonCreator
    public JsonAdaptedToDo(String toDoDescription) {
        this.toDoDescription = toDoDescription;
    }

    /**
     * Converts a given {@code PatientToDo} into this class for Jackson use.
     */
    public JsonAdaptedToDo(PatientToDo source) {
        toDoDescription = source.getTodo();
    }

    @JsonValue
    public String getToDoDescription() {
        return toDoDescription;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code PatientToDo} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public PatientToDo toModelType() throws IllegalValueException {
        return new PatientToDo(toDoDescription);
    }

}
