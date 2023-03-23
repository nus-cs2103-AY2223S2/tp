package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyInternBuddy;

/**
 * Represents a storage for {@link seedu.address.model.InternBuddy}.
 */
public interface InternBuddyStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getInternBuddyFilePath();

    /**
     * Returns InternBuddy data as a {@link ReadOnlyInternBuddy}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyInternBuddy> readInternBuddy() throws DataConversionException, IOException;

    /**
     * @see #getInternBuddyFilePath()
     */
    Optional<ReadOnlyInternBuddy> readInternBuddy(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyInternBuddy} to the storage.
     * @param internBuddy cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveInternBuddy(ReadOnlyInternBuddy internBuddy) throws IOException;

    /**
     * @see #saveInternBuddy(ReadOnlyInternBuddy)
     */
    void saveInternBuddy(ReadOnlyInternBuddy internBuddy, Path filePath) throws IOException;

}
