package seedu.address.storage;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyListingBook;
import seedu.address.model.ReadOnlyUserPrefs2;
import seedu.address.model.UserPrefs2;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager2 implements Storage2 {

    private static final Logger logger = LogsCenter.getLogger(StorageManager2.class);
    private ListingBookStorage listingBookStorage;
    private UserPrefsStorage2 userPrefsStorage;

    /**
     * Creates a {@code StorageManager2} with the given {@code ListingBookStorage} and {@code UserPrefStorage2}.
     */
    public StorageManager2(ListingBookStorage listingBookStorage, UserPrefsStorage2 userPrefsStorage) {
        this.listingBookStorage = listingBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs2> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs2 userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ AddressBook methods ==============================

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
