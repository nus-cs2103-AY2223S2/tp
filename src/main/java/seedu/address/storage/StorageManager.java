package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyFriendlyLink;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private final FriendlyLinkStorage friendlyLinkStorage;
    private final UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(FriendlyLinkStorage friendlyLinkStorage, UserPrefsStorage userPrefsStorage) {
        this.friendlyLinkStorage = friendlyLinkStorage;
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
    public Path getFriendlyLinkFilePath() {
        return friendlyLinkStorage.getFriendlyLinkFilePath();
    }

    @Override
    public Optional<ReadOnlyFriendlyLink> readFriendlyLink() throws DataConversionException, IOException {
        return readFriendlyLink(friendlyLinkStorage.getFriendlyLinkFilePath());
    }

    @Override
    public Optional<ReadOnlyFriendlyLink> readFriendlyLink(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return friendlyLinkStorage.readFriendlyLink(filePath);
    }

    @Override
    public void saveFriendlyLink(ReadOnlyFriendlyLink addressBook) throws IOException {
        saveFriendlyLink(addressBook, friendlyLinkStorage.getFriendlyLinkFilePath());
    }

    @Override
    public void saveFriendlyLink(ReadOnlyFriendlyLink addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        friendlyLinkStorage.saveFriendlyLink(addressBook, filePath);
    }

}
