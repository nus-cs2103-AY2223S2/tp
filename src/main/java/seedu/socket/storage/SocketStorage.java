package seedu.socket.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.socket.commons.exceptions.DataConversionException;
import seedu.socket.model.ReadOnlySocket;
import seedu.socket.model.Socket;

/**
 * Represents a storage for {@link Socket}.
 */
public interface SocketStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getSocketFilePath();

    /**
     * Returns {@code Socket} data as a {@link ReadOnlySocket}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlySocket> readSocket() throws DataConversionException, IOException;

    /**
     * @see #getSocketFilePath()
     */
    Optional<ReadOnlySocket> readSocket(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlySocket} to the storage.
     * @param socket cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveSocket(ReadOnlySocket socket) throws IOException;

    /**
     * @see #saveSocket(ReadOnlySocket)
     */
    void saveSocket(ReadOnlySocket socket, Path filePath) throws IOException;

}
