package vimification.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import vimification.commons.core.LogsCenter;
import vimification.commons.exceptions.DataConversionException;
import vimification.model.ReadOnlyTaskPlanner;
import vimification.model.ReadOnlyUserPrefs;
import vimification.model.UserPrefs;

/**
 * Manages storage of TaskPlanner data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TaskPlannerStorage taskPlannerStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code TaskPlannerStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(TaskPlannerStorage taskPlannerStorage, UserPrefsStorage userPrefsStorage) {
        this.taskPlannerStorage = taskPlannerStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ TaskPlanner methods ==============================

    @Override
    public Path getTaskListFilePath() {
        return taskPlannerStorage.getTaskListFilePath();
    }

    @Override
    public Optional<ReadOnlyTaskPlanner> readTaskList() throws DataConversionException, IOException {
        return readTaskList(taskPlannerStorage.getTaskListFilePath());
    }

    @Override
    public Optional<ReadOnlyTaskPlanner> readTaskList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return taskPlannerStorage.readTaskList(filePath);
    }

    @Override

    public void saveTaskList(ReadOnlyTaskPlanner taskPlanner) throws IOException {
        saveTaskList(taskPlanner, taskPlannerStorage.getTaskListFilePath());
    }

    @Override
    public void saveTaskList(ReadOnlyTaskPlanner taskPlanner, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        taskPlannerStorage.saveTaskList(taskPlanner, filePath);
    }

}
