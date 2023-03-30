package wingman.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import wingman.commons.exceptions.DataConversionException;
import wingman.model.ReadOnlyItemManager;
import wingman.model.item.Item;

/**
 * Represents the interface for a storage that stores Item items.
 *
 * @param <T> The type of the item to be stored.
 */
public interface ItemStorage<T extends Item> {
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
    Optional<? extends ReadOnlyItemManager<T>> read()
            throws DataConversionException, IOException;

    /**
     * @see #read()
     */
    Optional<? extends ReadOnlyItemManager<T>> read(Path filePath)
            throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyItemManager} to the storage.
     *
     * @param itemManager cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void save(ReadOnlyItemManager<T> itemManager)
            throws IOException;

    /**
     * @see #save(ReadOnlyItemManager)
     */
    void save(ReadOnlyItemManager<T> itemManager, Path filePath)
            throws IOException;
}
