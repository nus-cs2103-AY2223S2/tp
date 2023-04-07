package vimification.storage;

import java.io.IOException;
import java.nio.file.Path;

import vimification.common.exceptions.DataConversionException;
import vimification.model.MacroMap;
import vimification.model.TaskList;
import vimification.model.UserPrefs;

/**
 * Reads and writes data of the application's entities from local storage.
 */
public class StorageManager implements Storage {

    private TaskListStorage taskListStorage;
    private MacroMapStorage macroMapStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a new {@code StorageManager}.
     */
    public StorageManager(
            TaskListStorage taskListStorage,
            MacroMapStorage macroMapStorage,
            UserPrefsStorage userPrefsStorage) {
        this.taskListStorage = taskListStorage;
        this.macroMapStorage = macroMapStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public UserPrefs readUserPrefs() throws IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(UserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }

    @Override
    public Path getTaskListFilePath() {
        return taskListStorage.getTaskListFilePath();
    }

    @Override
    public TaskList readTaskList() throws DataConversionException, IOException {
        return taskListStorage.readTaskList();
    }

    @Override
    public void saveTaskList(TaskList taskList) throws IOException {
        taskListStorage.saveTaskList(taskList);
    }

    @Override
    public Path getMacroMapFilePath() {
        return macroMapStorage.getMacroMapFilePath();
    }

    @Override
    public MacroMap readMacroMap() throws IOException {
        return macroMapStorage.readMacroMap();
    }

    @Override
    public void saveMacroMap(MacroMap macroMap) throws IOException {
        macroMapStorage.saveMacroMap(macroMap);
    }
}
