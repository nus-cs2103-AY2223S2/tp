package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyExpenseTracker;

/**
 * Represents a storage for {@link seedu.address.model.ExpenseTracker}.
 */
public interface ExpenseTrackerStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getExpenseTrackerFilePath();

    /**
     * Returns ExpenseTracker data as a {@link ReadOnlyExpenseTracker}.
     * Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyExpenseTracker> readExpenseTracker() throws DataConversionException, IOException;

    /**
     * @see #getExpenseTrackerFilePath()
     */
    Optional<ReadOnlyExpenseTracker> readExpenseTracker(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyExpenseTracker} to the storage.
     * @param expenseTracker cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveExpenseTracker(ReadOnlyExpenseTracker expenseTracker) throws IOException;

    /**
     * @see #saveExpenseTracker(ReadOnlyExpenseTracker)
     */
    void saveExpenseTracker(ReadOnlyExpenseTracker expenseTracker, Path filePath) throws IOException;

}
