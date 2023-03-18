package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.MasterDeck;
import seedu.address.model.ReadOnlyMasterDeck;

/**
 * Represents a storage for {@link MasterDeck}.
 */
public interface MasterDeckStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getMasterDeckFilePath();

    /**
     * Returns Deck data as a {@link ReadOnlyMasterDeck}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyMasterDeck> readMasterDeck() throws DataConversionException, IOException;

    /**
     * @see #getMasterDeckFilePath()
     */
    Optional<ReadOnlyMasterDeck> readMasterDeck(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyMasterDeck} to the storage.
     * @param masterDeck cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveMasterDeck(ReadOnlyMasterDeck masterDeck) throws IOException;

    /**
     * @see #saveMasterDeck(ReadOnlyMasterDeck)
     */
    void saveMasterDeck(ReadOnlyMasterDeck masterDeck, Path filePath) throws IOException;

}
