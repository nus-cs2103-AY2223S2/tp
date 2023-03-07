package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyFitBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of FitBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private FitBookStorage fitBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code FitBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(FitBookStorage fitBookStorage, UserPrefsStorage userPrefsStorage) {
        this.fitBookStorage = fitBookStorage;
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


    // ================ FitBook methods ==============================

    @Override
    public Path getFitBookFilePath() {
        return fitBookStorage.getFitBookFilePath();
    }

    @Override
    public Optional<ReadOnlyFitBook> readFitBook() throws DataConversionException, IOException {
        return readFitBook(fitBookStorage.getFitBookFilePath());
    }

    @Override
    public Optional<ReadOnlyFitBook> readFitBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return fitBookStorage.readFitBook(filePath);
    }

    @Override
    public void saveFitBook(ReadOnlyFitBook fitBook) throws IOException {
        saveFitBook(fitBook, fitBookStorage.getFitBookFilePath());
    }

    @Override
    public void saveFitBook(ReadOnlyFitBook fitBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        fitBookStorage.saveFitBook(fitBook, filePath);
    }

}
