package codoc.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import codoc.commons.exceptions.DataConversionException;
import codoc.model.Codoc;
import codoc.model.ReadOnlyCodoc;

/**
 * Represents a storage for {@link Codoc}.
 */
public interface CodocStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getCodocFilePath();

    /**
     * Returns Codoc data as a {@link ReadOnlyCodoc}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyCodoc> readCodoc() throws DataConversionException, IOException;

    /**
     * @see #getCodocFilePath()
     */
    Optional<ReadOnlyCodoc> readCodoc(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyCodoc} to the storage.
     * @param codoc cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveCodoc(ReadOnlyCodoc codoc) throws IOException;

    /**
     * @see #saveCodoc(ReadOnlyCodoc)
     */
    void saveCodoc(ReadOnlyCodoc codoc, Path filePath) throws IOException;

}
