package vimification.storage;

import java.io.IOException;
import java.nio.file.Path;

import vimification.common.exceptions.DataConversionException;
import vimification.model.TaskList;

/**
 * Represents a storage for {@link TaskList}.
 */
public interface TaskListStorage {

    /**
     * Returns the file path of the data file.
     *
     * @return the file path of the data file.
     */
    Path getTaskListFilePath();

    /**
     * Returns TaskList data as a {@link TaskList}. Returns {@code Optional.empty()} if storage file
     * is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    TaskList readTaskList() throws DataConversionException, IOException;

    /**
     * Save the given {@link TaskList} to the storage.
     *
     * @param taskList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTaskList(TaskList taskList) throws IOException;
}
