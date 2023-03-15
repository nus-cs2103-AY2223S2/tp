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
import seedu.address.model.fish.Fish;
import seedu.address.model.tank.Tank;
import seedu.address.model.task.Task;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Fish> filteredFish;
    private final TaskList taskList;
    private final FilteredList<Task> filteredTasks;
    private final TankList tankList;
    private final FilteredList<Tank> filteredTanks;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs,
                        ReadOnlyTaskList taskList, ReadOnlyTankList tankList) {
        requireAllNonNull(addressBook, userPrefs, taskList);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredFish = new FilteredList<>(this.addressBook.getFishList());
        this.taskList = new TaskList(taskList);
        filteredTasks = new FilteredList<>(this.taskList.getTaskList());
        this.tankList = new TankList(tankList);
        filteredTanks = new FilteredList<>(this.tankList.getTankList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs(), new TaskList(), new TankList());
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
        updateFilteredFishList(PREDICATE_SHOW_ALL_FISHES);
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
    public void updateFilteredFishList(Predicate<Fish> predicate) {
        requireNonNull(predicate);
        filteredFish.setPredicate(predicate);
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

}
