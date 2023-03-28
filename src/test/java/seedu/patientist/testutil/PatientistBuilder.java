package seedu.patientist.testutil;

import seedu.patientist.model.Patientist;
import seedu.patientist.model.person.patient.Patient;
import seedu.patientist.model.ward.Ward;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code Patientist ab = new PatientistBuilder().withPerson("John", "Doe").build();}
 */
public class PatientistBuilder {

    private Patientist patientist;
    private Ward ward = new Ward("Ward");

    /**
     * Constructor for PatientistBuilder.
     */
    public PatientistBuilder() {
        patientist = new Patientist();
        patientist.addWard(ward);
    }

    public PatientistBuilder(Patientist patientist) {
        this.patientist = patientist;
    }

    /**
     * Adds a new {@code Person} to the {@code Patientist} that we are building.
     */
    public PatientistBuilder withPerson(Patient patient) {
        patientist.addPatient(patient, ward);
        return this;
    }

    public Patientist build() {
        return patientist;
    }
}
