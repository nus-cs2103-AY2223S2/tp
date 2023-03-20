package seedu.connectus.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.connectus.commons.core.LogsCenter;
import seedu.connectus.commons.exceptions.DataConversionException;
import seedu.connectus.model.ReadOnlyConnectUs;
import seedu.connectus.model.ReadOnlyUserPrefs;
import seedu.connectus.model.UserPrefs;

/**
 * Manages storage of ConnectUS data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ConnectUsStorage connectUsStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ConnectUsStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ConnectUsStorage connectUsStorage, UserPrefsStorage userPrefsStorage) {
        this.connectUsStorage = connectUsStorage;
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


    // ================ ConnectUs methods ==============================

    @Override
    public Path getConnectUsFilePath() {
        return connectUsStorage.getConnectUsFilePath();
    }

    @Override
    public Optional<ReadOnlyConnectUs> readConnectUs() throws DataConversionException, IOException {
        return readConnectUs(connectUsStorage.getConnectUsFilePath());
    }

    @Override
    public Optional<ReadOnlyConnectUs> readConnectUs(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return connectUsStorage.readConnectUs(filePath);
    }

    @Override
    public void saveConnectUs(ReadOnlyConnectUs connectUs) throws IOException {
        saveConnectUs(connectUs, connectUsStorage.getConnectUsFilePath());
    }

    @Override
    public void saveConnectUs(ReadOnlyConnectUs connectUs, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        connectUsStorage.saveConnectUs(connectUs, filePath);
    }

}
