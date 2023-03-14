package trackr.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import trackr.commons.core.LogsCenter;
import trackr.commons.exceptions.DataConversionException;
import trackr.model.ReadOnlySupplierList;
import trackr.model.ReadOnlyTaskList;
import trackr.model.ReadOnlyUserPrefs;
import trackr.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TrackrStorage trackrStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code TrackrStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(TrackrStorage trackrStorage, UserPrefsStorage userPrefsStorage) {
        this.trackrStorage = trackrStorage;
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


    // ================ Trackr methods ==============================

    @Override
    public Path getTrackrFilePath() {
        return trackrStorage.getTrackrFilePath();
    }

    @Override
    public Optional<ReadOnlySupplierList> readSupplierList() throws DataConversionException, IOException {
        return readSupplierList(trackrStorage.getTrackrFilePath());
    }

    @Override
    public Optional<ReadOnlySupplierList> readSupplierList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return trackrStorage.readSupplierList(filePath);
    }

    @Override
    public Optional<ReadOnlyTaskList> readTaskList() throws DataConversionException, IOException {
        return readTaskList(trackrStorage.getTrackrFilePath());
    }

    @Override
    public Optional<ReadOnlyTaskList> readTaskList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return trackrStorage.readTaskList(filePath);
    }

    @Override
    public void saveTrackr(ReadOnlySupplierList addressBook, ReadOnlyTaskList taskList) throws IOException {
        saveTrackr(addressBook, taskList, trackrStorage.getTrackrFilePath());
    }

    @Override
    public void saveTrackr(ReadOnlySupplierList addressBook, ReadOnlyTaskList taskList, Path filePath)
            throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        trackrStorage.saveTrackr(addressBook, taskList, filePath);
    }

}
