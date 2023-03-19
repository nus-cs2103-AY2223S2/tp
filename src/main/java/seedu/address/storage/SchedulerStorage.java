package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyScheduler;
import seedu.address.model.Scheduler;

/**
 * Represents a storage for {@link Scheduler}.
 */
public interface SchedulerStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getSchedulerFilePath();

    /**
     * Returns Scheduler data as a {@link ReadOnlyScheduler}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException             if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyScheduler> readScheduler() throws DataConversionException, IOException;

    /**
     * @see #getSchedulerFilePath() ()
     */
    Optional<ReadOnlyScheduler> readScheduler(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyScheduler} to the storage.
     *
     * @param scheduler cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveScheduler(ReadOnlyScheduler scheduler) throws IOException;

    /**
     * @see #saveScheduler(ReadOnlyScheduler, Path) (ReadOnlyScheduler)
     */
    void saveScheduler(ReadOnlyScheduler scheduler, Path filePath) throws IOException;
}
