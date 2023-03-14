package seedu.careflow.model;

import static java.util.Objects.requireNonNull;
import static seedu.careflow.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.careflow.commons.core.GuiSettings;
import seedu.careflow.commons.core.LogsCenter;
import seedu.careflow.model.drug.Drug;
import seedu.careflow.model.hospital.Hospital;
import seedu.careflow.model.person.Patient;
import seedu.careflow.model.readonly.ReadOnlyDrugInventory;
import seedu.careflow.model.readonly.ReadOnlyHospitalRecords;
import seedu.careflow.model.readonly.ReadOnlyPatientRecord;

/**
 * Represents the in-memory model of the CareFlow data.
 */
public class CareFlowModelManager implements CareFlowModel {
    private static final Logger logger = LogsCenter.getLogger(CareFlowModelManager.class);
    private static final String LOGGER_MESSAGE = "Initialising with patient record: %s, drug inventory: %s and user "
            + "prefs %s";

    private final CareFlow careFlow;
    private final UserPrefs userPrefs;
    private final FilteredList<Patient> filteredPatients;
    private final FilteredList<Drug> filteredDrugs;
    private final FilteredList<Hospital> filteredHospitals;

    /**
     * Initializes a CareFlowModelManager with the given patientRecord, drugInventory and userPrefs.
     * @param patientRecord patient records
     * @param drugInventory drug inventory
     * @param hospitalRecord hospital records
     * @param userPrefs user preferences
     */
    public CareFlowModelManager(ReadOnlyPatientRecord patientRecord, ReadOnlyDrugInventory drugInventory,
                                ReadOnlyHospitalRecords hospitalRecord, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(drugInventory, patientRecord, userPrefs);
        logger.fine(String.format(LOGGER_MESSAGE, patientRecord, drugInventory, userPrefs));

        this.careFlow = new CareFlow(patientRecord, drugInventory, hospitalRecord);
        this.userPrefs = new UserPrefs(userPrefs);
        this.filteredPatients = new FilteredList<>(this.careFlow.getPatientList());
        this.filteredDrugs = new FilteredList<>(this.careFlow.getDrugList());
        setFixedHospitalList();
        this.filteredHospitals = new FilteredList<>(this.careFlow.getHospitalList());
    }

    public CareFlowModelManager() {
        this(new PatientRecord(), new DrugInventory() , new HospitalRecord(), new UserPrefs());
    }

    //=========== UserPrefs ================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getPatientRecordFilePath() {
        return userPrefs.getPatientRecordFilePath();
    }
    @Override
    public void setPatientRecordFilePath(Path patientRecordFilePath) {
        requireNonNull(patientRecordFilePath);
        userPrefs.setPatientRecordFilePath(patientRecordFilePath);
    }

    @Override
    public Path getDrugInventoryFilePath() {
        return userPrefs.getDrugInventoryFilePath();
    }

    @Override
    public void setDrugInventoryFilePath(Path drugInventoryFilePath) {
        requireNonNull(drugInventoryFilePath);
        userPrefs.setDrugInventoryFilePath(drugInventoryFilePath);
    }

    //=========== Patient Record & Drug Inventory =========================================================

    @Override
    public void setPatientRecord(ReadOnlyPatientRecord patientRecord) {
        this.careFlow.resetPatientData(patientRecord);
    }

    @Override
    public void setDrugInventory(ReadOnlyDrugInventory drugInventory) {
        this.careFlow.resetDrugData(drugInventory);
    }

    @Override
    public ReadOnlyPatientRecord getPatientRecord() {
        return careFlow.getPatientRecord();
    }

    @Override
    public ReadOnlyDrugInventory getDrugInventory() {
        return careFlow.getDrugInventory();
    }

    @Override
    public ReadOnlyHospitalRecords getHospitalRecords() {
        return careFlow.getHospitalRecords();
    }

    @Override
    public boolean hasPatient(Patient patient) {
        requireNonNull(patient);
        return careFlow.hasPatient(patient);
    }

    @Override
    public boolean hasDrug(Drug drug) {
        requireNonNull(drug);
        return careFlow.hasDrug(drug);
    }

    @Override
    public void deletePatient(Patient target) {
        careFlow.removePatient(target);
    }

    @Override
    public void deleteDrug(Drug target) {
        careFlow.removeDrug(target);
    }

    @Override
    public void addPatient(Patient patient) {
        careFlow.addPatient(patient);
    }

    @Override
    public void addDrug(Drug drug) {
        careFlow.addDrug(drug);
    }

    @Override
    public void setPatient(Patient target, Patient editedPatient) {
        careFlow.setPatient(target, editedPatient);
    }

    @Override
    public void setDrug(Drug target, Drug editedDrug) {
        careFlow.setDrug(target, editedDrug);
    }

    @Override
    public ObservableList<Patient> getFilteredPatientList() {
        return filteredPatients;
    }

    @Override
    public ObservableList<Drug> getFilteredDrugList() {
        return filteredDrugs;
    }


    @Override
    public void updateFilteredPatientList(Predicate<Patient> predicate) {
        requireNonNull(predicate);
        filteredPatients.setPredicate(predicate);
    }

    @Override
    public void updateFilteredDrugList(Predicate<Drug> predicate) {
        requireNonNull(predicate);
        filteredDrugs.setPredicate(predicate);
    }

    //=========== Hospital Hotlines =========================================================

    /**
     * Set a fixed list of hospital information
     */
    public void setFixedHospitalList() {
        careFlow.addHospital(new Hospital("KK Women's and Children's Hospital", "+65 62255554"));
        careFlow.addHospital(new Hospital("Changi General Hospital", "+65 67888833"));
        careFlow.addHospital(new Hospital("Khoo Teck Puat Hospital", "+65 65558000"));
        careFlow.addHospital(new Hospital("Tan Tock Seng Hospital", "+65 62566011"));
    }

    /**
     * @return fixed set of Hospital Hotlines
     */
    @Override
    public ObservableList<Hospital> getHospitalList() {
        return filteredHospitals;
    }
}
