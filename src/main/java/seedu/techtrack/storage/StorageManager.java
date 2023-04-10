package seedu.techtrack.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.techtrack.commons.core.LogsCenter;
import seedu.techtrack.commons.exceptions.DataConversionException;
import seedu.techtrack.model.ReadOnlyRoleBook;
import seedu.techtrack.model.ReadOnlyUserPrefs;
import seedu.techtrack.model.UserPrefs;

/**
 * Manages storage of RoleBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private RoleBookStorage roleBookStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code RoleBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(RoleBookStorage roleBookStorage, UserPrefsStorage userPrefsStorage) {
        this.roleBookStorage = roleBookStorage;
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


    // ================ RoleBook methods ==============================

    @Override
    public Path getRoleBookFilePath() {
        return roleBookStorage.getRoleBookFilePath();
    }

    @Override
    public Optional<ReadOnlyRoleBook> readRoleBook() throws DataConversionException, IOException {
        return readRoleBook(roleBookStorage.getRoleBookFilePath());
    }

    @Override
    public Optional<ReadOnlyRoleBook> readRoleBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return roleBookStorage.readRoleBook(filePath);
    }

    @Override
    public void saveRoleBook(ReadOnlyRoleBook roleBook) throws IOException {
        saveRoleBook(roleBook, roleBookStorage.getRoleBookFilePath());
    }

    @Override
    public void saveRoleBook(ReadOnlyRoleBook roleBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        roleBookStorage.saveRoleBook(roleBook, filePath);
    }

}
