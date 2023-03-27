package seedu.task.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.task.commons.exceptions.DataConversionException;
import seedu.task.model.ReadOnlyPlanner;
import seedu.task.model.calendar.DailyPlan;

/**
 * Represents a storage for {@link DailyPlan}.
 */
public interface PlannerStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getPlannerFilePath();

    /**
     * Returns Planner data as a {@link ReadOnlyPlanner}.
     * Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyPlanner> readPlanner() throws DataConversionException, IOException;

    /**
     * @see #getPlannerFilePath()
     */
    Optional<ReadOnlyPlanner> readPlanner(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyPlanner} to the storage.
     * @param plans cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void savePlanner(ReadOnlyPlanner plans) throws IOException;

    /**
     * @see #savePlanner(ReadOnlyPlanner)
     */
    void savePlanner(ReadOnlyPlanner plans, Path filePath) throws IOException;
}
