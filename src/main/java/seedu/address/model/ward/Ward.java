package seedu.address.model.ward;
import javafx.collections.ObservableList;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.UniquePatientList;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Patient's ward in MedInfo.
 */
public class Ward implements ReadOnlyAddressBook{

    public static final String MESSAGE_CONSTRAINTS = "Wards should only contain alphanumeric characters and spaces, "
        + "and it should not be blank";

    /*
     * The first character of the ward must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final Name name;
    public final Capacity capacity;

    public UniquePatientList patients;
    /**
     * Constructs a {@code Ward}.
     *
     * @param name A valid name.
     */
    public Ward(Name name, Capacity capacity) {
        requireNonNull(name);
        checkArgument(isValidWard(name), MESSAGE_CONSTRAINTS);
        this.name = name;
        this.capacity = capacity;
        patients = new UniquePatientList();
    }

    /**
     * Returns true if a given string is a valid ward.
     */
    public static boolean isValidWard(Name name) {
        return name.toString().matches(VALIDATION_REGEX);
    }

    public String getName() {
        return name.toString();
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the patient list with {@code patients}.
     * {@code patients} must not contain duplicate patients.
     */
    public void setPatients(List<Patient> patients) {
        this.patients.setPatients(patients);
    }

    //// patient-level operations

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in
     * the ward.
     */
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
        return patients.contains(patient);
    }

    /**
     * Returns true if a patient with the same NRIC as {@code patient} exists in
     * the ward.
     */
    public boolean hasPatientNric(Patient patient) {
        requireNonNull(patient);
        return patients.containsNric(patient);
    }

    /**
     * Adds a patient to the ward.
     * The patient must not already exist in the address book.
     */
    public void addPatient(Patient p) {
        patients.add(p);
    }

    /**
     * Replaces the given patient {@code target} in the list with
     * {@code editedPatient}.
     * {@code target} must exist in the address book.
     * The patient identity of {@code editedPatient} must not be the same as another
     * existing patient in the address book.
     */
    public void setPatient(Patient target, Patient editedPatient) {
        requireNonNull(editedPatient);

        patients.setPatient(target, editedPatient);
    }

    /**
     * Removes {@code key} from this {@code Ward}.
     * {@code key} must exist in the address book.
     */
    public void removePatient(Patient key) {
        patients.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return patients.asUnmodifiableObservableList().size() + " patients";
    }

    @Override
    public ObservableList<Patient> getPatientList() {
        return patients.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Ward // instanceof handles nulls
            && patients.equals(((Ward) other).patients));
    }

    @Override
    public int hashCode() {
        return patients.hashCode();
    }
}
