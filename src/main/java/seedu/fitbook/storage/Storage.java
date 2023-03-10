package seedu.fitbook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.fitbook.commons.exceptions.DataConversionException;
import seedu.fitbook.model.ReadOnlyFitBook;
import seedu.fitbook.model.ReadOnlyFitBookExerciseRoutine;
import seedu.fitbook.model.ReadOnlyUserPrefs;
import seedu.fitbook.model.UserPrefs;
import seedu.fitbook.storage.routine.FitBookExerciseRoutineStorage;

/**
 * API of the Storage component
 */
public interface Storage extends FitBookStorage, UserPrefsStorage, FitBookExerciseRoutineStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getFitBookFilePath();

    @Override
    Optional<ReadOnlyFitBook> readFitBook() throws DataConversionException, IOException;

    @Override
    void saveFitBook(ReadOnlyFitBook fitBook) throws IOException;

    @Override
    Path getFitBookExerciseRoutineFilePath();

    @Override
    Optional<ReadOnlyFitBookExerciseRoutine> readFitBookExerciseRoutine() throws DataConversionException, IOException;

    @Override
    Optional<ReadOnlyFitBookExerciseRoutine> readFitBookExerciseRoutine(Path filePath)
            throws DataConversionException, IOException;

    @Override
    void saveFitBookExerciseRoutine(ReadOnlyFitBookExerciseRoutine fitBookExerciseRoutine) throws IOException;

    @Override
    void saveFitBookExerciseRoutine(ReadOnlyFitBookExerciseRoutine fitBookExerciseRoutine,
            Path filePath) throws IOException;
}
