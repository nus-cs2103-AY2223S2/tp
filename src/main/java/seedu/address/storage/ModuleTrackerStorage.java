package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ModuleTracker;
import seedu.address.model.ReadOnlyModuleTracker;

/**
 * Represents a storage for {@link ModuleTracker}.
 */
public interface ModuleTrackerStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getModuleTrackerFilePath();

    /**
     * Returns ModuleTracker data as a {@link ReadOnlyModuleTracker}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyModuleTracker> readModuleTracker() throws DataConversionException, IOException;

    /**
     * @see #getModuleTrackerFilePath()
     */
    Optional<ReadOnlyModuleTracker> readModuleTracker(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyModuleTracker} to the storage.
     * @param moduleTracker cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveModuleTracker(ReadOnlyModuleTracker moduleTracker) throws IOException;

    /**
     * @see #saveModuleTracker(ReadOnlyModuleTracker)
     */
    void saveModuleTracker(ReadOnlyModuleTracker moduleTracker, Path filePath) throws IOException;

}
