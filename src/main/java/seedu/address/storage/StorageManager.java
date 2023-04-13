package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyTaskBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;



/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private TaskStorage taskStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage, TaskStorage taskStorage,
        UserPrefsStorage userPrefsStorage) {
        this.addressBookStorage = addressBookStorage;
        this.taskStorage = taskStorage;
        this.userPrefsStorage = userPrefsStorage;
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
        logger.fine("Attempting to read user data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write user to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }


    // ================ Tasks methods ==============================

    @Override
    public Path getTaskBookFilePath() {
        return taskStorage.getTaskBookFilePath();
    }

    @Override
    public Optional<ReadOnlyTaskBook> readTasks() throws DataConversionException, IOException {
        return readTasks(taskStorage.getTaskBookFilePath());
    }

    @Override
    public Optional<ReadOnlyTaskBook> readTasks(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read task data from file: " + filePath);
        return taskStorage.readTasks(filePath);
    }

    @Override
    public void saveTasks(ReadOnlyTaskBook taskBook) throws IOException {
        saveTasks(taskBook, taskStorage.getTaskBookFilePath());
    }

    @Override
    public void saveTasks(ReadOnlyTaskBook taskBook, Path filePath) throws IOException {
        logger.fine("Attempting to write task to data file: " + filePath);
        taskStorage.saveTasks(taskBook, filePath);
    }


}
