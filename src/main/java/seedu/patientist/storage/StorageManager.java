package seedu.patientist.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.patientist.commons.core.LogsCenter;
import seedu.patientist.commons.exceptions.DataConversionException;
import seedu.patientist.model.ReadOnlyPatientist;
import seedu.patientist.model.ReadOnlyUserPrefs;
import seedu.patientist.model.UserPrefs;

/**
 * Manages storage of Patientist data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private PatientistStorage patientistStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code PatientistStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(PatientistStorage patientistStorage, UserPrefsStorage userPrefsStorage) {
        this.patientistStorage = patientistStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

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


    // ================ Patientist methods ==============================

    @Override
    public Path getPatientistFilePath() {
        return patientistStorage.getPatientistFilePath();
    }

    @Override
    public Optional<ReadOnlyPatientist> readPatientist() throws DataConversionException, IOException {
        return readPatientist(patientistStorage.getPatientistFilePath());
    }

    @Override
    public Optional<ReadOnlyPatientist> readPatientist(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return patientistStorage.readPatientist(filePath);
    }

    @Override
    public void savePatientist(ReadOnlyPatientist patientist) throws IOException {
        savePatientist(patientist, patientistStorage.getPatientistFilePath());
    }

    @Override
    public void savePatientist(ReadOnlyPatientist patientist, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        patientistStorage.savePatientist(patientist, filePath);
    }

}
