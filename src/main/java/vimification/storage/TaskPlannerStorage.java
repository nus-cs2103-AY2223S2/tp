package vimification.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import vimification.commons.exceptions.DataConversionException;
import vimification.model.ReadOnlyTaskPlanner;

/**
 * Represents a storage for {@link vimification.model.TaskList}.
 */
public interface TaskPlannerStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTaskListFilePath();

    /**
     * Returns TaskPlanner data as a {@link ReadOnlyTaskPlanner}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTaskPlanner> readTaskList() throws DataConversionException, IOException;

    /**
     * @see #getTaskListFilePath()
     */
    Optional<ReadOnlyTaskPlanner> readTaskList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTaskPlanner} to the storage.
     * @param taskList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTaskList(ReadOnlyTaskPlanner taskList) throws IOException;

    /**
     * @see #saveTaskList(ReadOnlyTaskPlanner)
     */
    void saveTaskList(ReadOnlyTaskPlanner taskList, Path filePath) throws IOException;

}
