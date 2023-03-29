package seedu.careflow.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.careflow.model.patient.Patient;
import seedu.careflow.model.patient.UniquePatientList;
import seedu.careflow.model.readonly.ReadOnlyPatientRecord;

/**
 * Wraps all data at the Patient Record level
 */
public class PatientRecord implements ReadOnlyPatientRecord {
    private final UniquePatientList patients;

    {
        patients = new UniquePatientList();
    }

    public PatientRecord() {}

    /**
     * creates a PatientRecord using the Patients in the {@code toBeCopied}
     */
    public PatientRecord(ReadOnlyPatientRecord toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    public void setPatients(List<Patient> patients) {
        this.patients.setPatients(patients);
    }

    /**
     * Resets the existing data of CareFlow drug inventory with {@code newData}.
     */
    public void resetData(ReadOnlyPatientRecord newData) {
        requireNonNull(newData);
        setPatients(newData.getPatientList());
    }

    /**
     * Returns true if a patient with the same name as {@code patient} exists in the patient record.
     */
    public boolean hasSamePatientName(Patient patient) {
        requireNonNull(patient);
        return patients.containName(patient);
    }

    /**
     * Returns true if a patient with the same ic as {@code patient} exists in the patient record.
     */
    public boolean hasSamePatientIc(Patient patient) {
        requireNonNull(patient);
        return patients.containIc(patient);
    }

    /**
     * Adds a patient to the address book.
     * The patient must not already exist in the patient record.
     */
    public void addPatient(Patient p) {
        patients.add(p);
    }

    /**
     * Replaces the given patient {@code target} in the list with {@code editedPatient}.
     * {@code target} must exist in the patient record.
     * The patient identity of {@code editedPatient} must not be the same as another existing patient in the patient
     * record.
     */
    public void setPatient(Patient target, Patient editedPatient) {
        requireNonNull(editedPatient);
        patients.setPatient(target, editedPatient);
    }

    /**
     * Removes {@code key} from patient record.
     * {@code key} must exist in the patient record.
     */
    public void removePatient(Patient key) {
        patients.remove(key);
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
        return other == this
                || (other instanceof PatientRecord
                && patients.equals(((PatientRecord) other).patients));
    }

    @Override
    public int hashCode() {
        return patients.hashCode();
    }
}
