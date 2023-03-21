package seedu.ultron.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.ultron.commons.core.LogsCenter;
import seedu.ultron.commons.exceptions.DataConversionException;
import seedu.ultron.model.ReadOnlyUltron;
import seedu.ultron.model.ReadOnlyUserPrefs;
import seedu.ultron.model.UserPrefs;

/**
 * Manages storage of Ultron data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private UltronStorage ultronStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code UltronStorage} and {@code UserPrefStorageNew}.
     */
    public StorageManager(UltronStorage ultronStorage, UserPrefsStorage userPrefsStorage) {
        this.ultronStorage = ultronStorage;
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


    // ================ Ultron methods ==============================

    @Override
    public Path getUltronFilePath() {
        return ultronStorage.getUltronFilePath();
    }

    @Override
    public Optional<ReadOnlyUltron> readUltron() throws DataConversionException, IOException {
        return readUltron(ultronStorage.getUltronFilePath());
    }

    @Override
    public Optional<ReadOnlyUltron> readUltron(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return ultronStorage.readUltron(filePath);
    }

    @Override
    public void saveUltron(ReadOnlyUltron ultron) throws IOException {
        saveUltron(ultron, ultronStorage.getUltronFilePath());
    }

    @Override
    public void saveUltron(ReadOnlyUltron ultron, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        ultronStorage.saveUltron(ultron, filePath);
    }

}
