package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyTankList;
import seedu.address.model.ReadOnlyTaskList;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.tank.readings.ReadOnlyReadingLevels;
import seedu.address.storage.fish.AddressBookStorage;
import seedu.address.storage.tank.TankListStorage;
import seedu.address.storage.tank.readings.ammonialevels.FullReadingLevelsStorage;
import seedu.address.storage.task.TaskListStorage;
import seedu.address.storage.userprefs.UserPrefsStorage;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private final TaskListStorage taskListStorage;
    private final TankListStorage tankListStorage;
    private final FullReadingLevelsStorage fullReadingLevelsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage,
                          UserPrefsStorage userPrefsStorage,
                          TaskListStorage taskListStorage, TankListStorage tankListStorage,
                          FullReadingLevelsStorage ammoniaLevelsStorage) {
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.taskListStorage = taskListStorage;
        this.tankListStorage = tankListStorage;
        this.fullReadingLevelsStorage = ammoniaLevelsStorage;
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


    // ================ AddressBook methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return addressBookStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    // ================ TaskList methods ==============================

    @Override
    public Path getTaskListFilePath() {
        return taskListStorage.getTaskListFilePath();
    }

    @Override
    public Optional<ReadOnlyTaskList> readTaskList()
            throws DataConversionException,
            IOException {
        return readTaskList(taskListStorage.getTaskListFilePath());
    }

    @Override
    public Optional<ReadOnlyTaskList> readTaskList(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return taskListStorage.readTaskList(filePath);
    }

    @Override
    public void saveTaskList(ReadOnlyTaskList taskList) throws IOException {
        saveTaskList(taskList, taskListStorage.getTaskListFilePath());
    }

    @Override
    public void saveTaskList(ReadOnlyTaskList taskList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        taskListStorage.saveTaskList(taskList, filePath);
    }

    // ================ TankList methods ==============================
    @Override
    public Path getTankListFilePath() {
        return tankListStorage.getTankListFilePath();
    }

    @Override
    public Optional<ReadOnlyTankList> readTankList()
            throws DataConversionException,
            IOException {
        return readTankList(tankListStorage.getTankListFilePath());
    }

    @Override
    public Optional<ReadOnlyTankList> readTankList(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return tankListStorage.readTankList(filePath);
    }

    @Override
    public void saveTankList(ReadOnlyTankList tankList) throws IOException {
        saveTankList(tankList, tankListStorage.getTankListFilePath());
    }

    @Override
    public void saveTankList(ReadOnlyTankList tankList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        tankListStorage.saveTankList(tankList, filePath);
    }

    // ================ Readings methods ==============================
    @Override
    public Path getFullReadingLevelsFilePath() {
        return fullReadingLevelsStorage.getFullReadingLevelsFilePath();
    }

    @Override
    public Optional<ReadOnlyReadingLevels> readFullReadingLevels()
            throws DataConversionException,
            IOException {
        return readFullReadingLevels(fullReadingLevelsStorage.getFullReadingLevelsFilePath());
    }

    @Override
    public Optional<ReadOnlyReadingLevels> readFullReadingLevels(Path filePath)
            throws DataConversionException {
        logger.fine("Attempting to read data from file: " + filePath);
        return fullReadingLevelsStorage.readFullReadingLevels(filePath);
    }

    @Override
    public void saveFullReadingLevels(ReadOnlyReadingLevels fullReadingLevels) throws IOException {
        saveFullReadingLevels(fullReadingLevels, fullReadingLevelsStorage.getFullReadingLevelsFilePath());
    }

    @Override
    public void saveFullReadingLevels(ReadOnlyReadingLevels ammoniaLevels, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        fullReadingLevelsStorage.saveFullReadingLevels(ammoniaLevels, filePath);
    }
}
