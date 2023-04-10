package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyDeliveryJobSystem;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private AddressBookStorage addressBookStorage;
    private DeliveryJobSystemStorage deliveryJobSystemStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage}
     * and {@code UserPrefStorage}.
     */
    public StorageManager(AddressBookStorage addressBookStorage, DeliveryJobSystemStorage deliveryJobSystemStorage,
            UserPrefsStorage userPrefsStorage) {
        this.addressBookStorage = addressBookStorage;
        this.deliveryJobSystemStorage = deliveryJobSystemStorage;
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
        logger.fine("[AB] Attempting to read data from file: " + filePath);
        return addressBookStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException {
        saveAddressBook(addressBook, addressBookStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyAddressBook addressBook, Path filePath) throws IOException {
        logger.fine("[AB] Attempting to write to data file: " + filePath);
        addressBookStorage.saveAddressBook(addressBook, filePath);
    }

    // ================ Delivery Job system methods ==============================

    @Override
    public Path getDeliveryJobFilePath() {
        return deliveryJobSystemStorage.getDeliveryJobFilePath();
    }

    @Override
    public Optional<ReadOnlyDeliveryJobSystem> readDeliveryJobSystem() throws DataConversionException, IOException {
        return readDeliveryJobSystem(deliveryJobSystemStorage.getDeliveryJobFilePath());
    }

    @Override
    public Optional<ReadOnlyDeliveryJobSystem> readDeliveryJobSystem(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("[DJ] Attempting to read data from file: " + filePath);
        return deliveryJobSystemStorage.readDeliveryJobSystem(filePath);
    }

    @Override
    public void saveDeliveryJobSystem(ReadOnlyDeliveryJobSystem deliveryJobSystem) throws IOException {
        saveDeliveryJobSystem(deliveryJobSystem, deliveryJobSystemStorage.getDeliveryJobFilePath());
    }

    @Override
    public void saveDeliveryJobSystem(ReadOnlyDeliveryJobSystem deliveryJobSystem, Path filePath) throws IOException {
        logger.fine("[DJ] Attempting to write to data file: " + filePath);
        deliveryJobSystemStorage.saveDeliveryJobSystem(deliveryJobSystem, filePath);
    }

}
