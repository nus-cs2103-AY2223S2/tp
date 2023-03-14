package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyListingBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of ListingBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ListingBookStorage listingBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(ListingBookStorage listingBookStorage, UserPrefsStorage userPrefsStorage) {
        this.listingBookStorage = listingBookStorage;
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


    // ================ ListingBook methods ==============================

    @Override
    public Path getListingBookFilePath() {
        return listingBookStorage.getListingBookFilePath();
    }

    @Override
    public Optional<ReadOnlyListingBook> readListingBook() throws DataConversionException, IOException {
        return readListingBook(listingBookStorage.getListingBookFilePath());
    }

    @Override
    public Optional<ReadOnlyListingBook> readListingBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return listingBookStorage.readListingBook(filePath);
    }

    @Override
    public void saveListingBook(ReadOnlyListingBook listingBook) throws IOException {
        saveListingBook(listingBook, listingBookStorage.getListingBookFilePath());
    }

    @Override
    public void saveListingBook(ReadOnlyListingBook listingBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        listingBookStorage.saveListingBook(listingBook, filePath);
    }

}
