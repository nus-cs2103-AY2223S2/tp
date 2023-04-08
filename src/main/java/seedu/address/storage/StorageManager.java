package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyHMHero;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private HMHeroStorage HMHeroStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(HMHeroStorage HMHeroStorage, UserPrefsStorage userPrefsStorage) {
        this.HMHeroStorage = HMHeroStorage;
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
        return HMHeroStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlyHMHero> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(HMHeroStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlyHMHero> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return HMHeroStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlyHMHero addressBook) throws IOException {
        saveAddressBook(addressBook, HMHeroStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlyHMHero addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        HMHeroStorage.saveAddressBook(addressBook, filePath);
    }

}
