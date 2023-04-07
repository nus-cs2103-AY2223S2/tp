package seedu.internship.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.internship.commons.exceptions.DataConversionException;
import seedu.internship.model.ReadOnlyEventCatalogue;

/**
 * Represents a storage for {@link seedu.internship.model.EventCatalogue}.
 */
public interface EventCatalogueStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getEventCatalogueFilePath();

    /**
     * Returns EventCatalogue data as a {@link ReadOnlyEventCatalogue}.
     * Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyEventCatalogue> readEventCatalogue() throws DataConversionException, IOException;

    /**
     * @see #getEventCatalogueFilePath()
     */
    Optional<ReadOnlyEventCatalogue> readEventCatalogue(Path filePath) throws DataConversionException,
            IOException;

    /**
     * Saves the given {@link ReadOnlyEventCatalogue} to the storage.
     * @param eventCatalogue cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveEventCatalogue(ReadOnlyEventCatalogue eventCatalogue) throws IOException;

    /**
     * @see #saveEventCatalogue(ReadOnlyEventCatalogue)
     */
    void saveEventCatalogue(ReadOnlyEventCatalogue eventCatalogue, Path filePath) throws IOException;

}
