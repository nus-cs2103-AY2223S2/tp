package vimification.storage;

import java.io.IOException;
import java.nio.file.Path;

import vimification.commons.exceptions.DataConversionException;
import vimification.model.LogicTaskList;
import vimification.model.UserPrefs;

/**
 * Manages storage of TaskPlanner data in local storage.
 */
public class StorageManager implements Storage {

    private LogicTaskListStorage logicTaskListStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code TaskPlannerStorage} and
     * {@code UserPrefStorage}.
     */
    public StorageManager(
            LogicTaskListStorage logicTaskListStorage,
            UserPrefsStorage userPrefsStorage) {
        this.logicTaskListStorage = logicTaskListStorage;
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
    public Path getLogicTaskListFilePath() {
        return logicTaskListStorage.getLogicTaskListFilePath();
    }

    @Override
    public LogicTaskList readLogicTaskList() throws DataConversionException, IOException {
        return logicTaskListStorage.readLogicTaskList();
    }

    @Override
    public void saveLogicTaskList(LogicTaskList logicTaskList) throws IOException {
        logicTaskListStorage.saveLogicTaskList(logicTaskList);
    }

}
