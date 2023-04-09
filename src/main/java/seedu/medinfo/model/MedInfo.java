package seedu.medinfo.model;

import static java.util.Objects.requireNonNull;
import static seedu.medinfo.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.medinfo.logic.commands.exceptions.CommandException;
import seedu.medinfo.model.patient.Patient;
import seedu.medinfo.model.patient.UniquePatientList;
import seedu.medinfo.model.ward.UniqueWardList;
import seedu.medinfo.model.ward.Ward;
import seedu.medinfo.model.ward.exceptions.WardFullException;
import seedu.medinfo.model.ward.exceptions.WardNotFoundException;

/**
 * Wraps all data at the medinfo-book level
 * Duplicates are not allowed (by .isSamePatient comparison)
 */
public class MedInfo implements ReadOnlyMedInfo {

    private final UniquePatientList patients;
    private final UniqueWardList wards;

    /*
     * The 'unusual' code block below is a non-static initialization block,
     * sometimes used to avoid duplication
     * between constructors. See
     * https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other
     * ways to avoid duplication
     * among constructors.
     */ 
    {
        patients = new UniquePatientList();
        wards = new UniqueWardList();
    }

    public MedInfo() {
    }

    /**
     * Creates an MedInfo using the Patients in the {@code toBeCopied}
     */
    public MedInfo(ReadOnlyMedInfo toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * FOR TESTING
     * Replaces the contents of the patient list with a COPY of {@code patients}.
     * {@code patients} must not contain duplicate patients.
     */
    public void setPatients(List<Patient> patients) {
        List<Patient> patientsCopy = new ArrayList<>();
        for (Patient patient : patients) {
            Patient toCopy = new Patient(patient.getNric(), patient.getName(),
                    patient.getStatus(), patient.getWardName(), patient.getDischarge());
            patientsCopy.add(toCopy);
        }
        this.patients.setPatients(patientsCopy);
    }

    /**
     * FOR TESTING
     * Replaces the contents of the ward list with {@code wards}.
     * {@code wards} must not contain duplicate wards.
     */
    public void setWards(List<Ward> wards) {
        this.wards.setWards(wards);
    }

    /**
     * Resets the existing data of this {@code MedInfo} with {@code newData}.
     */
    public void resetData(ReadOnlyMedInfo newData) {
        requireNonNull(newData);
        setPatients(newData.getPatientList());
        setWards(newData.getWardList());
    }

    //// patient-level operations

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in
     * the medinfo book.
     */
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
        return patients.contains(patient);
    }

    /**
     * Returns true if a patient with the same NRIC as {@code patient} exists in
     * the medinfo book.
     */
    public boolean hasPatientNric(Patient patient) {
        requireNonNull(patient);
        return patients.containsNric(patient);
    }

    /**
     * Adds a patient to the medinfo book.
     * The patient must not already exist in the medinfo book.
     */
    public void addPatient(Patient p) throws CommandException {
        if (!wards.contains(p.getWardNameString())) { // If wardlist does not contain patient's ward, don't add it in.
            throw new WardNotFoundException(p.getWardNameString());
        }
        patients.add(p);
        try {
            wards.addPatient(p);
        } catch (WardFullException e) {
            patients.remove(p);
            throw new CommandException(e.toString(), e);
        }
    }

    /**
     * Replaces the given patient {@code target} in the list with
     * {@code editedPatient}.
     * {@code target} must exist in the medinfo book.
     * The patient identity of {@code editedPatient} must not be the same as another
     * existing patient in the medinfo book.
     */
    public void setPatient(Patient target, Patient editedPatient) throws CommandException{
        requireAllNonNull(target, editedPatient);
        patients.setPatient(target, editedPatient);
        try {
            wards.setPatient(target, editedPatient);
        } catch (WardFullException e) {
            patients.setPatient(target, target);
            throw new CommandException(e.toString(), e);
        }
    }

    /**
     * Removes {@code key} from this {@code MedInfo}.
     * {@code key} must exist in the medinfo book.
     */
    public void removePatient(Patient key) {
        requireNonNull(key);
        patients.remove(key);
        wards.remove(key);
    }

    @Override
    public void sortPatients(Comparator<Patient> comparator) {
        patients.sortPatients(comparator);
    }

    //// ward-level operations

    /**
     * Returns true if a ward with the same identity as {@code ward} exists in
     * the medinfo book.
     */
    public boolean hasWard(Ward ward) {
        requireNonNull(ward);
        return wards.contains(ward);
    }

    /**
     * Adds a ward to the medinfo book.
     * The ward must not already exist in the medinfo book.
     */
    public void addWard(Ward ward) {
        wards.add(ward);
    }

    /**
     * Replaces the given ward {@code target} in the list with
     * {@code editedWard}.
     * {@code target} must exist in the medinfo book.
     * The ward identity of {@code editedWard} must not be the same as another
     * existing ward in the medinfo book.
     */
    public void setWard(Ward target, Ward editedWard) {
        requireNonNull(editedWard);
        wards.setWard(target, editedWard);

        for (Patient patient : patients) {
            if (patient.getWardName().equals(target.getName())) {
                Patient editedPatient = new Patient(patient.getNric(), patient.getName(), patient.getStatus(),
                        editedWard.getName(),
                        patient.getDischarge());
                patients.setPatient(patient, editedPatient);
            }
        }
    }

    /**
     * Removes {@code key} from this {@code MedInfo}.
     * {@code key} must exist in the medinfo book.
     */
    public void removeWard(Ward ward) {
        wards.remove(ward);
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
    public ObservableList<Ward> getWardList() {
        return wards.asUnmodifiableObservableList();
    }

    /**
     * Stats to be displayed on StatusBarFooter.
     * This is the method to modify to choose what you want to display.
     *
     * @return List of information to display.
     */
    @Override
    public List<String> getStatsInfo() {
        List<String> statsInfo = new ArrayList<>();
        String currentOccupancy = "Current Occupancy: " + patients.size() + "/" + wards.capacity();
        String currentCriticals = "Critical Patients: " + patients.numberOfCritical();
        statsInfo.add(currentOccupancy);
        statsInfo.add(currentCriticals);
        return statsInfo;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MedInfo // instanceof handles nulls
                && patients.equals(((MedInfo) other).patients));
    }

    @Override
    public int hashCode() {
        return patients.hashCode();
    }
}
