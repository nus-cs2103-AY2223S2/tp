package seedu.patientist.model;

import static java.util.Objects.requireNonNull;
import static seedu.patientist.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.patientist.model.person.Person;
import seedu.patientist.model.person.patient.Patient;
import seedu.patientist.model.person.staff.Staff;
import seedu.patientist.model.ward.Ward;
import seedu.patientist.model.ward.WardList;

/**
 * Wraps all data at the patientist-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class Patientist implements ReadOnlyPatientist {

    private final WardList wards;
    //TODO: this should eventually hold a list of wards, which in turn hold 2 UniquePersonList, for patients and staff

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        wards = new WardList();
    }

    public Patientist() {}

    /**
     * Creates a Patientist using the Persons in the {@code toBeCopied}
     */
    public Patientist(ReadOnlyPatientist toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the ward list with {@code wards}.
     * {@code wards} must not contain duplicate wards, and no person should appear more than once.
     */
    public void setWards(List<Ward> wards) {
        this.wards.setWards(wards);
    }

    /**
     * Resets the existing data of this {@code Patientist} with {@code newData}.
     */
    public void resetData(ReadOnlyPatientist newData) {
        requireNonNull(newData);

        setWards(newData.getWardList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the patientist book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return wards.contains(person);
    }

    /**
     * Returns true if a person with the same identity as {@code person} exists in {@code ward}.
     */
    public boolean hasPerson(Person person, Ward ward) {
        requireAllNonNull(person, ward);
        return ward.containsPerson(person);
    }

    /**
     * Adds a patient to the ward.
     * The ward must exist and the patient must not already exist.
     */
    public void addPatient(Patient patient, Ward ward) {
        requireAllNonNull(patient, ward);
        ward.addPatient(patient);
    }

    /**
     * Adds a staff to the ward.
     * The ward must exist and the staff must not already be assigned to the ward.
     */
    public void addStaff(Staff staff, Ward ward) {
        requireAllNonNull(staff, ward);
        ward.addStaff(staff);
    }

    /**
     * Replaces the given staff {@code target} with {@code edited} throughout the whole patientist book.
     * {@code target} must exist in the patientist book.
     * {@code ward} must exist in the patientist book.
     * The staff identity of {@code edited} must not be the same as another existing staff.
     */
    public void setStaff(Staff target, Staff edited) {
        requireAllNonNull(target, edited);
        for (Ward ward : wards) {
            if (ward.containsStaff(target)) {
                ward.setStaff(target, edited);
            }
        }
    }

    /**
     * Replaces the given patient {@code target} with {@code edited}.
     * {@code target} must exist in the patientist book.
     * {@code ward} must exist in the patientist book.
     * The patient identity of {@code edited} must not be the same as another existing patient in the paitentist book.
     */
    public void setPatient(Patient target, Patient edited) {
        requireAllNonNull(target, edited);
        for (Ward ward : wards) {
            if (ward.containsPatient(target)) {
                ward.setPatient(target, edited);
            }
        }
    }

    /**
     * Removes {@code key} from this {@code ward}.
     * {@code ward} must exist.
     * {@code key} must exist in the ward.
     */
    public void removeStaff(Staff key, Ward ward) {
        ward.deleteStaff(key);
    }

    /**
     * Removes {@code key} from all {@code ward}s.
     * {@code key} must exist.
     */
    public void removeStaff(Staff key) {
        for (Ward ward : wards) {
            if (ward.containsStaff(key)) {
                ward.deleteStaff(key);
            }
        }
    }

    /**
     * Removes {@code key} from all {@code ward}s.
     * {@code key} could be a staff or patient, all instances will be removed.
     */
    public void removePerson(Person key) {
        for (Ward ward : wards) {
            if (ward.containsPerson(key)) {
                ward.deletePerson(key);
            }
        }
    }

    /**
     * Removes {@code key} from this {@code ward}.
     * {@code key} could be a staff or patient.
     */
    public void removePerson(Person person, Ward ward) {
        if (person instanceof Staff) {
            removeStaff((Staff) person, ward);
        }
        if (person instanceof Patient) {
            removePatient((Patient) person, ward);
        }
        return; //TODO: there's some kind of exception to be thrown here idk what
    }

    /**
     * Removes {@code key} from this {@code Patientist}.
     * {@code ward} must exist.
     * {@code key} must exist in the patientist book.
     */
    public void removePatient(Patient key, Ward ward) {
        ward.deletePatient(key);
    }

    //// ward level methods

    /**
     * Returns true if this Patientist contains a ward with the same name as the target.
     */
    public boolean hasWard(Ward ward) {
        return this.wards.contains(ward);
    }

    /**
     * Adds {@code ward} to the patientist's wardlist
     */
    public void addWard(Ward ward) {
        requireAllNonNull(ward);
        wards.add(ward);
    }

    /**
     * Deletes {@code ward} from the patientist's wardlist.
     * {@code ward} must exist.
     */
    public void deleteWard(Ward ward) {
        requireAllNonNull(ward);
        wards.delete(ward);
    }

    /**
     * Replaces {@code target} ward with {@code edited} ward.
     * Target must already exist.
     * Edited must not already exist.
     */
    public void setWard(Ward target, Ward edited) {
        requireAllNonNull(target, edited);
        wards.setWard(target, edited);
    }

    //// util methods

    @Override
    public String toString() {
        return wards.asUnmodifiableObservableList().size() + " wards";
        // TODO: refine later
    }

    @Override
    public ObservableList<Person> getPersonList() {
        ObservableList<Person> tempList = FXCollections.observableArrayList();
        for (Ward ward : wards) {
            tempList.addAll(ward.getStaffsAsUnmodifiableObservableList());
            tempList.addAll(ward.getPatientsAsUnmodifiableObservableList());
        }
        return FXCollections.unmodifiableObservableList(tempList);
    }

    @Override
    public ObservableList<Ward> getWardList() {
        return wards.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Patientist // instanceof handles nulls
                && wards.equals(((Patientist) other).wards));
    }

    @Override
    public int hashCode() {
        return wards.hashCode();
    }
}
