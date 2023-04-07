package taa.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import taa.commons.core.LogsCenter;
import taa.commons.exceptions.DataConversionException;
import taa.model.ReadOnlyUserPrefs;
import taa.model.UserPrefs;

/**
 * Manages storage of ClassList data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private final TaaStorage taaStorage;
    private final UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(TaaStorage taaStorage, UserPrefsStorage userPrefsStorage) {
        this.taaStorage = taaStorage;
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


    // ================ ClassList methods ==============================

    @Override
    public Path getTaaDataFilePath() {
        return taaStorage.getTaaDataFilePath();
    }

    @Override
    public Optional<TaaData> readTaaData() throws DataConversionException, IOException {
        return readTaaData(taaStorage.getTaaDataFilePath());
    }

    @Override
    public Optional<TaaData> readTaaData(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return taaStorage.readTaaData(filePath);
    }

    @Override
    public void saveTaaData(TaaData taaData) throws IOException {
        saveTaaData(taaData, taaStorage.getTaaDataFilePath());
    }

    @Override
    public void saveTaaData(TaaData taaData, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        taaStorage.saveTaaData(taaData, filePath);
    }

}
