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

    /**
     * {@inheritDoc}
     */
    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserPrefs readUserPrefs() throws IOException {
        return userPrefsStorage.readUserPrefs();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveUserPrefs(UserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Path getTaskListFilePath() {
        return taskListStorage.getTaskListFilePath();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskList readTaskList() throws DataConversionException, IOException {
        return taskListStorage.readTaskList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveTaskList(TaskList taskList) throws IOException {
        taskListStorage.saveTaskList(taskList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Path getMacroMapFilePath() {
        return macroMapStorage.getMacroMapFilePath();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MacroMap readMacroMap() throws IOException {
        return macroMapStorage.readMacroMap();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveMacroMap(MacroMap macroMap) throws IOException {
        macroMapStorage.saveMacroMap(macroMap);
    }
}
