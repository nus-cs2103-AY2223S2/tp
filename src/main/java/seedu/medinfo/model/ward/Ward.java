package seedu.medinfo.model.ward;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.medinfo.model.patient.Patient;
import seedu.medinfo.model.patient.UniquePatientList;
import seedu.medinfo.model.ward.exceptions.WardFullException;

/**
 * Represents a ward which stores patients.
 */
public class Ward {

    public final WardName value;

    private Capacity capacity;

    private UniquePatientList patients;

    /**
     * Constructs a {@code Ward}.
     *
     * @param name A valid name.
     */
    public Ward(WardName name) {
        requireNonNull(name);
        this.value = name;
        this.capacity = new Capacity(10);
        patients = new UniquePatientList();
    }

    /**
     * Constructs a {@code Ward}.
     *
     * @param name     A valid name.
     * @param capacity A specified capacity.
     */
    public Ward(WardName name, Capacity capacity) {
        requireNonNull(name);
        this.value = name;
        this.capacity = capacity;
        patients = new UniquePatientList();
    }

    /**
     * Ward factory constructor with string for comparing.
     *
     * @param name Name of the ward.
     * @return placeholder Ward for comparison.
     */
    public static Ward wardWithName(String name) {
        WardName wardName = new WardName(name);
        return new Ward(wardName);
    }

    /**
     * Edit capacity of this ward
     *
     * @param capacity
     * @return Ward with edited capacity
     */
    public Ward withCapacity(int capacity) {
        this.capacity = new Capacity(capacity);
        return this;
    }

    /**
     * Returns true if a given occupany can fit in the
     * ward's capacity
     *
     * @param occupancy Occupancy to check.
     * @return If occupancy can fit in the capacity.
     */
    public boolean canSupport(int occupancy) {
        return capacity.getValue() >= occupancy;
    }

    /**
     * Returns true if a given String is a valid Ward name.
     *
     * @param test String to check.
     * @return If the String is a valid Ward name.
     */
    public static boolean isValidWardName(String test) {
        return WardName.isValidWardName(test);
    }

    public WardName getName() {
        return value;
    }

    public String getNameString() {
        return value.wardName;
    }

    public Capacity getCapacity() {
        return capacity;
    }

    public String getCapacityString() {
        return capacity.toString();
    }

    public int getOccupancy() {
        return patients.size();
    }

    public String getOccupancyString() {
        return "Current occupancy: " + getOccupancy() + "/" + capacity.getValue();
    }

    public boolean isSameWard(Ward other) {
        return this.equals(other);
    }

    /**
     * Replaces the contents of the patient list with {@code patients}.
     * {@code patients} must not contain duplicate patients.
     *
     * @param patients List of patients to replace with.
     */
    public void setPatients(List<Patient> patients) {
        this.patients.setPatients(patients);
    }

    /**
     * Adds a patient to the ward.
     * The patient must not already exist in the medinfo book.
     *
     * @param patient Patient to be added.
     */
    public void addPatient(Patient patient) throws WardFullException {
        requireNonNull(patient);
        if (patients.size() == capacity.getValue()) {
            throw new WardFullException(value.toString());
        }
        patients.add(patient);
    }

    /**
     * Replaces the given patient {@code target} in the list with
     * {@code editedPatient}.
     * {@code target} must exist in the medinfo book.
     * The patient identity of {@code editedPatient} must not be the same as another
     * existing patient in the medinfo book.
     *
     * @param target Target Patient.
     * @param editedPatient Patient to edit {@code target} to.
     */
    public void setPatient(Patient target, Patient editedPatient) {
        requireNonNull(editedPatient);
        patients.setPatient(target, editedPatient);
    }

    /**
     * Removes {@code key} from this {@code Ward}.
     * {@code key} must exist in the medinfo book.
     *
     * @param patient Patient to be removed.
     */
    public void removePatient(Patient patient) {
        patients.remove(patient);
    }

    @Override
    public String toString() {
        return patients.asUnmodifiableObservableList().size() + " patients";
    }

    public ObservableList<Patient> getPatientList() {
        return patients.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Ward // instanceof handles nulls
                        && value.equals(((Ward) other).value));
    }

    @Override
    public int hashCode() {
        return patients.hashCode();
    }

}
