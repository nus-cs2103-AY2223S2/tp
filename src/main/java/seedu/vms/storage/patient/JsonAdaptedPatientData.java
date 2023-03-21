package seedu.vms.storage.patient;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.vms.commons.exceptions.IllegalValueException;
import seedu.vms.model.IdData;
import seedu.vms.model.patient.Patient;


/**
 * Jackson-friendly version of {@code IdData<Patient>}.
 */
public class JsonAdaptedPatientData {
    private final boolean isActive;
    private final int id;
    private final JsonAdaptedPatient patient;


    /**
     * Constructs a {@code JsonAdaptedPatientData} with the given patient details.
     */
    @JsonCreator
    public JsonAdaptedPatientData(
            @JsonProperty("isActive") boolean isActive,
            @JsonProperty("id") int id,
            @JsonProperty("patient") JsonAdaptedPatient patient) {
        this.isActive = isActive;
        this.id = id;
        this.patient = patient;
    }


    /**
     * Converts a given {@code IdData<Patient>} into this class for Jackson use.
     */
    public JsonAdaptedPatientData(IdData<Patient> patientData) {
        isActive = patientData.isActive();
        id = patientData.getId();
        patient = new JsonAdaptedPatient(patientData.getValue());
    }


    /**
     * Converts this Jackson-friendly adapted patient data object into the model's {@code IdData<Patient>} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted patient data.
     */
    public IdData<Patient> toModelType() throws IllegalValueException {
        return new IdData<>(isActive, id, patient.toModelType());
    }
}
