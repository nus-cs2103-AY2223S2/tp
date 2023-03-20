package seedu.patientist.testutil;

import seedu.patientist.model.Patientist;
import seedu.patientist.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code Patientist ab = new PatientistBuilder().withPerson("John", "Doe").build();}
 */
public class PatientistBuilder {

    private Patientist patientist;

    public PatientistBuilder() {
        patientist = new Patientist();
    }

    public PatientistBuilder(Patientist patientist) {
        this.patientist = patientist;
    }

    /**
     * Adds a new {@code Person} to the {@code Patientist} that we are building.
     */
    public PatientistBuilder withPerson(Person person) {
        //patientist.addPerson(person); todo
        return this;
    }

    public Patientist build() {
        return patientist;
    }
}
