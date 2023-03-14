package trackr.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import trackr.commons.core.LogsCenter;
import trackr.commons.exceptions.DataConversionException;
import trackr.model.ReadOnlyAddressBook;
import trackr.model.ReadOnlyOrderList;
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
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(trackrStorage.getTrackrFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return trackrStorage.readAddressBook(filePath);
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
    public Optional<ReadOnlyOrderList> readOrderList() throws DataConversionException, IOException {
        return readOrderList(trackrStorage.getTrackrFilePath());
    }

    @Override
    public Optional<ReadOnlyOrderList> readOrderList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return trackrStorage.readOrderList(filePath);
    }

    @Override
    public void saveTrackr(ReadOnlyAddressBook addressBook, ReadOnlyTaskList taskList,
            ReadOnlyOrderList orderList) throws IOException {
        saveTrackr(addressBook, taskList, orderList, trackrStorage.getTrackrFilePath());
    }

    @Override
    public void saveTrackr(ReadOnlyAddressBook addressBook, ReadOnlyTaskList taskList,
            ReadOnlyOrderList orderList, Path filePath)
            throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        trackrStorage.saveTrackr(addressBook, taskList, orderList, filePath);
    }

}
