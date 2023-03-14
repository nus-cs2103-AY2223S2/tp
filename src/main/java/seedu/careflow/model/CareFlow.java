package seedu.careflow.model;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.careflow.model.drug.Drug;
import seedu.careflow.model.hospital.Hospital;
import seedu.careflow.model.person.Patient;
import seedu.careflow.model.readonly.ReadOnlyDrugInventory;
import seedu.careflow.model.readonly.ReadOnlyHospitalRecords;
import seedu.careflow.model.readonly.ReadOnlyPatientRecord;

/**
 * Wraps all data at the CareFlow level
 * Duplicates are not allowed
 */
public class CareFlow {

    private final PatientRecord patientRecord;
    private final DrugInventory drugInventory;
    private final HospitalRecord hospitalRecord;

    {
        patientRecord = new PatientRecord();
        drugInventory = new DrugInventory();
        hospitalRecord = new HospitalRecord();
    }


    /**
     * creates a CareFlow
     */
    public CareFlow() {}

    /**
     * Creates a CareFlow using Patients and Drugs in {@code toBeCopiedP @code toBeCopiedD}
     * @param toBeCopiedP the patient records
     * @param toBeCopiedD the drug inventory
     */
    public CareFlow(ReadOnlyPatientRecord toBeCopiedP, ReadOnlyDrugInventory toBeCopiedD,
                    ReadOnlyHospitalRecords toBeCopiedH) {
        this();
        resetPatientData(toBeCopiedP);
        resetDrugData(toBeCopiedD);
        resetHospitalData(toBeCopiedH);
    }

    public ReadOnlyPatientRecord getPatientRecord() {
        return patientRecord;
    }

    public ReadOnlyDrugInventory getDrugInventory() {
        return drugInventory;
    }

    public ReadOnlyHospitalRecords getHospitalRecords() {
        return hospitalRecord;
    }
    //// list overwrite operations

    public void setPatients(List<Patient> patients) {
        patientRecord.setPatients(patients);
    }

    public void setDrugs(List<Drug> drugs) {
        drugInventory.setDrugs(drugs);
    }

    public void setHospitals(List<Hospital> hospitals) {
        hospitalRecord.setHospitals(hospitals);
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

    public void resetHospitalData(ReadOnlyHospitalRecords newData) {
        setHospitals(newData.getHospitalList());
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

    public void addHospital(Hospital h) {
        hospitalRecord.addHospital(h);
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

    public ObservableList<Hospital> getHospitalList() {
        return hospitalRecord.getHospitalList();
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
        return ((Integer) (patientRecord.hashCode() + drugInventory.hashCode())).hashCode();
    }
}
