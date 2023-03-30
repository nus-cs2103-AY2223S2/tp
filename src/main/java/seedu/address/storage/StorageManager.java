package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.history.InputHistory;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private InputHistoryStorage inputHistoryStorage;

    /**
     * Creates a {@code StorageManager} with the given
     * {@code AddressBookStorage}, {@code UserPrefStorage} and {@code HistoryStorage}.
     *
     * @param addressBookStorage An object represents storage of address book.
     * @param userPrefsStorage An object represents storage of user preferences.
     * @param inputHistoryStorage An object represents storage of executed commands.
     */
    public StorageManager(AddressBookStorage addressBookStorage,
            UserPrefsStorage userPrefsStorage, InputHistoryStorage inputHistoryStorage) {
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.inputHistoryStorage = inputHistoryStorage;
    }

    /**
     * Creates a {@code StorageManager} with the given arguments.
     * This constructors will initialize the {@code StorageManager} historyStorage
     * with a {@code TxtHistoryStorage} with default history file Path.
     *
     * @param addressBookStorage An object represents storage of address book.
     * @param userPrefsStorage An object represents storage of user preferences.
     */
    public StorageManager(AddressBookStorage addressBookStorage, UserPrefsStorage userPrefsStorage) {
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.inputHistoryStorage = new TxtInputHistoryStorage();
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


    // ================ History methods ==============================

    @Override
    public Path getHistoryStoragePath() {
        return inputHistoryStorage.getHistoryStoragePath();
    }

    @Override
    public Optional<InputHistory> readInputHistory() throws IOException {
        return readInputHistory(inputHistoryStorage.getHistoryStoragePath());
    }

    @Override
    public Optional<InputHistory> readInputHistory(Path filePath) throws IOException {
        logger.fine("Reading from history file" + filePath);
        return inputHistoryStorage.readInputHistory(filePath);
    }

    @Override
    public void saveInputHistory(InputHistory history) throws IOException {
        saveInputHistory(history, inputHistoryStorage.getHistoryStoragePath());
    }

    @Override
    public void saveInputHistory(InputHistory history, Path filePath) throws IOException {
        logger.fine("Saving previous executed command(s) to the history file: " + filePath);
        inputHistoryStorage.saveInputHistory(history, filePath);
    }
}
