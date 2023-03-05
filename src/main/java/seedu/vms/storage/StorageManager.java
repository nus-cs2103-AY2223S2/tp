package seedu.vms.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.vms.commons.core.LogsCenter;
import seedu.vms.commons.exceptions.DataConversionException;
import seedu.vms.model.ReadOnlyUserPrefs;
import seedu.vms.model.UserPrefs;
import seedu.vms.model.patient.ReadOnlyAddressBook;
import seedu.vms.model.vaccination.VaxTypeManager;
import seedu.vms.storage.vaccination.VaxTypeStorage;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private VaxTypeStorage vaxTypeStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(
                AddressBookStorage addressBookStorage,
                VaxTypeStorage vaxTypeStorage,
                UserPrefsStorage userPrefsStorage) {
        this.addressBookStorage = addressBookStorage;
        this.vaxTypeStorage = vaxTypeStorage;
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

    // ================ Vax Type methods ==============================

    @Override
    public VaxTypeManager loadDefaultVaxTypes() throws RuntimeException {
        logger.fine("Attempting to load default vaccination types");
        return vaxTypeStorage.loadDefaultVaxTypes();
    }

    @Override
    public VaxTypeManager loadUserVaxTypes() throws IOException {
        return vaxTypeStorage.loadUserVaxTypes();
    }

}
