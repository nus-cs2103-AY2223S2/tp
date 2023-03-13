package seedu.address.storage;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUltron;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Manages storage of Ultron data in local storage.
 */
public class StorageManagerNew implements StorageNew {

    private static final Logger logger = LogsCenter.getLogger(StorageManagerNew.class);
    private UltronStorage UltronStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code UltronStorage} and {@code UserPrefStorage}.
     */
    public StorageManagerNew(UltronStorage UltronStorage, UserPrefsStorage userPrefsStorage) {
        this.UltronStorage = UltronStorage;
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
        return UltronStorage.getUltronFilePath();
    }

    @Override
    public Optional<ReadOnlyUltron> readUltron() throws DataConversionException, IOException {
        return readUltron(UltronStorage.getUltronFilePath());
    }

    @Override
    public Optional<ReadOnlyUltron> readUltron(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return UltronStorage.readUltron(filePath);
    }

    @Override
    public void saveUltron(ReadOnlyUltron Ultron) throws IOException {
        saveUltron(Ultron, UltronStorage.getUltronFilePath());
    }

    @Override
    public void saveUltron(ReadOnlyUltron Ultron, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        UltronStorage.saveUltron(Ultron, filePath);
    }

}
