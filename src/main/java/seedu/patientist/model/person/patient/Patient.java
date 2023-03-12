package seedu.patientist.model.person.patient;

import seedu.patientist.model.Patientist;
import seedu.patientist.model.person.Address;
import seedu.patientist.model.person.Email;
import seedu.patientist.model.person.Name;
import seedu.patientist.model.person.Person;
import seedu.patientist.model.person.Phone;
import seedu.patientist.model.tag.Tag;

import java.util.Set;

public class Patient extends Person {
    private PatientStatusDetails details;
    private PatientIdNumber id;

    /**
     * Every field must be present and not null.
     *
     * @param id
     * @param name
     * @param phone
     * @param email
     * @param address
     * @param tags
     */
    public Patient(PatientIdNumber id, Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        //TODO: for now, let's set ward using tags. we can change this implementation once wards are implemented
        this.details = new PatientStatusDetails();
        this.id = id;
    }

    public Patient(PatientIdNumber id, Name name, Phone phone, Email email, Address address, PatientStatusDetails details, Set<Tag> tags) {
        super(name, phone, email, address, tags);
        //TODO: for now, let's set ward using tags. we can change this implementation once wards are implemented
        this.details = details;
        this.id = id;
    }

    public void setPatientStatusDetails(PatientStatusDetails details) {
        this.details = details;
    }

    public PatientStatusDetails getPatientStatusDetails() {
        return this.details;
    }

    public PatientIdNumber getPatientIdNumber() {
        return this.id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("; Details: ")
                .append(details.toString())
                .append("; Type: Patient ");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Patient)) {
            return false;
        }

        Patient otherPat = (Patient) other;
        return this.id.equals(otherPat.id);
    }

    @Override
    public boolean isSamePerson(Person otherPerson) {
        return this.equals(otherPerson);
    }

}
