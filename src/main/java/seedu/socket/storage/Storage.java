package seedu.socket.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.socket.commons.exceptions.DataConversionException;
import seedu.socket.model.ReadOnlySocket;
import seedu.socket.model.ReadOnlyUserPrefs;
import seedu.socket.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends SocketStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getSocketFilePath();

    @Override
    Optional<ReadOnlySocket> readSocket() throws DataConversionException, IOException;

    @Override
    void saveSocket(ReadOnlySocket socket) throws IOException;

}
