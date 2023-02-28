package seedu.address.model;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.drug.Drug;
import seedu.address.model.person.patient.Patient;
import seedu.address.model.readonly.ReadOnlyDrugInventory;
import seedu.address.model.readonly.ReadOnlyPatientRecord;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class CareFlowModelManager implements CareFlowModel{
    private static final Logger logger = LogsCenter.getLogger(CareFlowModelManager.class);

    private final CareFlow careFlow;
    private final UserPrefs userPrefs;
    private final FilteredList<Patient> filteredPatients;
    private final FilteredList<Drug> filteredDrugs;

    private static final String LOGGER_MESSAGE = "Initialising with patient record: %s, drug inventory: %s and user " +
            "prefs %s";

    public CareFlowModelManager(ReadOnlyPatientRecord patientRecord, ReadOnlyDrugInventory drugInventory,
                                ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(drugInventory, patientRecord, userPrefs);
        logger.fine(String.format(LOGGER_MESSAGE, patientRecord, drugInventory, userPrefs));

        this.careFlow = new CareFlow();
        this.userPrefs = new UserPrefs(userPrefs);
        this.filteredPatients = new FilteredList<>(this.careFlow.getPatientList());
        this.filteredDrugs = new FilteredList<>(this.careFlow.getDrugList());
    }

    public CareFlowModelManager() {
        this(new PatientRecord(), new DrugInventory() , new UserPrefs());
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
//        return userPrefs.getPatientRecordFilePath();
        return null;
    }
    @Override
    public void setPatientRecordFilePath(Path patientRecordFilePath) {
        requireNonNull(patientRecordFilePath);
        // implement ltr
        // userPrefs.setPatientRecordFilePath(patientRecordFilePath);
    }

    @Override
    public Path getDrugInventoryFilePath() {
//        return userPrefs.getDrugInventoryFilePath();
        return null;
    }

    @Override
    public void setDrugInventoryFilePath(Path drugInventoryFilePath) {
        requireNonNull(drugInventoryFilePath);
        // implementation here
        // implement ltr
//        userPrefs.setDrugInventoryFilePath(drugInventoryFilePath);
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
    public void decrDrugStorageBy(Drug target, int amount) {
        // to be implemented ltr
    }

    @Override
    public void incrDrugStorageBy(Drug target, int amount) {
        // to be implemented ltr
    }

    @Override
    public void addPatient(Patient patient) {
        careFlow.addPatient(patient);
    }

    @Override
    public  void addDrug(Drug drug) {
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
}
