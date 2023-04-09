package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.task.Task;


/**
 * The API of the Model component.
 */
public interface TaskBookModel {

    /** {@code Predicate} that always evaluate to true */
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getTaskBookFilePath();


    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setTaskBook(ReadOnlyTaskBook taskBook);

    /** Returns the AddressBook */
    ReadOnlyTaskBook getTaskBook();

    /**
     * Returns true if a task with the same specifications as {@code task} exists in the address book.
     */
    boolean hasTask(Task task);

    /**
     * Returns true if a task exists in the address book.
     */
    boolean hasTaskIndex(Index taskIndex);


    void setTask(Task target, Task editedTask);

    /**
     * Deletes the given task.
     * Task must exist in the address book.
     */
    void deleteTask(Task target);

    /**
     * Adds the given task.
     * {@code task} must not already exist in the address book.
     * @param target
     */
    void addTask(Task target);

    /**
     * Marks the given task {@code task} as done.
     * {@code task} must exist in the address book.
     */
    void markTask(Task task, Task markedTask, Index taskIndex);

    /**
     * Unmarks the given task {@code task} as not done.
     * {@code task} must exist in the address book.
     */
    void unmarkTask(Task taskToUnmark, Task unmarkedTask, Index taskIndex);

    /**
     * Adds the given comment to the specified task.
     * {@code task} must exist in the address book.
     * @param taskToComment
     * @param commentedTask
     * @param taskIndex
     */
    void commentOnTask(Task taskToComment, Task commentedTask, Index taskIndex);


    /**
     * Replace the task to be assigned with the assigned task.
     * @param taskToAssign
     * @param assignedTask
     * @param taskIndex
     */
    void assignTask(Task taskToAssign, Task assignedTask, Index taskIndex);


    /**
     * Replace all the tasks with assigned member deleted
     * with the unassigned task.
     * @param personIndex
     */
    void deletePersonFromTask(Index personIndex);

    /**
     * Gets the filtered task list
     */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);
}
