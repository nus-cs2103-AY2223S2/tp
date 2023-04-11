package seedu.address.storage.parents;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.person.parent.ReadOnlyParents;

/**
 * A interface of ParentsStorage
 */
public interface ParentsStorage {
    /**
     * Returns the file path of the data file.
     */
    Path getParentsFilePath();

    /**
     * Returns Parent data as a {@link ReadOnlyParents}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyParents> readParents() throws DataConversionException, IOException;

    /**
     * @see #getParentsFilePath()
     */
    Optional<ReadOnlyParents> readParents(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyParents} to the storage.
     * @param parents cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveParents(ReadOnlyParents parents) throws IOException;

    /**
     * @see #saveParents(ReadOnlyParents)
     */
    void saveParents(ReadOnlyParents parents, Path filePath) throws IOException;

}
