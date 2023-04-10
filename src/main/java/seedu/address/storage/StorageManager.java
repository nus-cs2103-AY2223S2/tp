package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTracker;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of Le Tracker data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TrackerStorage trackerStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code TrackerStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(TrackerStorage trackerStorage, UserPrefsStorage userPrefsStorage) {
        this.trackerStorage = trackerStorage;
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

    // ================= Tracker methods ===============================

    @Override
    public Path getTrackerFilePath() {
        return trackerStorage.getTrackerFilePath();
    }

    @Override
    public Optional<ReadOnlyTracker> readTracker() throws DataConversionException, IOException {
        return trackerStorage.readTracker(trackerStorage.getTrackerFilePath());
    }

    @Override
    public Optional<ReadOnlyTracker> readTracker(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return trackerStorage.readTracker(filePath);
    }

    @Override
    public void saveTracker(ReadOnlyTracker tracker) throws IOException {
        saveTracker(tracker, trackerStorage.getTrackerFilePath());
    }

    @Override
    public void saveTracker(ReadOnlyTracker tracker, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        trackerStorage.saveTracker(tracker, filePath);
    }
}
