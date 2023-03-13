package codoc.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import codoc.commons.core.LogsCenter;
import codoc.commons.exceptions.DataConversionException;
import codoc.model.ReadOnlyCodoc;
import codoc.model.ReadOnlyUserPrefs;
import codoc.model.UserPrefs;

/**
 * Manages storage of Codoc data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private CodocStorage codocStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code CodocStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(CodocStorage codocStorage, UserPrefsStorage userPrefsStorage) {
        this.codocStorage = codocStorage;
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


    // ================ Codoc methods ==============================

    @Override
    public Path getCodocFilePath() {
        return codocStorage.getCodocFilePath();
    }

    @Override
    public Optional<ReadOnlyCodoc> readCodoc() throws DataConversionException, IOException {
        return readCodoc(codocStorage.getCodocFilePath());
    }

    @Override
    public Optional<ReadOnlyCodoc> readCodoc(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return codocStorage.readCodoc(filePath);
    }

    @Override
    public void saveCodoc(ReadOnlyCodoc codoc) throws IOException {
        saveCodoc(codoc, codocStorage.getCodocFilePath());
    }

    @Override
    public void saveCodoc(ReadOnlyCodoc codoc, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        codocStorage.saveCodoc(codoc, filePath);
    }

}
