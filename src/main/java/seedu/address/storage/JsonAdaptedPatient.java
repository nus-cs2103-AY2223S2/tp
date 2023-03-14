package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.NRIC;
import seedu.address.model.patient.Status;

import java.util.Stack;

/**
 * Jackson-friendly version of {@link Patient}.
 */
class JsonAdaptedPatient {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Patient's %s field is missing!";

    private final String name;
    private final String nric;
    private final String status;


    /**
     * Constructs a {@code JsonAdaptedPatient} with the given patient details.
     */
    @JsonCreator
    public JsonAdaptedPatient(@JsonProperty("name") String name, @JsonProperty("nric") String nric,
                              @JsonProperty("status") String status) {
        this.name = name;
        this.nric = nric;
        this.status = status;
    }

    /**
     * Converts a given {@code Patient} into this class for Jackson use.
     */
    public JsonAdaptedPatient(Patient source) {
        name = source.getName().fullName;
        nric = source.getNric().value;
        status = source.getStatus().value;

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

        if (nric == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, NRIC.class.getSimpleName()));
        }
        if (!NRIC.isValidNRIC(nric)) {
            throw new IllegalValueException(NRIC.MESSAGE_CONSTRAINTS);
        }
        final NRIC modelNric = new NRIC(nric);

        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName()));
        }
        if (!Status.isValidStatus(status)) {
            throw new IllegalValueException(Status.MESSAGE_CONSTRAINTS);
        }

        final Status modelStatus = Status.valueOf(status);


        return new Patient(modelNric, modelName, modelStatus);
    }

}
