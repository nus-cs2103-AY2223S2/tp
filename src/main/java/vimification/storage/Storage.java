package vimification.storage;

import java.io.IOException;
import java.nio.file.Path;

import vimification.commons.exceptions.DataConversionException;
import vimification.model.LogicTaskList;
import vimification.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends LogicTaskListStorage, UserPrefsStorage {

    @Override
    Path getUserPrefsFilePath();

    @Override
    UserPrefs readUserPrefs() throws IOException;

    @Override
    void saveUserPrefs(UserPrefs userPrefs) throws IOException;

    @Override
    Path getLogicTaskListFilePath();

    @Override
    LogicTaskList readLogicTaskList() throws DataConversionException, IOException;

    @Override
    void saveLogicTaskList(LogicTaskList taskList) throws IOException;
}
