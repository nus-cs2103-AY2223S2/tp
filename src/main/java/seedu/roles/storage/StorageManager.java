package seedu.roles.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.roles.commons.core.LogsCenter;
import seedu.roles.commons.exceptions.DataConversionException;
import seedu.roles.model.ReadOnlyRoleBook;
import seedu.roles.model.ReadOnlyUserPrefs;
import seedu.roles.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
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


    // ================ AddressBook methods ==============================

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
    public void saveRoleBook(ReadOnlyRoleBook addressBook) throws IOException {
        saveRoleBook(addressBook, roleBookStorage.getRoleBookFilePath());
    }

    @Override
    public void saveRoleBook(ReadOnlyRoleBook addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        roleBookStorage.saveRoleBook(addressBook, filePath);
    }

}
