package seedu.address.storage.pair;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.FriendlyLink;
import seedu.address.model.ReadOnlyPair;

/**
 * Storage class for handling serializing and unserializing of the pair entity.
 */
public interface PairStorage {
    /**
     * Returns the file path of the pairs data file.
     *
     * @return Pair data file path.
     */
    Path getPairFilePath();

    /**
     * Returns FriendlyLink data as a {@link ReadOnlyPair}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @param friendlyLink FriendlyLink cache.
     * @return {@code Optional} of FriendlyLink cache.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyPair> readPair(
            FriendlyLink friendlyLink) throws DataConversionException, IOException;

    /**
     * @see #getPairFilePath()
     */
    Optional<ReadOnlyPair> readPair(
            Path filePath, FriendlyLink friendlyLink) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyPair} to the storage.
     * @param pair Pair to save. Cannot be null.
     * @throws IOException If there was any problem writing to the file.
     */
    void savePair(ReadOnlyPair pair) throws IOException;

    /**
     * @see #savePair(ReadOnlyPair)
     */
    void savePair(ReadOnlyPair pair, Path filePath) throws IOException;
}
