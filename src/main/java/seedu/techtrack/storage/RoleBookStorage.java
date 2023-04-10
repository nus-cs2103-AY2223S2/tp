package seedu.techtrack.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.techtrack.commons.exceptions.DataConversionException;
import seedu.techtrack.model.ReadOnlyRoleBook;

/**
 * Represents a storage for {@link seedu.techtrack.model.RoleBook}.
 */
public interface RoleBookStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getRoleBookFilePath();

    /**
     * Returns RoleBook data as a {@link ReadOnlyRoleBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyRoleBook> readRoleBook() throws DataConversionException, IOException;

    /**
     * @see #getRoleBookFilePath()
     */
    Optional<ReadOnlyRoleBook> readRoleBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyRoleBook} to the storage.
     * @param roleBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveRoleBook(ReadOnlyRoleBook roleBook) throws IOException;

    /**
     * @see #saveRoleBook(ReadOnlyRoleBook)
     */
    void saveRoleBook(ReadOnlyRoleBook roleBook, Path filePath) throws IOException;

}
