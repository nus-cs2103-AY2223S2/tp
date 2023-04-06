package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyReroll;
import seedu.address.model.Reroll;

/**
 * Represents a storage for {@link Reroll}.
 */
public interface RerollStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getRerollFilePath();

    /**
     * Returns Reroll data as a {@link ReadOnlyReroll}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyReroll> readReroll() throws DataConversionException, IOException;

    /**
     * @see #getRerollFilePath()
     */
    Optional<ReadOnlyReroll> readReroll(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyReroll} to the storage.
     * @param rr cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveReroll(ReadOnlyReroll rr) throws IOException;

    /**
     * @see #saveReroll(ReadOnlyReroll)
     */
    void saveReroll(ReadOnlyReroll rr, Path filePath) throws IOException;
}
