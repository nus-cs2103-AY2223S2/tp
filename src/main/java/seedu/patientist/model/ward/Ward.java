package seedu.patientist.model.ward;

import static seedu.patientist.commons.util.AppUtil.checkArgument;
import static seedu.patientist.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;

import javafx.collections.ObservableList;
import seedu.patientist.model.person.Person;
import seedu.patientist.model.person.UniquePersonList;
import seedu.patientist.model.person.exceptions.DuplicatePersonException;
import seedu.patientist.model.person.exceptions.PersonNotFoundException;
import seedu.patientist.model.person.patient.Patient;
import seedu.patientist.model.person.staff.Staff;

/**
 * Represents a ward in Patientist, which holds a collection of Patients and a collection of Staff.
 */
public class Ward {

    public static final String MESSAGE_CONSTRAINTS = "Ward name cannot be blank";

    private final UniquePersonList patients;
    private final UniquePersonList staffs;
    private final String wardName;

    /**
     * Initialises an empty ward with wardName.
     */
    public Ward(String wardName) {
        requireAllNonNull(wardName);
        checkArgument(isValidWardName(wardName), MESSAGE_CONSTRAINTS);
        patients = new UniquePersonList(wardName);
        staffs = new UniquePersonList(wardName);
        this.wardName = wardName;
    }

    public static boolean isValidWardName(String wardName) {
        return !wardName.equals("");
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
            throw new DuplicatePersonException();
        }
        patients.add(patient);
    }

    /**
     * Adds staff into the ward. Staff must not already exist.
     */
    public void addStaff(Staff staff) {
        requireAllNonNull(staff);
        if (containsStaff(staff)) {
            throw new DuplicatePersonException();
        }
        staffs.add(staff);
    }

    /**
     * Deletes patient from ward. Patient must already exist in ward.
     */
    public void deletePatient(Patient patient) {
        requireAllNonNull(patient);
        if (!containsPatient(patient)) {
            throw new PersonNotFoundException();
        }
        patients.remove(patient);
    }

    /**
     * Deletes staff from ward. Staff must already exist in ward.
     */
    public void deleteStaff(Staff staff) {
        requireAllNonNull(staff);
        if (!containsStaff(staff)) {
            throw new PersonNotFoundException();
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
        } else if (person instanceof Patient && patients.contains(person)) {
            patients.remove(person);
        } else {
            throw new PersonNotFoundException();
        }
    }

    /**
     * Deletes a patient based on id from any ward. Patient must already exist in ward.
     *
     * @param patient The dummy patient used to check equality of {@code IdNumber}.
     */
    public void deletePatientById(Patient patient) {
        requireAllNonNull(patient);
        if (!containsPatient(patient)) {
            throw new PersonNotFoundException();
        }
        Iterator<Person> patientIterator = patients.iterator();
        while (patientIterator.hasNext()) {
            Person toCheck = patientIterator.next();
            if (patient.isSamePerson(toCheck)) {
                patientIterator.remove();
                return;
            }
        }
    }

    /**
     * Deletes a staff member based on id from any ward. Staff must already exist in ward.
     *
     * @param staff The dummy staff used to check equality of {@code IdNumber}.
     */
    public void deleteStaffById(Staff staff) {
        requireAllNonNull(staff);
        if (!containsStaff(staff)) {
            throw new PersonNotFoundException();
        }
        Iterator<Person> staffIterator = staffs.iterator();
        while (staffIterator.hasNext()) {
            Person toCheck = staffIterator.next();
            if (staff.isSamePerson(toCheck)) {
                staffIterator.remove();
                return;
            }
        }
    }

    /**
     * Replaces target patient with updated patient.
     * Target must already exist in ward.
     * Updated must not already exist in ward.
     */
    public void setPatient(Patient target, Patient updated) {
        requireAllNonNull(target, updated);
        patients.setPerson(target, updated);
    }

    /**
     * Replaces target staff with updated staff.
     * Target must already exist in ward.
     * Updated must not already exist in ward.
     */
    public void setStaff(Staff target, Staff updated) {
        requireAllNonNull(target, updated);
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
        return this.wardName;
    }

    public String getWardName() {
        return wardName;
    }

    /**
     * Checks if ward is empty.
     */
    public boolean isEmpty() {
        return patients.isEmpty() && staffs.isEmpty();
    }
}
