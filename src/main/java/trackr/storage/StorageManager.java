package trackr.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import trackr.commons.core.LogsCenter;
import trackr.commons.exceptions.DataConversionException;
import trackr.model.ReadOnlyMenu;
import trackr.model.ReadOnlyOrderList;
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
    //@@author liumc-sg-reused
    public StorageManager(TrackrStorage trackrStorage, UserPrefsStorage userPrefsStorage) {
        this.trackrStorage = trackrStorage;
        this.userPrefsStorage = userPrefsStorage;
    }
    //@@author

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

    //@@author liumc-sg-reused
    @Override
    public Path getTrackrFilePath() {
        return trackrStorage.getTrackrFilePath();
    }
    //@@author

    @Override
    public Optional<ReadOnlySupplierList> readSupplierList() throws DataConversionException, IOException {
        return readSupplierList(trackrStorage.getTrackrFilePath());
    }

    @Override
    public Optional<ReadOnlySupplierList> readSupplierList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return trackrStorage.readSupplierList(filePath);
    }

    //@@author liumc-sg-reused
    @Override
    public Optional<ReadOnlyTaskList> readTaskList() throws DataConversionException, IOException {
        return readTaskList(trackrStorage.getTrackrFilePath());
    }

    @Override
    public Optional<ReadOnlyTaskList> readTaskList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return trackrStorage.readTaskList(filePath);
    }
    //@@author

    //@@author changgittyhub-reused
    @Override
    public Optional<ReadOnlyMenu> readMenu() throws DataConversionException, IOException {
        return readMenu(trackrStorage.getTrackrFilePath());
    }

    //@@author changgittyhub-reused
    @Override
    public Optional<ReadOnlyMenu> readMenu(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return trackrStorage.readMenu(filePath);
    }

    //@@author chongweiguan-reused
    @Override
    public Optional<ReadOnlyOrderList> readOrderList() throws DataConversionException, IOException {
        return readOrderList(trackrStorage.getTrackrFilePath());
    }

    @Override
    public Optional<ReadOnlyOrderList> readOrderList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return trackrStorage.readOrderList(filePath);
    }
    //@@author

    //@@author liumc-sg-reused
    @Override
    public void saveTrackr(ReadOnlySupplierList supplierList, ReadOnlyTaskList taskList,
                           ReadOnlyMenu menu, ReadOnlyOrderList orderList) throws IOException {
        saveTrackr(supplierList, taskList, menu, orderList, trackrStorage.getTrackrFilePath());
    }

    @Override
    public void saveTrackr(ReadOnlySupplierList supplierList, ReadOnlyTaskList taskList,
                           ReadOnlyMenu menu, ReadOnlyOrderList orderList, Path filePath)
            throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        trackrStorage.saveTrackr(supplierList, taskList, menu, orderList, filePath);
    }

}
