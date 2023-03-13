package seedu.patientist.model.ward;

import static seedu.patientist.commons.util.CollectionUtil.requireAllNonNull;

import javafx.collections.ObservableList;
import seedu.patientist.model.person.Person;
import seedu.patientist.model.person.UniquePersonList;
import seedu.patientist.model.person.patient.Patient;
import seedu.patientist.model.person.staff.Staff;

/**
 * Represents a ward in Patientist, which holds a collection of Patients and a collection of Staff.
 * TODO: implement
 */
public class Ward {

    private final UniquePersonList patients;
    private final UniquePersonList staffs;
    private final String wardName;

    /**
     * Initialises an empty ward with wardName.
     */
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

    /**
     * Checks if ward contains Person, which could be either staff or patient
     */
    public boolean containsPerson(Person person) {
        return patients.contains(person) || staffs.contains(person);
    }

    /**
     * Adds patient into the ward. Patient must not already exist.
     */
    public void addPatient(Patient patient) {
        requireAllNonNull(patient);
        if (containsPatient(patient)) {
            return; //TODO: throw DuplicatePersonsException
        }
        patients.add(patient);
    }

    /**
     * Adds staff into the ward. Staff must not already exist.
     */
    public void addStaff(Staff staff) {
        requireAllNonNull(staff);
        if (containsStaff(staff)) {
            return; //TODO: throw DuplicatePersonsException
        }
        staffs.add(staff);
    }

    /**
     * Deletes patient from ward. Patient must already exist in ward.
     */
    public void deletePatient(Patient patient) {
        requireAllNonNull(patient);
        if (!containsPatient(patient)) {
            return; //TODO: throw PersonNotFoundException
        }
        patients.remove(patient);
    }

    /**
     * Deletes staff from ward. Staff must already exist in ward.
     */
    public void deleteStaff(Staff staff) {
        requireAllNonNull(staff);
        if (!containsStaff(staff)) {
            return; //TODO: throw PersonNotFoundException
        }
        staffs.remove(staff);
    }

    /**
     * Deletes person which could be a staff or patient from ward. Person must already exist in ward.
     */
    public void deletePerson(Person person) {
        requireAllNonNull(person);
        if (person instanceof Staff && staffs.contains(person)) {
            staffs.remove(person);
        }
        if (person instanceof Patient && patients.contains(person)) {
            patients.remove(person);
        }
        return; //TODO: throw PersonNotFoundException
    }

    /**
     * Replaces target patient with updated patient.
     * Target must already exist in ward.
     * Updated must not already exist in ward.
     */
    public void setPatient(Patient target, Patient updated) {
        requireAllNonNull(target, updated);
        //TODO: throw PersonNotFoundException
        patients.setPerson(target, updated);
    }

    /**
     * Replaces target staff with updated staff.
     * Target must already exist in ward.
     * Updated must not already exist in ward.
     */
    public void setStaff(Staff target, Staff updated) {
        requireAllNonNull(target, updated);
        //TODO: throw PersonNotFoundException
        staffs.setPerson(target, updated);
    }

    /**
     * Returns an unmodifiable list view of patients in the ward
     */
    public ObservableList<Person> getPatientsAsUnmodifiableObservableList() {
        return patients.asUnmodifiableObservableList();
    }

    /**
     * Returns an unmodifiable list view of staff in the ward
     */
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
