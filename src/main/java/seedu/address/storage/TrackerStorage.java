package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTracker;
import seedu.address.model.Tracker;

/**
 * Represents a storage for {@link Tracker}.
 */
public interface TrackerStorage {

    /**
     * Returns the file path of the data file.
     *
     * @return The file path of the data file.
     */
    Path getTrackerFilePath();

    /**
     * Returns tracker data as a {@link ReadOnlyTracker}.<p>
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @return The tracker data as a {@link ReadOnlyTracker} if storage file is found. Otherwise, returns
     *         {@code Optional.empty()}.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTracker> readTracker() throws DataConversionException, IOException;

    /**
     * Similar to {@link #readTracker()}.
     *
     * @param filePath The location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    Optional<ReadOnlyTracker> readTracker(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTracker} to the storage.
     *
     * @param tracker cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTracker(ReadOnlyTracker tracker) throws IOException;

    /**
     * Similar to {@link #saveTracker(ReadOnlyTracker)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    void saveTracker(ReadOnlyTracker tracker, Path filePath) throws IOException;
}
