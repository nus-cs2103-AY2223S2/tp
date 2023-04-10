package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyElister;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.history.InputHistory;

/**
 * Manages storage of Elister data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private ElisterStorage elisterStorage;
    private UserPrefsStorage userPrefsStorage;
    private InputHistoryStorage inputHistoryStorage;

    /**
     * Creates a {@code StorageManager} with the given
     * {@code ElisterStorage}, {@code UserPrefStorage} and {@code HistoryStorage}.
     *
     * @param elisterStorage An object represents storage of E-Lister.
     * @param userPrefsStorage An object represents storage of user preferences.
     * @param inputHistoryStorage An object represents storage of executed commands.
     */
    public StorageManager(ElisterStorage elisterStorage,
            UserPrefsStorage userPrefsStorage, InputHistoryStorage inputHistoryStorage) {
        this.elisterStorage = elisterStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.inputHistoryStorage = inputHistoryStorage;
    }

    /**
     * Creates a {@code StorageManager} with the given arguments.
     * This constructors will initialize the {@code StorageManager} historyStorage
     * with a {@code TxtHistoryStorage} with default history file Path.
     *
     * @param elisterStorage An object represents storage of E-Lister.
     * @param userPrefsStorage An object represents storage of user preferences.
     */
    public StorageManager(ElisterStorage elisterStorage, UserPrefsStorage userPrefsStorage) {
        this.elisterStorage = elisterStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.inputHistoryStorage = new TxtInputHistoryStorage();
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


    // ================ Elister methods ==============================

    @Override
    public Path getElisterFilePath() {
        return elisterStorage.getElisterFilePath();
    }

    @Override
    public Optional<ReadOnlyElister> readElister() throws DataConversionException, IOException {
        return readElister(elisterStorage.getElisterFilePath());
    }

    @Override
    public Optional<ReadOnlyElister> readElister(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return elisterStorage.readElister(filePath);
    }

    @Override
    public void saveElister(ReadOnlyElister elister) throws IOException {
        saveElister(elister, elisterStorage.getElisterFilePath());
    }

    @Override
    public void saveElister(ReadOnlyElister elister, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        elisterStorage.saveElister(elister, filePath);
    }


    // ================ History methods ==============================

    @Override
    public Path getHistoryStoragePath() {
        return inputHistoryStorage.getHistoryStoragePath();
    }

    @Override
    public Optional<InputHistory> readInputHistory() throws IOException {
        return readInputHistory(inputHistoryStorage.getHistoryStoragePath());
    }

    @Override
    public Optional<InputHistory> readInputHistory(Path filePath) throws IOException {
        logger.fine("Reading from history file" + filePath);
        return inputHistoryStorage.readInputHistory(filePath);
    }

    @Override
    public void saveInputHistory(InputHistory history) throws IOException {
        saveInputHistory(history, inputHistoryStorage.getHistoryStoragePath());
    }

    @Override
    public void saveInputHistory(InputHistory history, Path filePath) throws IOException {
        logger.fine("Saving previous executed command(s) to the history file: " + filePath);
        inputHistoryStorage.saveInputHistory(history, filePath);
    }
}
