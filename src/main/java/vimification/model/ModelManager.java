package vimification.model;

import static java.util.Objects.requireNonNull;
import static vimification.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import vimification.commons.core.GuiSettings;
import vimification.commons.core.LogsCenter;
import vimification.model.task.Task;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final TaskPlanner taskPlanner;
    private final UserPrefs userPrefs;
    private final FilteredList<Task> filteredTasks;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyTaskPlanner taskList, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(taskList, userPrefs);

        logger.fine("Initializing with address book: " + taskList + " and user prefs " + userPrefs);

        this.taskPlanner = new TaskPlanner(taskList);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredTasks = new FilteredList<>(this.taskPlanner.getTaskList());
    }

    public ModelManager() {
        this(new TaskPlanner(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getTaskListFilePath() {
        return userPrefs.getTaskListFilePath();
    }

    @Override
    public void setTaskListFilePath(Path taskListFilePath) {
        requireNonNull(taskListFilePath);
        userPrefs.setTaskListFilePath(taskListFilePath);
    }

    //=========== TaskPlanner ================================================================================

    @Override
    public void setTaskList(ReadOnlyTaskPlanner taskList) {
        this.taskPlanner.resetData(taskList);
    }

    @Override
    public ReadOnlyTaskPlanner getTaskList() {
        return taskPlanner;
    }

    @Override
    public boolean hasTask(Task t) {
        requireNonNull(t);
        return taskPlanner.hasTask(t);
    }

    @Override
    public void deleteTask(Task target) {
        taskPlanner.removeTask(target);
    }

    @Override
    public void addTask(Task t) {
        taskPlanner.addTask(t);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);
        taskPlanner.setTask(target, editedTask);
    }

    @Override
    public void markTask(Task task) {
        requireNonNull(task);
        taskPlanner.markTask(task);
    }

    @Override
    public void unmarkTask(Task task) {
        requireNonNull(task);
        taskPlanner.unmarkTask(task);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredTasks.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return taskPlanner.equals(other.taskPlanner)
                && userPrefs.equals(other.userPrefs)
                && filteredTasks.equals(other.filteredTasks);
    }

}
