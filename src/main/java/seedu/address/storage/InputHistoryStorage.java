package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.model.history.InputHistory;

/**
 * Represents a storage for {@link seedu.address.model.history.InputHistory}.
 */
public interface InputHistoryStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getHistoryStoragePath();

    /**
     * Returns history string.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<String> readHistoryString() throws IOException;

    /**
     * @see #getHistoryStoragePath()
     */
    Optional<String> readHistoryString(Path filePath) throws IOException;

    /**
     * Replace the history txt file's content with the given string to the storage.
     *
     * @param historyString cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveHistoryString(String historyString) throws IOException;

    /**
     * @see #saveHistory(InputHistory)
     */
    void saveHistoryString(String historyString, Path filePath) throws IOException;

}
