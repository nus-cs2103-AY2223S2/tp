package seedu.modtrek.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.modtrek.commons.exceptions.DataConversionException;
import seedu.modtrek.model.ReadOnlyDegreeProgression;

/**
 * Represents a storage for {@link seedu.address.model.DegreeProgression}.
 */
public interface DegreeProgressionStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getDegreeProgressionFilePath();

    /**
     * Returns DegreeProgression data as a {@link ReadOnlyDegreeProgression}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyDegreeProgression> readDegreeProgression() throws DataConversionException, IOException;

    /**
     * @see #getDegreeProgressionFilePath()
     */
    Optional<ReadOnlyDegreeProgression> readDegreeProgression(Path filePath) throws DataConversionException,
            IOException;

    /**
     * Saves the given {@link ReadOnlyDegreeProgression} to the storage.
     * @param degreeProgression cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveDegreeProgression(ReadOnlyDegreeProgression degreeProgression) throws IOException;

    /**
     * @see #saveDegreeProgression(ReadOnlyDegreeProgression)
     */
    void saveDegreeProgression(ReadOnlyDegreeProgression degreeProgression, Path filePath) throws IOException;

}
