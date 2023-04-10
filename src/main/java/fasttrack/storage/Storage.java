package fasttrack.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import fasttrack.commons.exceptions.DataConversionException;
import fasttrack.model.ReadOnlyExpenseTracker;
import fasttrack.model.ReadOnlyUserPrefs;
import fasttrack.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends ExpenseTrackerStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getExpenseTrackerFilePath();

    @Override
    Optional<ReadOnlyExpenseTracker> readExpenseTracker() throws DataConversionException, IOException;

    @Override
    void saveExpenseTracker(ReadOnlyExpenseTracker expenseTracker) throws IOException;

}
