package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyMathutoring;

/**
 * Represents a storage for {@link seedu.address.model.Mathutoring}.
 */
public interface MathutoringStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getAddressBookFilePath();

    /**
     * Returns Mathutoring data as a {@link ReadOnlyMathutoring}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<seedu.address.model.ReadOnlyMathutoring> readAddressBook() throws DataConversionException, IOException;

    /**
     * @see #getAddressBookFilePath()
     */
    Optional<seedu.address.model.ReadOnlyMathutoring> readAddressBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyMathutoring} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(seedu.address.model.ReadOnlyMathutoring addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyMathutoring)
     */
    void saveAddressBook(seedu.address.model.ReadOnlyMathutoring addressBook, Path filePath) throws IOException;

}
