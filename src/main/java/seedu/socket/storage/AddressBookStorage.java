package seedu.socket.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.socket.commons.exceptions.DataConversionException;
import seedu.socket.model.ReadOnlySocket;
import seedu.socket.model.Socket;

/**
 * Represents a storage for {@link Socket}.
 */
public interface AddressBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns Socket data as a {@link ReadOnlySocket}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlySocket> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlySocket> readAddressBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlySocket} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlySocket addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlySocket)
     */
    void saveAddressBook(ReadOnlySocket addressBook, Path filePath) throws IOException;

}
