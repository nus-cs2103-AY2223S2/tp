package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyExecutiveProDb;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of ExecutiveProDb data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ExecutiveProStorage executiveProStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ExecutiveProStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ExecutiveProStorage executiveProStorage, UserPrefsStorage userPrefsStorage) {
        this.executiveProStorage = executiveProStorage;
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


    // ================ ExecutiveProDb methods ==============================

    @Override
    public Path getExecutiveProDbFilePath() {
        return executiveProStorage.getExecutiveProDbFilePath();
    }

    @Override
    public Optional<ReadOnlyExecutiveProDb> readExecutiveProDb() throws DataConversionException, IOException {
        return readExecutiveProDb(executiveProStorage.getExecutiveProDbFilePath());
    }

    @Override
    public Optional<ReadOnlyExecutiveProDb> readExecutiveProDb(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return executiveProStorage.readExecutiveProDb(filePath);
    }

    @Override
    public void saveExecutiveProDb(ReadOnlyExecutiveProDb executiveProDb) throws IOException {
        saveExecutiveProDb(executiveProDb, executiveProStorage.getExecutiveProDbFilePath());
    }

    @Override
    public void saveExecutiveProDb(ReadOnlyExecutiveProDb executiveProDb, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        executiveProStorage.saveExecutiveProDb(executiveProDb, filePath);
    }

}
