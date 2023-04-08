package vimification.storage;

import java.io.IOException;
import java.nio.file.Path;

import vimification.common.exceptions.DataConversionException;
import vimification.model.TaskList;

/**
 * Represents a storage for {@link TaskList}.
 */
public interface TaskListStorage {

    Path getTaskListFilePath();

    TaskList readTaskList() throws DataConversionException, IOException;

    void saveTaskList(TaskList taskList) throws IOException;
}
