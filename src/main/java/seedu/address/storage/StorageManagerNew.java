package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUltron;
import seedu.address.model.ReadOnlyUserPrefsNew;
import seedu.address.model.UserPrefsNew;

/**
 * Manages storage of Ultron data in local storage.
 */
public class StorageManagerNew implements StorageNew {

    private static final Logger logger = LogsCenter.getLogger(StorageManagerNew.class);
    private UltronStorage ultronStorage;
    private UserPrefsStorageNew userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code UltronStorage} and {@code UserPrefStorageNew}.
     */
    public StorageManagerNew(UltronStorage ultronStorage, UserPrefsStorageNew userPrefsStorage) {
        this.ultronStorage = ultronStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefsNew> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefsNew userPrefs) throws IOException {
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
