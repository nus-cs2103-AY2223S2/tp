package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyElister;

/**
 * Represents a storage for {@link seedu.address.model.Elister}.
 */
public interface ElisterStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getElisterFilePath();

    /**
     * Returns Elister data as a {@link ReadOnlyElister}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyElister> readElister() throws DataConversionException, IOException;

    /**
     * @see #getElisterFilePath()
     */
    Optional<ReadOnlyElister> readElister(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyElister} to the storage.
     * @param elister cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveElister(ReadOnlyElister elister) throws IOException;

    /**
     * @see #saveElister(ReadOnlyElister)
     */
    void saveElister(ReadOnlyElister elister, Path filePath) throws IOException;

}
