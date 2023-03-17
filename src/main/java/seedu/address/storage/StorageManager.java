package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyContactList;
import seedu.address.model.ReadOnlyEventBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of EventBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private EventBookStorage eventBookStorage;
    private ContactListStorage contactListStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code EventBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(EventBookStorage eventBookStorage,
                          ContactListStorage contactListStorage, UserPrefsStorage userPrefsStorage) {
        this.eventBookStorage = eventBookStorage;
        this.contactListStorage = contactListStorage;
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


    // ================ EventBook methods ==============================

    @Override
    public Path getEventBookFilePath() {
        return eventBookStorage.getEventBookFilePath();
    }

    @Override
    public Optional<ReadOnlyEventBook> readEventBook() throws DataConversionException, IOException {
        return readEventBook(eventBookStorage.getEventBookFilePath());
    }

    @Override
    public Optional<ReadOnlyEventBook> readEventBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return eventBookStorage.readEventBook(filePath);
    }

    @Override
    public void saveEventBook(ReadOnlyEventBook addressBook) throws IOException {
        saveEventBook(addressBook, eventBookStorage.getEventBookFilePath());
    }

    @Override
    public void saveEventBook(ReadOnlyEventBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        eventBookStorage.saveEventBook(addressBook, filePath);
    }

    // ================ ContactList methods ==============================

    @Override
    public Path getContactListFilePath() {
        return contactListStorage.getContactListFilePath();
    }

    @Override
    public Optional<ReadOnlyContactList> readContactList() throws DataConversionException, IOException {
        return readContactList(contactListStorage.getContactListFilePath());
    }

    @Override
    public Optional<ReadOnlyContactList> readContactList(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return contactListStorage.readContactList(filePath);
    }

    @Override
    public void saveContactList(ReadOnlyContactList contactList) throws IOException {
        saveContactList(contactList, contactListStorage.getContactListFilePath());
    }

    @Override
    public void saveContactList(ReadOnlyContactList contactList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        contactListStorage.saveContactList(contactList, filePath);
    }
}
