package taa.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import taa.commons.exceptions.DataConversionException;
import taa.model.ClassList;
import taa.model.ReadOnlyTaaData;

/**
 * Represents a storage for {@link ClassList}.`
 */
public interface TaaStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTaaDataFilePath();

    /**
     * Returns ClassList data as a {@link ReadOnlyTaaData}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTaaData> readTaaData() throws DataConversionException, IOException;

    /**
     * @see #getTaaDataFilePath()
     */
    Optional<ReadOnlyTaaData> readTaaData(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTaaData} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTaaData(ReadOnlyTaaData addressBook) throws IOException;

    /**
     * @see #saveTaaData(ReadOnlyTaaData)
     */
    void saveTaaData(ReadOnlyTaaData addressBook, Path filePath) throws IOException;

}
