package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyExpenseTracker;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of ExpenseTracker data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ExpenseTrackerStorage expenseBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code ExpenseTrackerStorage}
     * and {@code UserPrefStorage}.
     */
    public StorageManager(ExpenseTrackerStorage expenseTracker, UserPrefsStorage userPrefsStorage) {
        this.expenseBookStorage = expenseTracker;
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

    // ================ ExpenseTracker methods ==============================

    @Override
    public Path getExpenseTrackerFilePath() {
        return expenseBookStorage.getExpenseTrackerFilePath();
    }

    @Override
    public Optional<ReadOnlyExpenseTracker> readExpenseTracker() throws DataConversionException, IOException {
        return readExpenseTracker(expenseBookStorage.getExpenseTrackerFilePath());
    }

    @Override
    public Optional<ReadOnlyExpenseTracker> readExpenseTracker(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return expenseBookStorage.readExpenseTracker(filePath);
    }

    @Override
    public void saveExpenseTracker(ReadOnlyExpenseTracker expenseTracker) throws IOException {
        saveExpenseTracker(expenseTracker, expenseBookStorage.getExpenseTrackerFilePath());
    }

    @Override
    public void saveExpenseTracker(ReadOnlyExpenseTracker expenseTracker, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        expenseBookStorage.saveExpenseTracker(expenseTracker, filePath);
    }

}
