package vimification.storage;

import java.io.IOException;
import java.nio.file.Path;

import vimification.commons.exceptions.DataConversionException;
import vimification.model.MacroMap;
import vimification.model.TaskListRef;
import vimification.model.UserPrefs;

/**
 * Manages storage of TaskPlanner data in local storage.
 */
public class StorageManager implements Storage {

    private TaskListRefStorage taskListRefStorage;
    private JsonMacroMapStorage macroMapStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code TaskPlannerStorage} and
     * {@code UserPrefStorage}.
     */
    public StorageManager(
            TaskListRefStorage taskListRefStorage,
            JsonMacroMapStorage macroMapStorage,
            UserPrefsStorage userPrefsStorage) {
        this.taskListRefStorage = taskListRefStorage;
        this.macroMapStorage = macroMapStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // UserPrefs methods

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


    // LogicTaskList methods

    @Override
    public Path getTaskListRefFilePath() {
        return taskListRefStorage.getTaskListRefFilePath();
    }

    @Override
    public TaskListRef readTaskListRef() throws DataConversionException, IOException {
        return taskListRefStorage.readTaskListRef();
    }

    @Override
    public void saveTaskListRef(TaskListRef ref) throws IOException {
        taskListRefStorage.saveTaskListRef(ref);
    }

    public Path getMacroMapFilePath() {
        return macroMapStorage.getMacroMapFilePath();
    }

    public MacroMap readMacroMap() throws IOException {
        return macroMapStorage.readMacroMap();
    }

    public void saveMacroMap(MacroMap macroMap) throws IOException {
        macroMapStorage.saveMacroMap(macroMap);
    }
}
