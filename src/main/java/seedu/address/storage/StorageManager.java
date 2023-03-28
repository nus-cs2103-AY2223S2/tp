package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of Mathutoring data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private MathutoringStorage mathutoringStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code MathutoringStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(MathutoringStorage mathutoringStorage, UserPrefsStorage userPrefsStorage) {
        this.mathutoringStorage = mathutoringStorage;
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


    // ================ Mathutoring methods ==============================

    @Override
    public Path getAddressBookFilePath() {
        return mathutoringStorage.getAddressBookFilePath();
    }

    @Override
    public Optional<seedu.address.model.ReadOnlyMathutoring> readAddressBook() throws DataConversionException, IOException {
        return readAddressBook(mathutoringStorage.getAddressBookFilePath());
    }

    @Override
    public Optional<seedu.address.model.ReadOnlyMathutoring> readAddressBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return mathutoringStorage.readAddressBook(filePath);
    }

    @Override
    public void saveAddressBook(seedu.address.model.ReadOnlyMathutoring addressBook) throws IOException {
        saveAddressBook(addressBook, mathutoringStorage.getAddressBookFilePath());
    }

    @Override
    public void saveAddressBook(seedu.address.model.ReadOnlyMathutoring addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        mathutoringStorage.saveAddressBook(addressBook, filePath);
    }

}
