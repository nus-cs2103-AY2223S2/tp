package vimification.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import vimification.commons.exceptions.DataConversionException;
import vimification.model.LogicTaskList;
import vimification.model.ReadOnlyUserPrefs;
import vimification.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends LogicTaskListStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getLogicTaskListFilePath();

    @Override
    Optional<LogicTaskList> readLogicTaskList() throws DataConversionException, IOException;

    @Override
    void saveLogicTaskList(LogicTaskList taskList) throws IOException;
}
