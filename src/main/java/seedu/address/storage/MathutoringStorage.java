package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.Mathutoring;
import seedu.address.model.ReadOnlyMathutoring;


/**
 * Represents a storage for {@link Mathutoring}.
 */
public interface MathutoringStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getMathutoringFilePath();

    /**
     * Returns Mathutoring data as a {@link ReadOnlyMathutoring}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyMathutoring> readMathutoring() throws DataConversionException, IOException;

    /**
     * @see #getMathutoringFilePath()
     */
    Optional<ReadOnlyMathutoring> readMathutoring(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyMathutoring} to the storage.
     * @param mathutoring cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveMathutoring(ReadOnlyMathutoring mathutoring) throws IOException;

    /**
     * @see #saveMathutoring(ReadOnlyMathutoring)
     */
    void saveMathutoring(ReadOnlyMathutoring mathutoring, Path filePath) throws IOException;

}
