package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyEduMate;

/**
 * Represents a storage for {@link seedu.address.model.EduMate}.
 */
public interface EduMateStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getEduMateFilePath();

    /**
     * Returns EduMate data as a {@link ReadOnlyEduMate}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyEduMate> readEduMate() throws DataConversionException, IOException;

    /**
     * @see #getEduMateFilePath()
     */
    Optional<ReadOnlyEduMate> readEduMate(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyEduMate} to the storage.
     * @param eduMate cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveEduMate(ReadOnlyEduMate eduMate) throws IOException;

    /**
     * @see #saveEduMate(ReadOnlyEduMate)
     */
    void saveEduMate(ReadOnlyEduMate eduMate, Path filePath) throws IOException;

}
