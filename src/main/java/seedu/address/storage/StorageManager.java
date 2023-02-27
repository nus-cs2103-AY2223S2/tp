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
    private FitBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code FitBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(FitBookStorage addressBookStorage, UserPrefsStorage userPrefsStorage) {
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


    // ================ FitBook methods ==============================

    @Override
    public Path getFitBookFilePath() {
        return addressBookStorage.getFitBookFilePath();
    }

    @Override
    public Optional<ReadOnlyFitBook> readFitBook() throws DataConversionException, IOException {
        return readFitBook(addressBookStorage.getFitBookFilePath());
    }

    @Override
    public Optional<ReadOnlyFitBook> readFitBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readFitBook(filePath);
    }

    @Override
    public void saveFitBook(ReadOnlyFitBook addressBook) throws IOException {
        saveFitBook(addressBook, addressBookStorage.getFitBookFilePath());
    }

    @Override
    public void saveFitBook(ReadOnlyFitBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveFitBook(addressBook, filePath);
    }

}
