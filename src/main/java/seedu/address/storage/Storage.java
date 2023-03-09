package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyFitBook;
import seedu.address.model.ReadOnlyFitBookExerciseRoutine;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.storage.routine.FitBookExerciseRoutineStorage;

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
