package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.FriendlyLink;
import seedu.address.model.ReadOnlyFriendlyLink;

/**
 * Represents a storage for {@link FriendlyLink}.
 * <p>
 * Data will be saved in the following format.
 * <code>
 *     {
 *         elderly: [],
 *         volunteers: [],
 *         pairs: []
 *     }
 * </code>
 */
public interface FriendlyLinkStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getFriendlyLinkFilePath();

    /**
     * Returns FriendlyLink data as a {@link ReadOnlyFriendlyLink}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyFriendlyLink> readFriendlyLink() throws DataConversionException, IOException;

    /**
     * @see #getFriendlyLinkFilePath()
     */
    Optional<ReadOnlyFriendlyLink> readFriendlyLink(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyFriendlyLink} to the storage.
     * @param friendlyLink cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveFriendlyLink(ReadOnlyFriendlyLink friendlyLink) throws IOException;

    /**
     * @see #saveFriendlyLink(ReadOnlyFriendlyLink)
     */
    void saveFriendlyLink(ReadOnlyFriendlyLink friendlyLink, Path filePath) throws IOException;
}
