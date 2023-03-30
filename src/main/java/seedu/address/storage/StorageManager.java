package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyModuleTracker;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ModuleTrackerStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ModuleTrackerStorage addressBookStorage, UserPrefsStorage userPrefsStorage) {
        this.addressBookStorage = addressBookStorage;
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


    // ================ AddressBook methods ==============================

    @Override
    public Path getModuleTrackerFilePath() {
        return addressBookStorage.getModuleTrackerFilePath();
    }

    @Override
    public Optional<ReadOnlyModuleTracker> readModuleTracker() throws DataConversionException, IOException {
        return readModuleTracker(addressBookStorage.getModuleTrackerFilePath());
    }

    @Override
    public Optional<ReadOnlyModuleTracker> readModuleTracker(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readModuleTracker(filePath);
    }

    @Override
    public void saveModuleTracker(ReadOnlyModuleTracker moduleTracker) throws IOException {
        saveModuleTracker(moduleTracker, addressBookStorage.getModuleTrackerFilePath());
    }

    @Override
    public void saveModuleTracker(ReadOnlyModuleTracker moduleTracker, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveModuleTracker(moduleTracker, filePath);
    }

}
