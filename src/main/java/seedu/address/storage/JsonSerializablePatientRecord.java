package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.PatientRecord;
import seedu.address.model.person.Patient;
import seedu.address.model.readonly.ReadOnlyPatientRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonSerializablePatientRecord {
    public static final String MESSAGE_DUPLICATE_PATIENT = "Patient list contains duplicate patient(s).";

    private final List<JsonAdaptedPatient> patients = new ArrayList<>();

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
            if (patientRecord.hasPatient(patient)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PATIENT);
            }
            patientRecord.addPatient(patient);
        }
        return patientRecord;
    }
}
