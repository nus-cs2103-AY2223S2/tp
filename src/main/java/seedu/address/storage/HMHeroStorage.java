package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.HMHero;
import seedu.address.model.ReadOnlyHMHero;

/**
 * Represents a storage for {@link HMHero}.
 */
public interface HMHeroStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns HMHero data as a {@link ReadOnlyHMHero}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyHMHero> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyHMHero> readAddressBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyHMHero} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyHMHero addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyHMHero)
     */
    void saveAddressBook(ReadOnlyHMHero addressBook, Path filePath) throws IOException;

}
