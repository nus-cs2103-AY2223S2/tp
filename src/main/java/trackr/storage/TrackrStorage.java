package trackr.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import trackr.commons.exceptions.DataConversionException;
import trackr.model.ReadOnlyAddressBook;
import trackr.model.ReadOnlyOrderList;
import trackr.model.ReadOnlyTaskList;

/**
 * Represents a storage for {@link trackr.model.AddressBook} and {@link trackr.model.TaskList}.
 */
public interface TrackrStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getTrackrFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyAddressBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getTrackrFilePath()
     */
    Optional<ReadOnlyAddressBook> readAddressBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyAddressBook} and {@link ReadOnlyTaskList} to the storage.
     * @param addressBook cannot be null.
     * @param taskList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveTrackr(ReadOnlyAddressBook addressBook, ReadOnlyTaskList taskList,
            ReadOnlyOrderList orderList) throws IOException;

    /**
     * @see #saveTrackr(ReadOnlyAddressBook, ReadOnlyTaskList)
     */
    void saveTrackr(ReadOnlyAddressBook addressBook, ReadOnlyTaskList taskList,
            ReadOnlyOrderList orderList, Path filePath) throws IOException;

    /**
     * @see #getTrackrFilePath()
     */
    Optional<ReadOnlyTaskList> readTaskList(Path filePath) throws DataConversionException, IOException;

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
    Optional<ReadOnlyOrderList> readOrderList(Path filePath) throws DataConversionException, IOException;

    /**
     * Returns OrderList data as a {@link ReadOnlyOrderList}.
     * Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyOrderList> readOrderList() throws DataConversionException, IOException;

}
