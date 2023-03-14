package vimification.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import vimification.commons.exceptions.DataConversionException;
import vimification.model.ReadOnlyTaskPlanner;
import vimification.model.ReadOnlyUserPrefs;
import vimification.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends TaskPlannerStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getTaskListFilePath();

    @Override
    Optional<ReadOnlyTaskPlanner> readTaskList() throws DataConversionException, IOException;

    @Override
    void saveTaskList(ReadOnlyTaskPlanner taskList) throws IOException;

}
