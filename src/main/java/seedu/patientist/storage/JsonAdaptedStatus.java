package seedu.patientist.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.patientist.commons.exceptions.IllegalValueException;
import seedu.patientist.model.person.patient.PatientStatusDetails;

/**
 * Jackson-friendly version of {@link PatientStatusDetails}.
 */
public class JsonAdaptedStatus {
    private final String statusDescription;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code statusDescription}.
     */
    @JsonCreator
    public JsonAdaptedStatus(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedStatus(PatientStatusDetails source) {
        statusDescription = source.getDetails();
    }

    @JsonValue
    public String getStatusDescription() {
        return statusDescription;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public PatientStatusDetails toModelType() throws IllegalValueException {
        return new PatientStatusDetails(statusDescription);
    }

}
