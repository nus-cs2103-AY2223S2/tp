package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.EventBook;
import seedu.address.model.ReadOnlyEventBook;

/**
 * Represents a storage for {@link EventBook}.
 */
public interface EventBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getEventBookFilePath();

    /**
     * Returns EventBook data as a {@link ReadOnlyEventBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyEventBook> readEventBook() throws DataConversionException, IOException;

    /**
     * @see #getEventBookFilePath()
     */
    Optional<ReadOnlyEventBook> readEventBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyEventBook} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveEventBook(ReadOnlyEventBook addressBook) throws IOException;

    /**
     * @see #saveEventBook(ReadOnlyEventBook)
     */
    void saveEventBook(ReadOnlyEventBook addressBook, Path filePath) throws IOException;

}
