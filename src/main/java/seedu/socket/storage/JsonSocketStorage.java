package seedu.socket.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.socket.commons.core.LogsCenter;
import seedu.socket.commons.exceptions.DataConversionException;
import seedu.socket.commons.exceptions.IllegalValueException;
import seedu.socket.commons.util.FileUtil;
import seedu.socket.commons.util.JsonUtil;
import seedu.socket.model.ReadOnlySocket;

/**
 * A class to access SOCket data stored as a json file on the hard disk.
 */
public class JsonSocketStorage implements SocketStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonSocketStorage.class);

    private Path filePath;

    public JsonSocketStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getSocketFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlySocket> readSocket() throws DataConversionException {
        return readSocket(filePath);
    }

    /**
     * Similar to {@link #readSocket()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlySocket> readSocket(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableSocket> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableSocket.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveSocket(ReadOnlySocket socket) throws IOException {
        saveSocket(socket, filePath);
    }

    /**
     * Similar to {@link #saveSocket(ReadOnlySocket)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveSocket(ReadOnlySocket socket, Path filePath) throws IOException {
        requireNonNull(socket);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableSocket(socket), filePath);
    }

}
