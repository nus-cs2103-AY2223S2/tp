package tfifteenfour.clipboard.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import tfifteenfour.clipboard.commons.exceptions.DataConversionException;
import tfifteenfour.clipboard.model.ReadOnlyRoster;

/**
 * Represents a storage for {@link tfifteenfour.clipboard.model.Roster}.
 */
public interface RosterStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getRosterFilePath();

    /**
     * Returns Roster data as a {@link ReadOnlyRoster}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyRoster> readRoster() throws DataConversionException, IOException;

    /**
     * @see #getRosterFilePath()
     */
    Optional<ReadOnlyRoster> readRoster(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyRoster} to the storage.
     * @param roster cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveRoster(ReadOnlyRoster roster) throws IOException;

    /**
     * @see #saveRoster(ReadOnlyRoster)
     */
    void saveRoster(ReadOnlyRoster roster, Path filePath) throws IOException;

}
