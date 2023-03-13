package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUltron;

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

    /*
     * Saves the given {@link ReadOnlyUltron} to the storage.
     * @param ultron cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveUltron(ReadOnlyUltron ultron) throws IOException;

    /**
     * @see #saveUltron(ReadOnlyUltron)
     */
    void saveUltron(ReadOnlyUltron ultron, Path filePath) throws IOException;

}
