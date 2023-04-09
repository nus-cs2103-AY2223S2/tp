package seedu.address.model;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.fish.Fish;
import seedu.address.model.tank.Tank;
import seedu.address.model.tank.readings.AmmoniaLevel;
import seedu.address.model.tank.readings.PH;
import seedu.address.model.tank.readings.ReadOnlyReadingLevels;
import seedu.address.model.tank.readings.Reading;
import seedu.address.model.tank.readings.Temperature;
import seedu.address.model.tank.readings.UniqueIndividualReadingLevels;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskFeedingReminder;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Fish> PREDICATE_SHOW_ALL_FISHES = unused -> true;
    Predicate<Tank> PREDICATE_SHOW_ALL_TANKS = unused -> true;
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;
    Function<Tank, Predicate<Fish>> SHOW_FISHES_IN_TANK = tank -> (fish -> fish.isInTank(tank));
    Function<Tank, Predicate<Task>> SHOW_TANK_TASKS = tank
            -> (task -> task.isTankRelatedTask() && task.getTank().isSameTank(tank));
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
     * Change the user prefs' GUI Mode while keeping everything else the same
     */
    void setGuiMode(GuiSettings.GuiMode newMode);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

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

    /** Returns an unmodifiable view of the sorted fish list */
    ObservableList<Fish> getSortedFishList();

    /**
     * Updates the filter of the filtered fish list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredFishList(Predicate<Fish> predicate);

    /**
     * Sorts the filtered fish list by the given {@code comparator}.
     */
    void sortFilteredFishList(Comparator<Fish> comparator);

    //=========== TaskList =============================================================
    void setTaskList(ReadOnlyTaskList taskList);

    ReadOnlyTaskList getTaskList();

    Path getTaskListFilePath();

    void setTaskListFilePath(Path taskListFilePath);

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

    //=========== TankList =============================================================
    void setTankList(ReadOnlyTankList tankList);

    ReadOnlyTankList getTankList();

    Path getTankListFilePath();

    void setTankListFilePath(Path tankListFilePath);

    /**
     * Returns true if a Tank with the same identity as {@code Tank} exists in Fish Ahoy!.
     */
    boolean hasTank(Tank tank);

    /**
     * Adds the given Tank.
     * {@code Tank} must not already exist in Fish Ahoy!.
     */
    void addTank(Tank tank);

    /**
     * Deletes the given Tank.
     * The Tank must exist in the address book.
     */
    void deleteTank(Tank tank);

    void setTank(Tank target, Tank editedTank);

    /** Returns an unmodifiable view of the filtered tank list */
    ObservableList<Tank> getFilteredTankList();

    void updateFilteredTankList(Predicate<Tank> predicate);

    void setLastFedDateTimeFishes(Tank tankToFeed, String formattedDate);

    Tank getTankFromIndex(Index index);

    /**
     * Executes the auto feeding reminder feature for Model
     */
    public ArrayList<TaskFeedingReminder> executeFeedingReminderInitModel();

    //=========== FullReadingLevels  =============================================================
    void addReadingsToIndividualReadingLevels(AmmoniaLevel a, PH ph, Temperature temp, Tank t);

    void setFullReadingLevels(ReadOnlyReadingLevels ammoniaLevels);

    ReadOnlyReadingLevels getFullReadingLevels();

    boolean hasIndividualReadingLevels(UniqueIndividualReadingLevels ammoniaLevels);

    void deleteIndividualReadingLevels(UniqueIndividualReadingLevels target);

    Reading[] deleteLastEntryFromIndividualReadings(UniqueIndividualReadingLevels target);

    void addIndividualReadingLevels(UniqueIndividualReadingLevels ammoniaLevels);

    void setIndividualReadingLevels(UniqueIndividualReadingLevels target,
                                    UniqueIndividualReadingLevels editedList);

    ObservableList<UniqueIndividualReadingLevels> getFilteredReadingLevels();

    void updateFilteredReadingLevels(Predicate<UniqueIndividualReadingLevels> predicate);

    Index getTankIndex(Tank tank);
}
