package seedu.task.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.task.commons.core.LogsCenter;
import seedu.task.commons.exceptions.DataConversionException;
import seedu.task.model.ReadOnlyPlanner;
import seedu.task.model.ReadOnlyTaskBook;
import seedu.task.model.ReadOnlyUserPrefs;
import seedu.task.model.UserPrefs;

/**
 * Manages storage of TaskBook data and Planner data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private TaskBookStorage taskBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private PlannerStorage plannerStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code TaskBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(TaskBookStorage taskBookStorage, UserPrefsStorage userPrefsStorage,
                          PlannerStorage plannerStorage) {
        this.taskBookStorage = taskBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.plannerStorage = plannerStorage;
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


    // ================ TaskBook methods ==============================

    @Override
    public Path getTaskBookFilePath() {
        return taskBookStorage.getTaskBookFilePath();
    }

    @Override
    public Optional<ReadOnlyTaskBook> readTaskBook() throws DataConversionException, IOException {
        return readTaskBook(taskBookStorage.getTaskBookFilePath());
    }

    @Override
    public Optional<ReadOnlyTaskBook> readTaskBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return taskBookStorage.readTaskBook(filePath);
    }

    @Override
    public void saveTaskBook(ReadOnlyTaskBook taskBook) throws IOException {
        saveTaskBook(taskBook, taskBookStorage.getTaskBookFilePath());
    }

    @Override
    public void saveTaskBook(ReadOnlyTaskBook taskBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to task data file: " + filePath);
        taskBookStorage.saveTaskBook(taskBook, filePath);
    }

    @Override
    public Path getPlannerFilePath() {
        return plannerStorage.getPlannerFilePath();
    }

    @Override
    public Optional<ReadOnlyPlanner> readPlanner() throws DataConversionException, IOException {
        return Optional.empty();
    }

    @Override
    public Optional<ReadOnlyPlanner> readPlanner(Path filePath) throws DataConversionException, IOException {
        return Optional.empty();
    }

    @Override
    public void savePlanner(ReadOnlyPlanner plans) throws IOException {
        savePlanner(plans, plannerStorage.getPlannerFilePath());
    }

    @Override
    public void savePlanner(ReadOnlyPlanner plans, Path filePath) throws IOException {
        logger.fine("Attempting to write to planner data file: " + filePath);
        plannerStorage.savePlanner(plans, filePath);
    }
}
