package seedu.careflow.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.careflow.commons.core.LogsCenter;
import seedu.careflow.commons.exceptions.DataConversionException;
import seedu.careflow.model.ReadOnlyUserPrefs;
import seedu.careflow.model.UserPrefs;
import seedu.careflow.model.readonly.ReadOnlyDrugInventory;
import seedu.careflow.model.readonly.ReadOnlyPatientRecord;

/**
 * Manages storage of CareFlow data in local storage.
 */
public class CareFlowStorageManager implements CareFlowStorage {
    private static final Logger logger = LogsCenter.getLogger(CareFlowStorageManager.class);
    private final PatientRecordStorage patientRecordStorage;
    private final DrugInventoryStorage drugInventoryStorage;
    private final UserPrefsStorage userPrefsStorage;

    /**
     * Creates a CareFlowStorageManager with {@code patientRecordStorage,
     * @code drugInventoryStorage, @code userPrefsStorage}
     */
    public CareFlowStorageManager(PatientRecordStorage patientRecordStorage, DrugInventoryStorage drugInventoryStorage,
                                  UserPrefsStorage userPrefsStorage) {
        super();
        this.patientRecordStorage = patientRecordStorage;
        this.drugInventoryStorage = drugInventoryStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }

    @Override
    public Path getPatientRecordFilePath() {
        return patientRecordStorage.getPatientRecordFilePath();
    }

    @Override
    public Path getDrugInventoryFilePath() {
        return drugInventoryStorage.getDrugInventoryFilePath();
    }

    @Override
    public Optional<ReadOnlyPatientRecord> readPatientRecord() throws DataConversionException, IOException {
        return readPatientRecord(patientRecordStorage.getPatientRecordFilePath());
    }

    @Override
    public Optional<ReadOnlyPatientRecord> readPatientRecord(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return patientRecordStorage.readPatientRecord(filePath);
    }

    @Override
    public Optional<ReadOnlyDrugInventory> readDrugInventory() throws DataConversionException, IOException {
        return readDrugInventory(drugInventoryStorage.getDrugInventoryFilePath());
    }

    @Override
    public Optional<ReadOnlyDrugInventory> readDrugInventory(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return drugInventoryStorage.readDrugInventory(filePath);
    }

    @Override
    public void savePatientRecord(ReadOnlyPatientRecord patientRecord) throws IOException {
        savePatientRecord(patientRecord, patientRecordStorage.getPatientRecordFilePath());
    }

    @Override
    public void savePatientRecord(ReadOnlyPatientRecord patientRecord, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        patientRecordStorage.savePatientRecord(patientRecord, filePath);
    }

    @Override
    public void saveDrugInventory(ReadOnlyDrugInventory drugInventory) throws IOException {
        saveDrugInventory(drugInventory, drugInventoryStorage.getDrugInventoryFilePath());
    }

    @Override
    public void saveDrugInventory(ReadOnlyDrugInventory drugInventory, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        drugInventoryStorage.saveDrugInventory(drugInventory, filePath);
    }
}
