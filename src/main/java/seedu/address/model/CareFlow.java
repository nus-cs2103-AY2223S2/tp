package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.drug.Drug;
import seedu.address.model.person.Person;
import seedu.address.model.person.patient.Patient;
import seedu.address.model.readonly.ReadOnlyDrugInventory;
import seedu.address.model.readonly.ReadOnlyPatientRecord;

import java.util.List;
/**
 * Wraps all data at the CareFlow level
 * Duplicates are not allowed
 */
public class CareFlow {

    private final PatientRecord patientRecord;
    private final DrugInventory drugInventory;

    {
        patientRecord = new PatientRecord();
        drugInventory = new DrugInventory();
    }

    //// constructors

    public CareFlow() {}

    public CareFlow(ReadOnlyPatientRecord toBeCopiedP, ReadOnlyDrugInventory toBeCopiedD) {
        this();
        resetPatientData(toBeCopiedP);
        resetDrugData(toBeCopiedD);
    }

    public CareFlow(ReadOnlyPatientRecord toBeCopied) {
        this();
        resetPatientData(toBeCopied);
    }
    public CareFlow(ReadOnlyDrugInventory toBeCopied) {
        this();
        resetDrugData(toBeCopied);
    }

    public ReadOnlyPatientRecord getPatientRecord() {
        return patientRecord;
    }

    public ReadOnlyDrugInventory getDrugInventory() {
        return drugInventory;
    }
    //// list overwrite operations

    public void setPatients(List<Patient> patients) {
        patientRecord.setPatients(patients);
    }

    public void setDrugs(List<Drug> drugs) {
        drugInventory.setDrugs(drugs);
    }

    /**
     * Resets the existing data of CareFlow patient record with {@code newData}.
     */
    public void resetPatientData(ReadOnlyPatientRecord newData) {
        setPatients(newData.getPatientList());
    }

    /**
     * Resets the existing data of CareFlow drug inventory with {@code newData}.
     */
    public void resetDrugData(ReadOnlyDrugInventory newData) {
        setDrugs(newData.getDrugList());
    }

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in the patient record.
     */
    public boolean hasPatient(Patient patient) {
        return patientRecord.hasPatient(patient);
    }

    /**
     * Returns true if a drug with the same identity as {@code drug} exists in the drug inventory.
     */
    public boolean hasDrug(Drug drug) {
        return drugInventory.hasDrug(drug);
    }

    /**
     * Adds a patient to the address book.
     * The patient must not already exist in the patient record.
     */
    public void addPatient(Patient p) {
        patientRecord.addPatient(p);
    }

    /**
     * Adds a drug to the drug inventory.
     * The drug must not already exist in the drug inventory.
     */
    public void addDrug(Drug d) {
        drugInventory.addDrug(d);
    }

    /**
     * Replaces the given patient {@code target} in the list with {@code editedPatient}.
     * {@code target} must exist in the patient record.
     * The patient identity of {@code editedPatient} must not be the same as another existing patient in the patient
     * record.
     */
    public void setPatient(Patient target, Patient editedPatient) {
        patientRecord.setPatient(target, editedPatient);
    }

    /**
     * Replaces the given drug {@code target} in the list with {@code editedDrug}.
     * {@code target} must exist in the drug inventory.
     * The drug identity of {@code editedDrug} must not be the same as another existing drug in the drug
     * inventory.
     */
    public void setDrug(Drug target, Drug editedDrug) {
        drugInventory.setDrug(target, editedDrug);
    }

    /**
     * Removes {@code key} from patient record.
     * {@code key} must exist in the patient record.
     */
    public void removePatient(Patient key) {
        patientRecord.removePatient(key);
    }

    /**
     * Removes {@code key} from drug inventory.
     * {@code key} must exist in the drug inventory.
     */
    public void removeDrug(Drug key) {
        drugInventory.removeDrug(key);
    }

    //// util methods

    @Override
    public String toString() {
        return patientRecord.toString() + "\n" + drugInventory.toString();
    }

    public ObservableList<Patient> getPatientList() {
        return patientRecord.getPatientList();
    }

    public ObservableList<Drug> getDrugList() {
        return drugInventory.getDrugList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof CareFlow
                && patientRecord.equals(((CareFlow) other).patientRecord)
                && drugInventory.equals(((CareFlow) other).drugInventory));
    }

    @Override
    public int hashCode() {
        return ((Integer)(patientRecord.hashCode() + drugInventory.hashCode())).hashCode();
    }

}
