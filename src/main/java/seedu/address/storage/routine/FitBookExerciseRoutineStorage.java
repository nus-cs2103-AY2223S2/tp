package seedu.address.storage.routine;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyFitBookExerciseRoutine;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Represents a storage for {@link seedu.address.model.FitBookExerciseRoutine}.
 */
public interface FitBookExerciseRoutineStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getFitBookExerciseRoutineFilePath();

    /**
     * Returns FitBook Exercise Routine data as a {@link seedu.address.model.ReadOnlyFitBookExerciseRoutine}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyFitBookExerciseRoutine> readFitBookExerciseRoutine() throws DataConversionException, IOException;

    /**
     * @see #getFitBookExerciseRoutineFilePath()
     */
    Optional<ReadOnlyFitBookExerciseRoutine> readFitBookExerciseRoutine(Path filePath)
            throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyFitBookExerciseRoutine} to the storage.
     * @param fitBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveFitBookExerciseRoutine(ReadOnlyFitBookExerciseRoutine fitBook) throws IOException;

    /**
     * @see #saveFitBookExerciseRoutine(ReadOnlyFitBookExerciseRoutine)
     */
    void saveFitBookExerciseRoutine(ReadOnlyFitBookExerciseRoutine addressBook, Path filePath) throws IOException;

}
