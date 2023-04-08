package seedu.medinfo.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.medinfo.commons.core.LogsCenter;
import seedu.medinfo.commons.exceptions.DataConversionException;
import seedu.medinfo.logic.commands.exceptions.CommandException;
import seedu.medinfo.model.ReadOnlyMedInfo;
import seedu.medinfo.model.ReadOnlyUserPrefs;
import seedu.medinfo.model.UserPrefs;

/**
 * Manages storage of MedInfo data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private MedInfoStorage medInfoStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code MedInfoStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(MedInfoStorage medInfoStorage, UserPrefsStorage userPrefsStorage) {
        this.medInfoStorage = medInfoStorage;
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


    // ================ MedInfo methods ==============================

    @Override
    public Path getMedInfoFilePath() {
        return medInfoStorage.getMedInfoFilePath();
    }

    @Override
    public Optional<ReadOnlyMedInfo> readMedInfo() throws DataConversionException, IOException, CommandException {
        return readMedInfo(medInfoStorage.getMedInfoFilePath());
    }

    @Override
    public Optional<ReadOnlyMedInfo> readMedInfo(Path filePath) throws DataConversionException, IOException,
        CommandException {
        logger.fine("Attempting to read data from file: " + filePath);
        return medInfoStorage.readMedInfo(filePath);
    }

    @Override
    public void saveMedInfo(ReadOnlyMedInfo addressBook) throws IOException {
        saveMedInfo(addressBook, medInfoStorage.getMedInfoFilePath());
    }

    @Override
    public void saveMedInfo(ReadOnlyMedInfo addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        medInfoStorage.saveMedInfo(addressBook, filePath);
    }

}
