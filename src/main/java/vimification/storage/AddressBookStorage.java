package vimification.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import vimification.commons.exceptions.DataConversionException;
import vimification.model.ReadOnlyTaskPlanner;

/**
 * Represents a storage for {@link vimification.model.AddressBook}.
 */
public interface AddressBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyTaskPlanner}. Returns {@code Optional.empty()}
     * if storage file is not found.
     * 
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTaskPlanner> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyTaskPlanner> readAddressBook(Path filePath)
            throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTaskPlanner} to the storage.
     * 
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyTaskPlanner addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyTaskPlanner)
     */
    void saveAddressBook(ReadOnlyTaskPlanner addressBook, Path filePath) throws IOException;

}
