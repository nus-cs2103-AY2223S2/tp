package seedu.careflow.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.careflow.commons.core.GuiSettings;
import seedu.careflow.model.drug.Drug;
import seedu.careflow.model.hospital.Hospital;
import seedu.careflow.model.person.Patient;
import seedu.careflow.model.readonly.ReadOnlyDrugInventory;
import seedu.careflow.model.readonly.ReadOnlyHospitalRecords;
import seedu.careflow.model.readonly.ReadOnlyPatientRecord;

/**
 * The API of the Model component.
 */
public interface CareFlowModel {
    /**
     * {@code Predicate} that always evaluates to true.
     */
    Predicate<Patient> PREDICATE_SHOW_ALL_PATIENTS = unused -> true;
    Predicate<Drug> PREDICATE_SHOW_ALL_DRUGS = unused -> true;

    /**
     * Replaces user prefs data with data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' patient record file path.
     */
    Path getPatientRecordFilePath();

    /**
     * Returns the user prefs' drug inventory file path.
     */
    Path getDrugInventoryFilePath();

    /**
     * Sets the user prefs' patient record file path.
     */
    void setPatientRecordFilePath(Path patientRecordFilePath);

    /**
     * Sets the user prefs' drug inventory file path.
     */
    void setDrugInventoryFilePath(Path drugInventoryFilePath);

    /**
     * Replaces patient record data with the data in {@code patientRecord}.
     */
    void setPatientRecord(ReadOnlyPatientRecord patientRecord);

    /**
     * Replaces drug inventory data with the data in {@code drugInventory}.
     */
    void setDrugInventory(ReadOnlyDrugInventory drugInventory);

    /**
     * Returns the patient record.
     */
    ReadOnlyPatientRecord getPatientRecord();

    /**
     * Returns the drug inventory.
     */
    ReadOnlyDrugInventory getDrugInventory();

    /**
     * Return the HospitalRecords
     */
    ReadOnlyHospitalRecords getHospitalRecords();

    /**
     * Returns true if a patient with the same identity as {@code patient} exists in the patient record.
     */
    boolean hasPatient(Patient patient);

    /**
     * Returns true if a drug with the same identity as {@code drug} exists in the drug inventory.
     */
    boolean hasDrug(Drug drug);

    /**
     * Deletes the given patient.
     * The patient must exist in the patient record.
     */
    void deletePatient(Patient target);

    /**
     * Deletes the given drug.
     * The drug must exist in the drug inventory.
     */
    void deleteDrug(Drug target);

    /**
     * Adds the given patient.
     * {@code patient} must not already exist in the patient record.
     */
    void addPatient(Patient patient);

    /**
     * Adds the given drug.
     * {@code drug} must not already exist in the drug inventory.
     */
    void addDrug(Drug drug);

    /**
     * Replaces the given patient {@code target} with {@code editedPatient}.
     * {@code target} must exist in the patient record.
     * The patient identity of {@code editedPatient} must not be the same as another existing patient in the patient
     * record.
     */
    void setPatient(Patient target, Patient editedPatient);

    /**
     * Replaces the given drug {@code target} with {@code editedDrug}.
     * {@code target} must exist in the drug inventory.
     * The drug identity of {@code editedDrug} must not be the same as another existing drug in the drug inventory.
     */
    void setDrug(Drug target, Drug editedDrug);

    /** Returns an unmodifiable view of the filtered patient list */
    ObservableList<Patient> getFilteredPatientList();

    /** Returns an unmodifiable view of the filtered drug list */
    ObservableList<Drug> getFilteredDrugList();

    /** Return an unmodifiable view of hospital records*/
    ObservableList<Hospital> getHospitalList();

    /**
     * Updates the filter of the filtered patient list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPatientList(Predicate<Patient> predicate);

    /**
     * Updates the filter of the filtered drug list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredDrugList(Predicate<Drug> predicate);
}
