package seedu.connectus.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.connectus.commons.exceptions.DataConversionException;
import seedu.connectus.model.ConnectUs;
import seedu.connectus.model.ReadOnlyConnectUs;

/**
 * Represents a storage for {@link ConnectUs}.
 */
public interface ConnectUsStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getConnectUsFilePath();

    /**
     * Returns ConnectUS data as a {@link ReadOnlyConnectUs}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyConnectUs> readConnectUs() throws DataConversionException, IOException;

    /**
     * @see #getConnectUsFilePath()
     */
    Optional<ReadOnlyConnectUs> readConnectUs(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyConnectUs} to the storage.
     * @param connectUs cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveConnectUs(ReadOnlyConnectUs connectUs) throws IOException;

    /**
     * @see #saveConnectUs(ReadOnlyConnectUs)
     */
    void saveConnectUs(ReadOnlyConnectUs connectUs, Path filePath) throws IOException;

}
