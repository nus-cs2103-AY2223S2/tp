package expresslibrary.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import expresslibrary.commons.core.LogsCenter;
import expresslibrary.commons.exceptions.DataConversionException;
import expresslibrary.model.ReadOnlyExpressLibrary;
import expresslibrary.model.ReadOnlyUserPrefs;
import expresslibrary.model.UserPrefs;

/**
 * Manages storage of ExpressLibrary data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ExpressLibraryStorage expressLibraryStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ExpressLibraryStorage}
     * and {@code UserPrefStorage}.
     */
    public StorageManager(ExpressLibraryStorage expressLibraryStorage, UserPrefsStorage userPrefsStorage) {
        this.expressLibraryStorage = expressLibraryStorage;
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

    // ================ ExpressLibrary methods ==============================

    @Override
    public Path getExpressLibraryFilePath() {
        return expressLibraryStorage.getExpressLibraryFilePath();
    }

    @Override
    public Optional<ReadOnlyExpressLibrary> readExpressLibrary() throws DataConversionException, IOException {
        return readExpressLibrary(expressLibraryStorage.getExpressLibraryFilePath());
    }

    @Override
    public Optional<ReadOnlyExpressLibrary> readExpressLibrary(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return expressLibraryStorage.readExpressLibrary(filePath);
    }

    @Override
    public void saveExpressLibrary(ReadOnlyExpressLibrary expressLibrary) throws IOException {
        saveExpressLibrary(expressLibrary, expressLibraryStorage.getExpressLibraryFilePath());
    }

    @Override
    public void saveExpressLibrary(ReadOnlyExpressLibrary expressLibrary, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        expressLibraryStorage.saveExpressLibrary(expressLibrary, filePath);
    }

}
