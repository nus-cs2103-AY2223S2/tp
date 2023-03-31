package vimification.storage;

import java.io.IOException;
import java.nio.file.Path;

import vimification.commons.exceptions.DataConversionException;
import vimification.model.MacroMap;
import vimification.model.TaskListRef;
import vimification.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends TaskListRefStorage, UserPrefsStorage {

    @Override
    Path getUserPrefsFilePath();

    @Override
    UserPrefs readUserPrefs() throws IOException;

    @Override
    void saveUserPrefs(UserPrefs userPrefs) throws IOException;

    @Override
    Path getTaskListRefFilePath();

    @Override
    TaskListRef readTaskListRef() throws DataConversionException, IOException;

    @Override
    void saveTaskListRef(TaskListRef taskList) throws IOException;

    Path getMacroMapFilePath();

    MacroMap readMacroMap() throws IOException;

    void saveMacroMap(MacroMap macroMap) throws IOException;
}
