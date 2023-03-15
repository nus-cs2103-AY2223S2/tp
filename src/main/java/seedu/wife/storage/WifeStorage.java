package seedu.wife.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.wife.commons.exceptions.DataConversionException;
import seedu.wife.model.ReadOnlyWife;

/**
 * Represents a storage for {@link seedu.wife.model.Wife}.
 */
public interface WifeStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getWifeFilePath();

    /**
     * Returns Wife data as a {@link ReadOnlyWife}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyWife> readWife() throws DataConversionException, IOException;

    /**
     * @see #getWifeFilePath()
     */
    Optional<ReadOnlyWife> readWife(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyWife} to the storage.
     * @param wife cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveWife(ReadOnlyWife wife) throws IOException;

    /**
     * @see #saveWife(ReadOnlyWife)
     */
    void saveWife(ReadOnlyWife wife, Path filePath) throws IOException;

}
