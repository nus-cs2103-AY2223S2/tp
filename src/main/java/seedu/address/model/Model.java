package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.fish.Fish;
import seedu.address.model.task.Task;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Fish> PREDICATE_SHOW_ALL_FISHES = unused -> true;

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
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    Path getTaskListFilePath();

    void setTaskListFilePath(Path TaskListFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a fish with the same identity as {@code fish} exists in the address book.
     */
    boolean hasFish(Fish fish);

    /**
     * Deletes the given fish.
     * The fish must exist in the address book.
     */
    void deleteFish(Fish target);

    /**
     * Adds the given fish.
     * {@code fish} must not already exist in the address book.
     */
    void addFish(Fish fish);

    /**
     * Replaces the given fish {@code target} with {@code editedFish}.
     * {@code target} must exist in the address book.
     * The fish identity of {@code editedFish} must not be the same as another existing fish in the address book.
     */
    void setFish(Fish target, Fish editedFish);

    /** Returns an unmodifiable view of the filtered fish list */
    ObservableList<Fish> getFilteredFishList();

    /**
     * Updates the filter of the filtered fish list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredFishList(Predicate<Fish> predicate);

    //=========== TaskList =============================================================
    void setTaskList(ReadOnlyTaskList TaskList);

    ReadOnlyTaskList getTaskList();

    /**
     * Returns true if a Task with the same identity as {@code Task} exists in Fish Ahoy!.
     */
    boolean hasTask(Task task);

    /**
     * Adds the given Task.
     * {@code Task} must not already exist in Fish Ahoy!.
     */
    void addTask(Task task);

    /**
     * Deletes the given Task.
     * The Task must exist in the address book.
     */
    void deleteTask(Task task);

    void setTask(Task target, Task editedTask);

    /** Returns an unmodifiable view of the filtered task list */
    ObservableList<Task> getFilteredTaskList();

    void updateFilteredTaskList(Predicate<Task> predicate);
}
