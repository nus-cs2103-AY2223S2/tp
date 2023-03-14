package seedu.task.model;

import static java.util.Objects.requireNonNull;
import static seedu.task.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.task.commons.core.GuiSettings;
import seedu.task.commons.core.LogsCenter;
import seedu.task.model.task.Task;

/**
 * Represents the in-memory model of the task book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final TaskBook taskBook;
    private final UserPrefs userPrefs;
    private final Planner planner;
    private final FilteredList<Task> filteredTasks;
    private final FilteredList<Task> alertTasks;

    /**
     * Initializes a ModelManager with the given taskBook and userPrefs.
     */
    public ModelManager(ReadOnlyTaskBook taskBook, ReadOnlyUserPrefs userPrefs, ReadOnlyPlanner planner) {
        requireAllNonNull(taskBook, userPrefs);

        logger.fine("Initializing with task book: " + taskBook + " and user prefs " + userPrefs);

        this.taskBook = new TaskBook(taskBook);
        this.userPrefs = new UserPrefs(userPrefs);
        this.planner = new Planner(planner);
        filteredTasks = new FilteredList<>(this.taskBook.getTaskList());
        alertTasks = new FilteredList<>(this.taskBook.getTaskList());
    }

    public ModelManager() {
        this(new TaskBook(), new UserPrefs(), new Planner());
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
    public Path getTaskBookFilePath() {
        return userPrefs.getTaskBookFilePath();
    }

    @Override
    public void setTaskBookFilePath(Path taskBookFilePath) {
        requireNonNull(taskBookFilePath);
        userPrefs.setTaskBookFilePath(taskBookFilePath);
    }

    @Override
    public Path getPlannerFilePath() {
        return userPrefs.getPlannerFilePath();
    }

    //=========== TaskBook ================================================================================

    @Override
    public void setTaskBook(ReadOnlyTaskBook taskBook) {
        this.taskBook.resetData(taskBook);
    }

    @Override
    public ReadOnlyTaskBook getTaskBook() {
        return taskBook;
    }

    @Override
    public ReadOnlyPlanner getPlanner() {
        return planner;
    }

    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return taskBook.hasTask(task);
    }

    @Override
    public void deleteTask(Task target) {
        taskBook.removeTask(target);
    }

    @Override
    public void addTask(Task task) {
        taskBook.addTask(task);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        taskBook.setTask(target, editedTask);
    }

    @Override
    public void sortTask() {
        taskBook.sortTask();
    }

    @Override
    public void plan(int workload) {
        taskBook.plan(workload, planner);
    }

    //=========== Filtered Task List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Task} backed by the internal list of
     * {@code versionedTaskBook}
     */
    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
    }

    /**
     * Returns an unmodifiable view of the list of {@code Task} backed by the internal list of
     * {@code versionedTaskBook}
     */
    @Override
    public ObservableList<Task> getAlertTaskList() {
        return alertTasks;
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredTasks.setPredicate(predicate);
    }

    @Override
    public void updateAlertTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        alertTasks.setPredicate(predicate);
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
        return taskBook.equals(other.taskBook)
                && userPrefs.equals(other.userPrefs)
                && filteredTasks.equals(other.filteredTasks)
                && alertTasks.equals(other.alertTasks);
    }

}
