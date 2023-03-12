package seedu.patientist.model.ward;

import javafx.collections.ObservableList;
import seedu.patientist.model.person.Person;
import seedu.patientist.model.person.UniquePersonList;
import seedu.patientist.model.person.patient.Patient;
import seedu.patientist.model.person.staff.Staff;

import static seedu.patientist.commons.util.AppUtil.checkArgument;
import static seedu.patientist.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a ward in Patientist, which holds a collection of Patients and a collection of Staff.
 * TODO: implement
 */
public class Ward {

    private final UniquePersonList patients;
    private final UniquePersonList staffs;
    private final String wardName;

    public Ward(String wardName) {
        patients = new UniquePersonList();
        staffs = new UniquePersonList();
        this.wardName = wardName;
    }

    public boolean containsPatient(Patient patient) {
        return patients.contains(patient);
    }

    public boolean containsStaff(Staff staff) {
        return staffs.contains(staff);
    }

    public boolean containsPerson(Person person) {
        return patients.contains(person) || staffs.contains(person);
    }

    public void addPatient(Patient patient) {
        requireAllNonNull(patient);
        checkArgument(!containsPatient(patient));
        patients.add(patient);
    }

    public void addStaff(Staff staff) {
        requireAllNonNull(staff);
        checkArgument(!containsStaff(staff));
        staffs.add(staff);
    }

    public void deletePatient(Patient patient) {
        requireAllNonNull(patient);
        checkArgument(containsPatient(patient));
        patients.remove(patient);
    }

    public void deleteStaff(Staff staff) {
        requireAllNonNull(staff);
        checkArgument(containsStaff(staff));
        staffs.remove(staff);
    }

    public void setPatient(Patient target, Patient updated) {
        requireAllNonNull(target, updated);
        patients.setPerson(target, updated);
    }

    public void setStaff(Staff target, Staff updated) {
        requireAllNonNull(target, updated);
        staffs.setPerson(target, updated);
    }

    public ObservableList<Person> getPatientsAsUnmodifiableObservableList() {
        return patients.asUnmodifiableObservableList();
    }

    public ObservableList<Person> getStaffsAsUnmodifiableObservableList() {
        return staffs.asUnmodifiableObservableList();
    }

    /**
     * 2 wards are equal iff they share the same name
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Ward)) {
            return false;
        }
        Ward otherWard = (Ward) other;
        return this.wardName.equals(otherWard.wardName);
    }

    /**
     * Identity of a ward is defined by its name
     */
    @Override
    public int hashCode() {
        return this.wardName.hashCode();
    }

    @Override
    public String toString() {
        return this.wardName; //TODO: not sure whats needed here
    }
}
