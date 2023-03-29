package seedu.quickcontacts.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.quickcontacts.commons.exceptions.DataConversionException;
import seedu.quickcontacts.model.QuickBook;
import seedu.quickcontacts.model.ReadOnlyQuickBook;

/**
 * Represents a storage for {@link QuickBook}.
 */
public interface QuickBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getQuickBookFilePath();

    /**
     * Returns QuickBook data as a {@link ReadOnlyQuickBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyQuickBook> readQuickBook() throws DataConversionException, IOException;

    /**
     * @see #getQuickBookFilePath()
     */
    Optional<ReadOnlyQuickBook> readQuickBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyQuickBook} to the storage.
     * @param quickBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveQuickBook(ReadOnlyQuickBook quickBook) throws IOException;

    /**
     * @see #saveQuickBook(ReadOnlyQuickBook)
     */
    void saveQuickBook(ReadOnlyQuickBook quickBook, Path filePath) throws IOException;

}
