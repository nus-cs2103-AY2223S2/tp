package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyInternBuddy;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of InternBuddy data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private InternBuddyStorage internBuddyStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code InternBuddyStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(InternBuddyStorage internBuddyStorage, UserPrefsStorage userPrefsStorage) {
        this.internBuddyStorage = internBuddyStorage;
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


    // ================ InternBuddy methods ==============================

    @Override
    public Path getInternBuddyFilePath() {
        return internBuddyStorage.getInternBuddyFilePath();
    }

    @Override
    public Optional<ReadOnlyInternBuddy> readInternBuddy() throws DataConversionException, IOException {
        return readInternBuddy(internBuddyStorage.getInternBuddyFilePath());
    }

    @Override
    public Optional<ReadOnlyInternBuddy> readInternBuddy(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return internBuddyStorage.readInternBuddy(filePath);
    }

    @Override
    public void saveInternBuddy(ReadOnlyInternBuddy internBuddy) throws IOException {
        saveInternBuddy(internBuddy, internBuddyStorage.getInternBuddyFilePath());
    }

    @Override
    public void saveInternBuddy(ReadOnlyInternBuddy internBuddy, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        internBuddyStorage.saveInternBuddy(internBuddy, filePath);
    }

}
