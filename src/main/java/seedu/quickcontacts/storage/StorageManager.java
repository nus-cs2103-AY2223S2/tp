package seedu.quickcontacts.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.quickcontacts.commons.core.LogsCenter;
import seedu.quickcontacts.commons.exceptions.DataConversionException;
import seedu.quickcontacts.model.ReadOnlyQuickBook;
import seedu.quickcontacts.model.ReadOnlyUserPrefs;
import seedu.quickcontacts.model.UserPrefs;

/**
 * Manages storage of QuickBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private QuickBookStorage quickBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code QuickBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(QuickBookStorage quickBookStorage, UserPrefsStorage userPrefsStorage) {
        this.quickBookStorage = quickBookStorage;
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


    // ================ QuickBook methods ==============================

    @Override
    public Path getQuickBookFilePath() {
        return quickBookStorage.getQuickBookFilePath();
    }

    @Override
    public Optional<ReadOnlyQuickBook> readQuickBook() throws DataConversionException, IOException {
        return readQuickBook(quickBookStorage.getQuickBookFilePath());
    }

    @Override
    public Optional<ReadOnlyQuickBook> readQuickBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return quickBookStorage.readQuickBook(filePath);
    }

    @Override
    public void saveQuickBook(ReadOnlyQuickBook quickBook) throws IOException {
        saveQuickBook(quickBook, quickBookStorage.getQuickBookFilePath());
    }

    @Override
    public void saveQuickBook(ReadOnlyQuickBook quickBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        quickBookStorage.saveQuickBook(quickBook, filePath);
    }

}
