package seedu.dengue.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.dengue.commons.exceptions.DataConversionException;
import seedu.dengue.model.DengueHotspotTracker;
import seedu.dengue.model.ReadOnlyDengueHotspotTracker;

/**
 * Represents a storage for {@link DengueHotspotTracker}.
 */
public interface DengueHotspotStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getDengueHotspotTrackerFilePath();

    /**
     * Returns DengueHotspotTracker data as a {@link ReadOnlyDengueHotspotTracker}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyDengueHotspotTracker> readDengueHotspotTracker() throws DataConversionException, IOException;

    /**
     * @see #getDengueHotspotTrackerFilePath()
     */
    Optional<ReadOnlyDengueHotspotTracker> readDengueHotspotTracker(Path filePath) throws DataConversionException,
            IOException;

    /**
     * Saves the given {@link ReadOnlyDengueHotspotTracker} to the storage.
     * @param dengueHotspotTracker cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveDengueHotspotTracker(ReadOnlyDengueHotspotTracker dengueHotspotTracker)
            throws IOException;

    /**
     * @see #saveDengueHotspotTracker(ReadOnlyDengueHotspotTracker)
     */
    void saveDengueHotspotTracker(ReadOnlyDengueHotspotTracker dengueHotspotTracker, Path filePath)
            throws IOException;

}
