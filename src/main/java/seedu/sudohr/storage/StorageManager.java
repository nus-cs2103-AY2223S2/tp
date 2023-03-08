package seedu.sudohr.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.sudohr.commons.core.LogsCenter;
import seedu.sudohr.commons.exceptions.DataConversionException;
import seedu.sudohr.model.ReadOnlySudoHr;
import seedu.sudohr.model.ReadOnlyUserPrefs;
import seedu.sudohr.model.UserPrefs;

/**
 * Manages storage of SudoHr data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private SudoHrStorage sudoHrStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code SudoHrStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(SudoHrStorage sudoHrStorage, UserPrefsStorage userPrefsStorage) {
        this.sudoHrStorage = sudoHrStorage;
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


    // ================ SudoHr methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return sudoHrStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<ReadOnlySudoHr> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(sudoHrStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<ReadOnlySudoHr> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return sudoHrStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(ReadOnlySudoHr addressBook) throws IOException {
        saveAddressBook(addressBook, sudoHrStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(ReadOnlySudoHr addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        sudoHrStorage.saveAddressBook(addressBook, filePath);
    }

}
