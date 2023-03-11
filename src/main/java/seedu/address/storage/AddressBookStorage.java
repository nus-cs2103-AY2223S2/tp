package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.MasterMasterDeck;
import seedu.address.model.ReadOnlyMasterDeck;

/**
 * Represents a storage for {@link MasterMasterDeck}.
 */
public interface AddressBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns Deck data as a {@link ReadOnlyMasterDeck}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyMasterDeck> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyMasterDeck> readAddressBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyMasterDeck} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyMasterDeck addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyMasterDeck)
     */
    void saveAddressBook(ReadOnlyMasterDeck addressBook, Path filePath) throws IOException;

}
