package seedu.address.storage.elderly;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.FriendlyLink;
import seedu.address.model.ReadOnlyElderly;

/**
 * Storage class for handling serializing and unserializing of the elderly entity.
 */
public interface ElderlyStorage {
    /**
     * Returns the file path of the elderly data file.
     *
     * @return Elderly data file path.
     */
    Path getElderlyFilePath();

    /**
     * Returns FriendlyLink data as a {@link ReadOnlyElderly}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @param friendlyLink FriendlyLink cache.
     * @return {@code Optional} of FriendlyLink cache.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyElderly> readElderly(FriendlyLink friendlyLink) throws DataConversionException, IOException;

    /**
     * @see #getElderlyFilePath()
     */
    Optional<ReadOnlyElderly> readElderly(Path filePath, FriendlyLink friendlyLink)
            throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyElderly} to the storage.
     *
     * @param elderly Cannot be null.
     * @throws IOException If there was any problem writing to the file.
     */
    void saveElderly(ReadOnlyElderly elderly) throws IOException;

    /**
     * @see #saveElderly(ReadOnlyElderly)
     */
    void saveElderly(ReadOnlyElderly elderly, Path filePath) throws IOException;
}
