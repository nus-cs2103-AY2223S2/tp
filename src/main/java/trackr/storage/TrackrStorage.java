package trackr.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import trackr.commons.exceptions.DataConversionException;
import trackr.model.ReadOnlySupplierList;
import trackr.model.ReadOnlyTaskList;

/**
 * Represents a storage for {@link trackr.model.SupplierList} and {@link trackr.model.TaskList}.
 */
public interface TrackrStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTrackrFilePath();

    /**
     * Returns SupplierList data as a {@link ReadOnlySupplierList}.
     * Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlySupplierList> readSupplierList() throws DataConversionException, IOException;

    /**
     * @see #getTrackrFilePath()
     */
    Optional<ReadOnlySupplierList> readSupplierList(Path filePath) throws DataConversionException, IOException;

    /**
     * Returns TaskList data as a {@link ReadOnlyTaskList}.
     * Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTaskList> readTaskList() throws DataConversionException, IOException;

    /**
     * @see #getTrackrFilePath()
     */
    Optional<ReadOnlyTaskList> readTaskList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlySupplierList} and {@link ReadOnlyTaskList} to the storage.
     * @param supplierList cannot be null.
     * @param taskList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTrackr(ReadOnlySupplierList supplierList, ReadOnlyTaskList taskList) throws IOException;

    /**
     * @see #saveTrackr(ReadOnlySupplierList, ReadOnlyTaskList)
     */
    void saveTrackr(ReadOnlySupplierList supplierList, ReadOnlyTaskList taskList, Path filePath) throws IOException;

}
