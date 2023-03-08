package seedu.address.storage;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;

/**
 * Jackson-friendly version of {@link Patient}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Patient's %s field is missing!";

    private final String name;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given patient details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name) {
        this.name = name;
    }

    /**
     * Converts a given {@code Patient} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Patient source) {
        name = source.getName().fullName;
    }

    /**
     * Converts this Jackson-friendly adapted patient object into the model's
     * {@code Patient} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted patient.
     */
    public Patient toModelType() throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        return new Patient(modelName);
    }

}
