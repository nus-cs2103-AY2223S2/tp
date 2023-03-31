package vimification.storage;

import java.io.IOException;
import java.nio.file.Path;

import vimification.commons.exceptions.DataConversionException;
import vimification.model.LogicTaskList;
import vimification.model.TaskListRef;

/**
 * Represents a storage for {@link vimification.model.TaskList}.
 */
public interface TaskListRefStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTaskListRefFilePath();

    /**
     * Returns TaskPlanner data as a {@link ReadOnlyTaskPlanner}. Returns {@code Optional.empty()}
     * if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    TaskListRef readTaskListRef() throws DataConversionException, IOException;

    /**
     * Saves the given {@link LogicTaskList} to the storage.
     *
     * @param ref cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTaskListRef(TaskListRef ref) throws IOException;
}
