package teambuilder.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import teambuilder.commons.exceptions.DataConversionException;
import teambuilder.model.ReadOnlyTeamBuilder;

/**
 * Represents a storage for {@link teambuilder.model.TeamBuilder}.
 */
public interface TeamBuilderStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyTeamBuilder}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyTeamBuilder> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<ReadOnlyTeamBuilder> readAddressBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyTeamBuilder} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyTeamBuilder addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyTeamBuilder)
     */
    void saveAddressBook(ReadOnlyTeamBuilder addressBook, Path filePath) throws IOException;

}
