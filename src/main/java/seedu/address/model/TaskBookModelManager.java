package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.task.Task;

/**
 * Represents the in-memory model of the address book data.
 */
public class TaskBookModelManager implements TaskBookModel {
    private static final Logger logger = LogsCenter.getLogger(TaskBookModelManager.class);

    private final TaskBook taskBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Task> filteredTasks;

    /**
     * Initializes a ModelManager with the given taskBook and userPrefs.
     */
    public TaskBookModelManager(ReadOnlyTaskBook taskBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(taskBook, userPrefs);

        logger.fine("Initializing with task book: " + taskBook + " and user prefs " + userPrefs);

        this.taskBook = new TaskBook(taskBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredTasks = new FilteredList<>(this.taskBook.getTaskList());
    }

    public TaskBookModelManager() {
        this(new TaskBook(), new UserPrefs());
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


    //=========== Task Book ================================================================================

    @Override
    public void setTaskBook(ReadOnlyTaskBook taskBook) {
        this.taskBook.resetData(taskBook);
    }

    @Override
    public ReadOnlyTaskBook getTaskBook() {
        return taskBook;
    }


    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return taskBook.hasTask(task);
    }

    @Override
    public boolean hasTaskIndex(Index taskIndex) {
        requireNonNull(taskIndex);
        return taskBook.hasTaskIndex(taskIndex);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        taskBook.setTask(target, editedTask);
    }

    @Override
    public void deleteTask(Task target) {
        taskBook.removeTask(target);
    }

    @Override
    public void addTask(Task task) {
        taskBook.addTask(task);
    }

    @Override
    public void commentOnTask(Task taskToComment, Task commentedTask, Index taskIndex) {
        taskBook.commentOnTask(taskToComment, commentedTask, taskIndex);
    }

    @Override
    public void markTask(Task task, Task markedTask, Index taskIndex) {
        requireNonNull(task);
        requireNonNull(markedTask);
        requireNonNull(taskIndex);
        taskBook.markTask(task, markedTask, taskIndex);
    }

    @Override
    public void unmarkTask(Task taskToUnmark, Task unmarkedTask, Index taskIndex) {
        requireNonNull(taskToUnmark);
        requireNonNull(unmarkedTask);
        requireNonNull(taskIndex);
        taskBook.unmarkTask(taskToUnmark, unmarkedTask, taskIndex);
    }

    @Override
    public void assignTask(Task taskToAssign, Task assignedTask, Index taskIndex) {
        taskBook.assignTask(taskToAssign, assignedTask, taskIndex);
    }

    @Override
    public void deletePersonFromTask(Index personIndex) {
        taskBook.deletePersonFromTask(personIndex);
    }


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
        if (!(obj instanceof TaskBookModelManager)) {
            return false;
        }

        // state check
        TaskBookModelManager other = (TaskBookModelManager) obj;
        return taskBook.equals(other.taskBook)
                && userPrefs.equals(other.userPrefs)
                && filteredTasks.equals(other.filteredTasks);
    }
}
