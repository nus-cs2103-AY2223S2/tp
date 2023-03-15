package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserData;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.storage.addressbook.JsonAddressBookStorage;
import seedu.address.storage.user.JsonUserDataStorage;
import seedu.address.storage.userpref.UserPrefsStorage;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private JsonAddressBookStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private JsonUserDataStorage userDataStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(JsonAddressBookStorage addressBookStorage, UserPrefsStorage userPrefsStorage,
                          JsonUserDataStorage userDataStorage) {
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.userDataStorage = userDataStorage;
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
        return addressBookStorage.getFilePath();
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(addressBookStorage.getFilePath());
    }

    @Override
    public Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readData(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveData(addressBook, filePath);
    }

    // ================ User Data methods ==============================

    @Override
    public Path getUserDataFilePath() {
        return this.userDataStorage.getFilePath();
    }

    @Override
    public Optional<ReadOnlyUserData> readUserData() throws DataConversionException, IOException {
        return readUserData(this.userDataStorage.getFilePath());
    }

    @Override
    public Optional<ReadOnlyUserData> readUserData(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return this.userDataStorage.readData(filePath);
    }

    @Override
    public void saveUserData(ReadOnlyUserData userData) throws IOException {
        saveUserData(userData, this.userDataStorage.getFilePath());
    }

    @Override
    public void saveUserData(ReadOnlyUserData userData, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        this.userDataStorage.saveData(userData, filePath);
    }

}
