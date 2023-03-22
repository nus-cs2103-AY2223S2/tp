package seedu.careflow.testutil;

import seedu.careflow.model.PatientRecord;
import seedu.careflow.model.patient.Patient;

/**
 * A utility class to help with building PatientRecord objects.
 * Example usage: <br>
 *     {@code PatientRecord pr = new PatientRecordBuilder().withPatient("John", "Doe").build();}
 */
public class PatientRecordBuilder {

    private PatientRecord patientRecord;

    public PatientRecordBuilder() {
        patientRecord = new PatientRecord();
    }

    public PatientRecordBuilder(PatientRecord patientRecord) {
        this.patientRecord = patientRecord;
    }

    /**
     * Adds a new {@code Patient} to the {@code CareFlow} that we are building.
     */
    public PatientRecordBuilder withPatient(Patient patient) {
        patientRecord.addPatient(patient);
        return this;
    }

    public PatientRecord build() {
        return patientRecord;
    }
}
