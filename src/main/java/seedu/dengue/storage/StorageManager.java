package seedu.dengue.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.dengue.commons.core.LogsCenter;
import seedu.dengue.commons.exceptions.DataConversionException;
import seedu.dengue.model.ReadOnlyDengueHotspotTracker;
import seedu.dengue.model.ReadOnlyUserPrefs;
import seedu.dengue.model.UserPrefs;

/**
 * Manages storage of DengueHotspotTracker data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private DengueHotspotStorage dengueHotspotStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code DengueHotspotTrackerStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(DengueHotspotStorage dengueHotspotStorage, UserPrefsStorage userPrefsStorage) {
        this.dengueHotspotStorage = dengueHotspotStorage;
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


    // ================ DengueHotspotTracker methods ==============================

    @Override
    public Path getDengueHotspotTrackerFilePath() {
        return dengueHotspotStorage.getDengueHotspotTrackerFilePath();
    }

    @Override
    public Optional<ReadOnlyDengueHotspotTracker> readDengueHotspotTracker()
            throws DataConversionException, IOException {
        return readDengueHotspotTracker(dengueHotspotStorage.getDengueHotspotTrackerFilePath());
    }

    @Override
    public Optional<ReadOnlyDengueHotspotTracker> readDengueHotspotTracker(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return dengueHotspotStorage.readDengueHotspotTracker(filePath);
    }

    @Override
    public void saveDengueHotspotTracker(ReadOnlyDengueHotspotTracker dengueHotspotTracker) throws IOException {
        saveDengueHotspotTracker(dengueHotspotTracker, dengueHotspotStorage.getDengueHotspotTrackerFilePath());
    }

    @Override
    public void saveDengueHotspotTracker(ReadOnlyDengueHotspotTracker dengueHotspotTracker, Path filePath)
            throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        dengueHotspotStorage.saveDengueHotspotTracker(dengueHotspotTracker, filePath);
    }

}
