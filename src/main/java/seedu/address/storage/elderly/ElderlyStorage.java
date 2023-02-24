package seedu.address.storage.elderly;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyElderly;

public interface ElderlyStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getElderlyFilePath();

    /**
     * Returns FriendlyLink data as a {@link ReadOnlyElderly}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyElderly> readElderly() throws DataConversionException, IOException;

    /**
     * @see #getElderlyFilePath()
     */
    Optional<ReadOnlyElderly> readElderly(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyElderly} to the storage.
     * @param elderly cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveElderly(ReadOnlyElderly elderly) throws IOException;

    /**
     * @see #saveElderly(ReadOnlyElderly)
     */
    void saveElderly(ReadOnlyElderly elderly, Path filePath) throws IOException;
}
