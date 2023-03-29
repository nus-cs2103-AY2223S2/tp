package seedu.wife.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.wife.commons.core.LogsCenter;
import seedu.wife.commons.exceptions.DataConversionException;
import seedu.wife.model.ReadOnlyUserPrefs;
import seedu.wife.model.ReadOnlyWife;
import seedu.wife.model.UserPrefs;

/**
 * Manages storage of Wife data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private WifeStorage wifeStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code WifeStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(WifeStorage wifeStorage, UserPrefsStorage userPrefsStorage) {
        this.wifeStorage = wifeStorage;
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


    // ================ Wife methods ==============================

    @Override
    public Path getWifeFilePath() {
        return wifeStorage.getWifeFilePath();
    }

    @Override
    public Optional<ReadOnlyWife> readWife() throws DataConversionException, IOException {
        return readWife(wifeStorage.getWifeFilePath());
    }

    @Override
    public Optional<ReadOnlyWife> readWife(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return wifeStorage.readWife(filePath);
    }

    @Override
    public void saveWife(ReadOnlyWife wife) throws IOException {
        saveWife(wife, wifeStorage.getWifeFilePath());
    }

    @Override
    public void saveWife(ReadOnlyWife wife, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        wifeStorage.saveWife(wife, filePath);
    }

}
