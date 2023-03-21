package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyInternshipBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class ApplicationStorageManager implements ApplicationStorage {

    private static final Logger logger = LogsCenter.getLogger(ApplicationStorageManager.class);
    private InternshipBookStorage internshipBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code ApplicationStorageManager} with the given
     * {@code InternshipBookStorage} and {@code UserPrefStorage}.
     */
    public ApplicationStorageManager(InternshipBookStorage internshipBookStorage, UserPrefsStorage userPrefsStorage) {
        this.internshipBookStorage = internshipBookStorage;
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
    public Path getInternshipBookFilePath() {
        return internshipBookStorage.getInternshipBookFilePath();
    }

    @Override
    public Optional<ReadOnlyInternshipBook> readInternshipBook() throws DataConversionException, IOException {
        return readInternshipBook(internshipBookStorage.getInternshipBookFilePath());
    }

    @Override
    public Optional<ReadOnlyInternshipBook> readInternshipBook(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return internshipBookStorage.readInternshipBook(filePath);
    }

    @Override
    public void saveInternshipBook(ReadOnlyInternshipBook internshipBook) throws IOException {
        saveInternshipBook(internshipBook, internshipBookStorage.getInternshipBookFilePath());
    }

    @Override
    public void saveInternshipBook(ReadOnlyInternshipBook internshipBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        internshipBookStorage.saveInternshipBook(internshipBook, filePath);
    }

}
