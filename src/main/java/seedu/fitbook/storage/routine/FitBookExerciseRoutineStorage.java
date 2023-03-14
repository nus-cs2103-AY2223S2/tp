package seedu.fitbook.storage.routine;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.fitbook.commons.exceptions.DataConversionException;
import seedu.fitbook.model.ReadOnlyFitBookExerciseRoutine;

/**
 * Represents a storage for {@link seedu.fitbook.model.FitBookExerciseRoutine}.
 */
public interface FitBookExerciseRoutineStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getFitBookExerciseRoutineFilePath();

    /**
     * Returns FitBook Exercise Routine data as a {@link seedu.fitbook.model.ReadOnlyFitBookExerciseRoutine}.
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
