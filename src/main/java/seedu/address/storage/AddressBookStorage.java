package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTuteeManagingSystem;
import seedu.address.model.TuteeManagingSystem;

/**
 * Represents a storage for {@link TuteeManagingSystem}.
 */
public interface AddressBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns TuteeManagingSystem data as a {@link ReadOnlyTuteeManagingSystem}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTuteeManagingSystem> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyTuteeManagingSystem> readAddressBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTuteeManagingSystem} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyTuteeManagingSystem addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyTuteeManagingSystem)
     */
    void saveAddressBook(ReadOnlyTuteeManagingSystem addressBook, Path filePath) throws IOException;

}
