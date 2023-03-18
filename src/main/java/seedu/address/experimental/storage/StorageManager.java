package seedu.address.experimental.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.experimental.model.ReadOnlyReroll;
import seedu.address.experimental.model.ReadOnlyUserPrefs;
import seedu.address.experimental.model.UserPrefs;

/**
 * Manages storage of AddressBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private RerollStorage rerollStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code AddressBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(RerollStorage rerollStorage, UserPrefsStorage userPrefsStorage) {
        this.rerollStorage = rerollStorage;
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

    // ================ Reroll methods ==============================

    @Override
    public Path getRerollFilePath() {
        return rerollStorage.getRerollFilePath();
    }

    @Override
    public Optional<ReadOnlyReroll> readReroll() throws DataConversionException, IOException {
        return readReroll(rerollStorage.getRerollFilePath());
    }

    @Override
    public Optional<ReadOnlyReroll> readReroll(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return rerollStorage.readReroll(filePath);
    }

    @Override
    public void saveReroll(ReadOnlyReroll reroll) throws IOException {
        saveReroll(reroll, rerollStorage.getRerollFilePath());
    }

    @Override
    public void saveReroll(ReadOnlyReroll reroll, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        rerollStorage.saveReroll(reroll, filePath);
    }

}
