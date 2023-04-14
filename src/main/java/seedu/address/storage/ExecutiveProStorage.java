package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ExecutiveProDb;
import seedu.address.model.ReadOnlyExecutiveProDb;

/**
 * Represents a storage for {@link ExecutiveProDb}.
 */
public interface ExecutiveProStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getExecutiveProDbFilePath();

    /**
     * Returns ExecutiveProDb data as a {@link ReadOnlyExecutiveProDb}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyExecutiveProDb> readExecutiveProDb() throws DataConversionException, IOException;

    /**
     * @see #getExecutiveProDbFilePath()
     */
    Optional<ReadOnlyExecutiveProDb> readExecutiveProDb(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyExecutiveProDb} to the storage.
     * @param executiveProDb cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveExecutiveProDb(ReadOnlyExecutiveProDb executiveProDb) throws IOException;

    /**
     * @see #saveExecutiveProDb(ReadOnlyExecutiveProDb)
     */
    void saveExecutiveProDb(ReadOnlyExecutiveProDb executiveProDb, Path filePath) throws IOException;

}
