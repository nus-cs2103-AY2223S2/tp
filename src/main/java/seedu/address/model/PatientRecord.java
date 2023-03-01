package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.patient.Patient;
import seedu.address.model.person.patient.UniquePatientList;
import seedu.address.model.readonly.ReadOnlyPatientRecord;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class PatientRecord implements ReadOnlyPatientRecord {
   private final UniquePatientList patients;

    {
        patients = new UniquePatientList();
    }

    public PatientRecord() {}

    public PatientRecord(ReadOnlyPatientRecord toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    public void setPatients(List<Patient> patients) {
//        this.patients.setPatients(patients);
    }

    /**
     * Resets the existing data of CareFlow drug inventory with {@code newData}.
     */
    public void resetData(ReadOnlyPatientRecord newData) {
        requireNonNull(newData);
        setPatients(newData.getPatientList());
    }

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in the patient record.
     */
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
//        return patients.contains(patient);
        return true; // to be removed when method is ready
    }

    /**
     * Adds a patient to the address book.
     * The patient must not already exist in the patient record.
     */
    public void addPatient(Patient p) {
//        patients.add(p);
    }

    /**
     * Replaces the given patient {@code target} in the list with {@code editedPatient}.
     * {@code target} must exist in the patient record.
     * The patient identity of {@code editedPatient} must not be the same as another existing patient in the patient
     * record.
     */
    public void setPatient(Person target, Person editedPatient) {
        requireNonNull(editedPatient);

//        patients.setPatient(target, editedPatient);
    }

    /**
     * Removes {@code key} from patient record.
     * {@code key} must exist in the patient record.
     */
    public void removePatient(Patient key) {
//        patients.remove(key);
    }

    @Override
    public String toString() {
//        return patients.asUnmodifiableObservableList().size() + " patients";
        return "";
    }

    public ObservableList<Patient> getPatientList() {
//        return patients.asUnmodifiableObservableList();
        return null;
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
