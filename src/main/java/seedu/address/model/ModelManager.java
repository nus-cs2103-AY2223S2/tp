package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.model.fish.Fish;
import seedu.address.model.tank.Tank;
import seedu.address.model.tank.readings.AmmoniaLevel;
import seedu.address.model.tank.readings.FullReadingLevels;
import seedu.address.model.tank.readings.PH;
import seedu.address.model.tank.readings.ReadOnlyReadingLevels;
import seedu.address.model.tank.readings.Reading;
import seedu.address.model.tank.readings.Temperature;
import seedu.address.model.tank.readings.UniqueIndividualReadingLevels;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskFeedingReminder;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    /* Compares tasks and sorts them in order of high > medium > low */
    public static final Comparator<Task> PRIORITY_COMPARATOR = (t1, t2) -> {
        return t1.hasPriority()
                ? t2.hasPriority()
                    ? t1.getPriority().compare(t2.getPriority())
                    : -1 /* if t2 has no priority, order behind t1*/
                : 1; /* if t1 has no priority, order behind t2 regardless*/

    };
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Fish> filteredFish;
    private final SortedList<Fish> sortedFish;
    private final TaskList taskList;
    private final FilteredList<Task> filteredTasks;
    private final TankList tankList;
    private final FilteredList<Tank> filteredTanks;
    private final FullReadingLevels fullReadingLevels;
    private final FilteredList<UniqueIndividualReadingLevels> filteredReadingLevels;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs,
                        ReadOnlyTaskList taskList, ReadOnlyTankList tankList, ReadOnlyReadingLevels fullReadings) {
        requireAllNonNull(addressBook, userPrefs, taskList);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        sortedFish = new SortedList<>(this.addressBook.getFishList());
        filteredFish = new FilteredList<>(sortedFish);
        this.taskList = new TaskList(taskList);
        SortedList<Task> sortedTasks = new SortedList<>(this.taskList.getTaskList());
        sortedTasks.setComparator(PRIORITY_COMPARATOR);
        filteredTasks = new FilteredList<>(sortedTasks);
        this.tankList = new TankList(tankList);
        filteredTanks = new FilteredList<>(this.tankList.getTankList());
        this.fullReadingLevels = new FullReadingLevels(fullReadings);
        filteredReadingLevels = new FilteredList<>(this.fullReadingLevels.getFullReadingLevels());

        updateTanksOfEachFishAndFishListOfEachTank();
        updateTankOfEachTask();
        updateTankOfEachIndividualReadings();
    }

    /**
     * Another constructor for model manager
     */
    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new TaskList(), new TankList(),
                new FullReadingLevels());
    }

    /**
     * Sets the Tank attribute of each tank's UniqueIndividualReadings to the correct real instance
     */
    public void updateTankOfEachIndividualReadings() {
        for (UniqueIndividualReadingLevels r : fullReadingLevels.getFullReadingLevels()) {
            Tank duplicateTank = r.getTank();
            Tank realTankInstance = getTankListTankInstance(duplicateTank);
            realTankInstance.setIndividualReadingLeves(r);
            r.setTank(realTankInstance);
        }
    }

    /**
     * Points the {@code Tank} object in each Task to the right instance.
     */
    public void updateTankOfEachTask() {
        for (Task t : taskList.getTaskList()) {
            if (t.isTankRelatedTask()) {
                Tank realInstance = getTankListTankInstance(t.getTank());
                t.setTank(realInstance);
            }
        }
    }

    /**
     * Points the {@code Tank} attribute of a Fish to the correct instance of {@code Tank}
     * and sets each {@code Fish} in the fishlist/addressbook of a tank to the correct instance
     */
    public void updateTanksOfEachFishAndFishListOfEachTank() {
        for (Fish f : addressBook.getFishList()) {
            Tank t = f.getTank();
            Tank realInstance = getTankListTankInstance(t);
            requireNonNull(realInstance);
            realInstance.getFishList().addFish(f);
            f.setTank(realInstance);
        }
    }

    public Tank getTankListTankInstance(Tank duplicate) {
        for (Tank t : tankList.getTankList()) {
            if (t.equals(duplicate)) {
                return t;
            }
        }
        return null;
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
    public void setGuiMode(GuiSettings.GuiMode newMode) {
        requireNonNull(newMode);
        GuiSettings currentSettings = userPrefs.getGuiSettings();
        userPrefs.setGuiSettings(currentSettings.changeGuiMode(newMode));
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
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

    @Override
    public Path getTankListFilePath() {
        return userPrefs.getTankListFilePath();
    }

    @Override
    public void setTankListFilePath(Path tankListFilePath) {
        requireNonNull(tankListFilePath);
        userPrefs.setTaskListFilePath(tankListFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasFish(Fish fish) {
        requireNonNull(fish);
        return addressBook.hasFish(fish);
    }

    @Override
    public void deleteFish(Fish target) {
        addressBook.removeFish(target);
    }

    @Override
    public void addFish(Fish fish) {
        addressBook.addFish(fish);
    }

    @Override
    public void setFish(Fish target, Fish editedFish) {
        requireAllNonNull(target, editedFish);

        addressBook.setFish(target, editedFish);
    }

    //=========== Filtered Fish List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Fish} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Fish> getFilteredFishList() {
        return filteredFish;
    }

    @Override
    public ObservableList<Fish> getSortedFishList() {
        return sortedFish;
    }

    @Override
    public void updateFilteredFishList(Predicate<Fish> predicate) {
        requireNonNull(predicate);
        filteredFish.setPredicate(predicate);
    }

    @Override
    public void sortFilteredFishList(Comparator<Fish> comparator) {
        requireNonNull(comparator);
        sortedFish.setComparator(comparator);
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
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredFish.equals(other.filteredFish)
                && taskList.equals(other.taskList)
                && filteredTasks.equals(other.filteredTasks)
                && filteredTanks.equals(other.filteredTanks);
    }

    //=========== TaskList =============================================================
    @Override
    public void setTaskList(ReadOnlyTaskList taskList) {
        this.taskList.resetData(taskList);
    }

    @Override
    public ReadOnlyTaskList getTaskList() {
        return taskList;
    }

    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return taskList.hasTask(task);
    }

    @Override
    public void deleteTask(Task target) {
        taskList.removeTask(target);
    }

    @Override
    public void addTask(Task task) {
        taskList.addTask(task);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        taskList.setTask(target, editedTask);
    }

    //=========== Filtered TaskList Accessors =============================================================

    /**
     * Returns an unmodifiable view of the {@code TaskList}.
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

    //=========== TankList tank =============================================================
    @Override
    public void setTankList(ReadOnlyTankList tankList) {
        this.tankList.resetData(tankList);
    }

    @Override
    public ReadOnlyTankList getTankList() {
        return tankList;
    }

    @Override
    public boolean hasTank(Tank tank) {
        requireNonNull(tank);
        return tankList.hasTank(tank);
    }

    @Override
    public void deleteTank(Tank target) {
        tankList.removeTank(target);
    }

    @Override
    public void addTank(Tank tank) {
        tankList.addTank(tank);
    }

    @Override
    public void setTank(Tank target, Tank editedTank) {
        requireAllNonNull(target, editedTank);

        tankList.setTank(target, editedTank);
    }

    /**
     * Sets the lastFedDate field of all fishes in this {@code tankToFeed}
     * with new LastFedDate object with {@code newDate}.
     */
    @Override
    public void setLastFedDateTimeFishes(Tank tankToFeed, String newDateTime) {
        requireAllNonNull(tankToFeed, newDateTime);

        tankToFeed.setLastFedDateTimeFishes(newDateTime);
        updateFilteredFishList(PREDICATE_SHOW_ALL_FISHES);
    }

    //=========== Filtered TankList Accessors =============================================================

    /**
     * Returns an unmodifiable view of the {@code TankList}.
     */
    @Override
    public ObservableList<Tank> getFilteredTankList() {
        return filteredTanks;
    }

    @Override
    public void updateFilteredTankList(Predicate<Tank> predicate) {
        requireNonNull(predicate);
        filteredTanks.setPredicate(predicate);
    }

    @Override
    public Tank getTankFromIndex(Index index) {
        return filteredTanks.get(index.getZeroBased());
    }

    @Override
    public Index getTankIndex(Tank tank) {
        return Index.fromZeroBased(filteredTanks.indexOf(tank));
    }

    //=========== Feeding reminders =============================================================
    @Override
    public ArrayList<TaskFeedingReminder> executeFeedingReminderInitModel() {
        //create new Feeding reminders and returns it
        ArrayList<Tank> tanksWithUnfedFish = tankList.getTanksWithUnfedFish();
        ArrayList<TaskFeedingReminder> reminders = TaskFeedingReminder
                .createListOfFeedingReminders(tanksWithUnfedFish);
        return reminders;
    }

    //=========== FullReadingLevels  =============================================================
    @Override
    public void addReadingsToIndividualReadingLevels(AmmoniaLevel a, PH pH, Temperature temp, Tank t) {
        this.fullReadingLevels.addReadingsToIndividualReadingLevels(a, pH, temp, t);
    }

    @Override
    public void setFullReadingLevels(ReadOnlyReadingLevels readingLevels) {
        this.fullReadingLevels.resetData(readingLevels);
    }

    @Override
    public ReadOnlyReadingLevels getFullReadingLevels() {
        return fullReadingLevels;
    }

    @Override
    public boolean hasIndividualReadingLevels(UniqueIndividualReadingLevels readingLevels) {
        requireNonNull(readingLevels);
        return fullReadingLevels.hasIndividualReadingLevels(readingLevels);
    }

    @Override
    public void deleteIndividualReadingLevels(UniqueIndividualReadingLevels target) {
        fullReadingLevels.removeIndividualReadingLevel(target);
    }

    @Override
    public Reading[] deleteLastEntryFromIndividualReadings(UniqueIndividualReadingLevels target) {
        return target.removeLastReadings();
    }

    @Override
    public void addIndividualReadingLevels(UniqueIndividualReadingLevels readingLevels) {
        fullReadingLevels.addIndividualReadingLevel(readingLevels);
    }

    @Override
    public void setIndividualReadingLevels(UniqueIndividualReadingLevels target,
                                           UniqueIndividualReadingLevels editedList) {
        requireAllNonNull(target, editedList);
        fullReadingLevels.setIndividualReadingLevel(target, editedList);
    }

    //=========== Filtered FullReadingLevels Accessors =============================================================

    /**
     * Returns an unmodifiable view of the {@code FullReadingLevels}.
     */
    @Override
    public ObservableList<UniqueIndividualReadingLevels> getFilteredReadingLevels() {
        return filteredReadingLevels;
    }

    @Override
    public void updateFilteredReadingLevels(Predicate<UniqueIndividualReadingLevels> predicate) {
        requireNonNull(predicate);
        filteredReadingLevels.setPredicate(predicate);
    }

}
