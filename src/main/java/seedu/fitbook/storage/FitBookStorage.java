package seedu.fitbook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.fitbook.commons.exceptions.DataConversionException;
import seedu.fitbook.model.ReadOnlyFitBook;

/**
 * Represents a storage for {@link seedu.fitbook.model.FitBook}.
 */
public interface FitBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getFitBookFilePath();

    /**
     * Returns FitBook data as a {@link ReadOnlyFitBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyFitBook> readFitBook() throws DataConversionException, IOException;

    /**
     * @see #getFitBookFilePath()
     */
    Optional<ReadOnlyFitBook> readFitBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyFitBook} to the storage.
     * @param fitBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveFitBook(ReadOnlyFitBook fitBook) throws IOException;

    /**
     * @see #saveFitBook(ReadOnlyFitBook)
     */
    void saveFitBook(ReadOnlyFitBook fitBook, Path filePath) throws IOException;

}
