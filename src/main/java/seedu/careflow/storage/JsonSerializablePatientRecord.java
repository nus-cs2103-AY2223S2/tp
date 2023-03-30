package seedu.careflow.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.careflow.commons.exceptions.IllegalValueException;
import seedu.careflow.model.PatientRecord;
import seedu.careflow.model.patient.Patient;
import seedu.careflow.model.readonly.ReadOnlyPatientRecord;

/**
 * An Immutable PatientRecord that is serializable to JSON format.
 */
@JsonRootName(value = "patientrecord")
public class JsonSerializablePatientRecord {
    public static final String MESSAGE_DUPLICATE_PATIENT_NAME = "Patient list contains duplicate patient(s).";
    public static final String MESSAGE_DUPLICATE_PATIENT_IC = "Patient list contains duplicate IC(s).";

    private final List<JsonAdaptedPatient> patients;

    {
        patients = new ArrayList<>();
    }

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializablePatientRecord(@JsonProperty("patients") List<JsonAdaptedPatient> patients) {
        this.patients.addAll(patients);
    }

    /**
     * Converts a given {@code ReadOnlyPatientRecord} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializablePatientRecord}.
     */
    public JsonSerializablePatientRecord(ReadOnlyPatientRecord source) {
        patients.addAll(source.getPatientList().stream().map(JsonAdaptedPatient::new).collect(Collectors.toList()));
    }

    /**
     * Converts this serializable patient record into the model's {@code PatientRecord} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public PatientRecord toModelType() throws IllegalValueException {
        PatientRecord patientRecord = new PatientRecord();
        for (JsonAdaptedPatient jsonAdaptedPatient : patients) {
            Patient patient = jsonAdaptedPatient.toModelType();
            if (patientRecord.hasSamePatientName(patient)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PATIENT_NAME);
            }
            if (patientRecord.hasSamePatientIc(patient)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PATIENT_IC);
            }
            patientRecord.addPatient(patient);
        }
        return patientRecord;
    }
}
