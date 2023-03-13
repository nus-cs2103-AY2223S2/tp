package seedu.address.storage;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUltron;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Represents a storage for {@link seedu.address.model.Ultron}.
 */
public interface UltronStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getUltronFilePath();

    /**
     * Returns Ultron data as a {@link ReadOnlyUltron}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyUltron> readUltron() throws DataConversionException, IOException;

    /**
     * @see #getUltronFilePath()
     */
    Optional<ReadOnlyUltron> readUltron(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyUltron} to the storage.
     * @param Ultron cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveUltron(ReadOnlyUltron Ultron) throws IOException;

    /**
     * @see #saveUltron(ReadOnlyUltron)
     */
    void saveUltron(ReadOnlyUltron Ultron, Path filePath) throws IOException;

}
