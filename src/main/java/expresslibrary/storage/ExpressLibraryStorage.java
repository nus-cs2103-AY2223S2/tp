package expresslibrary.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import expresslibrary.commons.exceptions.DataConversionException;
import expresslibrary.model.ReadOnlyExpressLibrary;

/**
 * Represents a storage for {@link expresslibrary.model.ExpressLibrary}.
 */
public interface ExpressLibraryStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getExpressLibraryFilePath();

    /**
     * Returns ExpressLibrary data as a {@link ReadOnlyExpressLibrary}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected
     *                                 format.
     * @throws IOException             if there was any problem when reading from
     *                                 the storage.
     */
    Optional<ReadOnlyExpressLibrary> readExpressLibrary() throws DataConversionException, IOException;

    /**
     * @see #getExpressLibraryFilePath()
     */
    Optional<ReadOnlyExpressLibrary> readExpressLibrary(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyExpressLibrary} to the storage.
     *
     * @param expressLibrary cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveExpressLibrary(ReadOnlyExpressLibrary expressLibrary) throws IOException;

    /**
     * @see #saveExpressLibrary(ReadOnlyExpressLibrary)
     */
    void saveExpressLibrary(ReadOnlyExpressLibrary expressLibrary, Path filePath) throws IOException;

}
