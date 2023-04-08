package seedu.socket.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.socket.commons.core.LogsCenter;
import seedu.socket.commons.exceptions.DataConversionException;
import seedu.socket.model.ReadOnlySocket;
import seedu.socket.model.ReadOnlyUserPrefs;
import seedu.socket.model.UserPrefs;

/**
 * Manages storage of SOCket data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private SocketStorage socketStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code SocketStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(SocketStorage socketStorage, UserPrefsStorage userPrefsStorage) {
        this.socketStorage = socketStorage;
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


    // ================ Socket methods ==============================

    @Override
    public Path getSocketFilePath() {
        return socketStorage.getSocketFilePath();
    }

    @Override
    public Optional<ReadOnlySocket> readSocket() throws DataConversionException, IOException {
        return readSocket(socketStorage.getSocketFilePath());
    }

    @Override
    public Optional<ReadOnlySocket> readSocket(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return socketStorage.readSocket(filePath);
    }

    @Override
    public void saveSocket(ReadOnlySocket socket) throws IOException {
        saveSocket(socket, socketStorage.getSocketFilePath());
    }

    @Override
    public void saveSocket(ReadOnlySocket socket, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        socketStorage.saveSocket(socket, filePath);
    }

}
