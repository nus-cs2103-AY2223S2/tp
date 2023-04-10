package seedu.vms.testutil;

import seedu.vms.model.patient.Patient;
import seedu.vms.model.patient.PatientManager;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code PatientManager pm = new PatientManagerBuilder().withPatient("John", "Doe").build();}
 */
public class PatientManagerBuilder {

    private PatientManager patientManager;

    public PatientManagerBuilder() {
        patientManager = new PatientManager();
    }

    public PatientManagerBuilder(PatientManager patientManager) {
        this.patientManager = patientManager;
    }

    /**
     * Adds a new {@code Patient} to the {@code PatientManager} that we are building.
     */
    public PatientManagerBuilder withPatient(Patient patient) {
        patientManager.add(patient);
        return this;
    }

    public PatientManager build() {
        return patientManager;
    }
}
