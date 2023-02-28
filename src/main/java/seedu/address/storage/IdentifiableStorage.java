package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyIdentifiableManager;
import seedu.address.model.item.Identifiable;

/**
 * Represents the interface for a storage that stores identifiable items.
 *
 * @param <T> The type of the identifiable item to be stored.
 */
public interface IdentifiableStorage<T extends Identifiable> {
    /**
     * Returns the file path of the data file.
     *
     * @return the file path of the data file.
     */
    Path getPath();

    /**
     * Reads the data from the file.
     *
     * @return the data from the file.
     * @throws DataConversionException if the data in storage is not in the
     *                                 expected format.
     * @throws IOException             if there was any problem when reading
     *                                 from the storage.
     */
    Optional<? extends ReadOnlyIdentifiableManager<T>> read()
            throws DataConversionException, IOException;

    /**
     * @see #read()
     */
    Optional<? extends ReadOnlyIdentifiableManager<T>> read(Path filePath)
            throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyIdentifiableManager} to the storage.
     *
     * @param identifiableManager cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void save(ReadOnlyIdentifiableManager<T> identifiableManager)
            throws IOException;

    /**
     * @see #save(ReadOnlyIdentifiableManager)
     */
    void save(ReadOnlyIdentifiableManager<T> identifiableManager, Path filePath)
            throws IOException;
}
