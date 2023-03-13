package seedu.fitbook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.fitbook.commons.core.LogsCenter;
import seedu.fitbook.commons.exceptions.DataConversionException;
import seedu.fitbook.model.ReadOnlyFitBook;
import seedu.fitbook.model.ReadOnlyFitBookExerciseRoutine;
import seedu.fitbook.model.ReadOnlyUserPrefs;
import seedu.fitbook.model.UserPrefs;
import seedu.fitbook.storage.routine.FitBookExerciseRoutineStorage;

/**
 * Manages storage of FitBook and Exercise Routine data in local storage.
 */

public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private FitBookStorage fitBookStorage;
    private UserPrefsStorage userPrefsStorage;

    private FitBookExerciseRoutineStorage fitBookExerciseRoutineStorage;



    /**
     * Creates a {@code StorageManager} with the given {@code FitBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(FitBookStorage fitBookStorage, UserPrefsStorage userPrefsStorage,
            FitBookExerciseRoutineStorage fitBookExerciseRoutineStorage) {
        this.fitBookStorage = fitBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.fitBookExerciseRoutineStorage = fitBookExerciseRoutineStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ FitBook methods ==============================

    @Override
    public Path getFitBookFilePath() {
        return fitBookStorage.getFitBookFilePath();
    }

    @Override
    public Optional<ReadOnlyFitBook> readFitBook() throws DataConversionException, IOException {
        return readFitBook(fitBookStorage.getFitBookFilePath());
    }

    @Override
    public Optional<ReadOnlyFitBook> readFitBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return fitBookStorage.readFitBook(filePath);
    }

    @Override
    public void saveFitBook(ReadOnlyFitBook fitBook) throws IOException {
        saveFitBook(fitBook, fitBookStorage.getFitBookFilePath());
    }

    @Override
    public void saveFitBook(ReadOnlyFitBook fitBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        fitBookStorage.saveFitBook(fitBook, filePath);
    }


    // ================ FitBook Exercise Routine methods ==============================

    @Override
    public Path getFitBookExerciseRoutineFilePath() {
        return fitBookExerciseRoutineStorage.getFitBookExerciseRoutineFilePath();
    }

    @Override
    public Optional<ReadOnlyFitBookExerciseRoutine> readFitBookExerciseRoutine()
            throws DataConversionException, IOException {
        return readFitBookExerciseRoutine(fitBookExerciseRoutineStorage.getFitBookExerciseRoutineFilePath());
    }

    @Override
    public Optional<ReadOnlyFitBookExerciseRoutine> readFitBookExerciseRoutine(Path filePath)
            throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return fitBookExerciseRoutineStorage.readFitBookExerciseRoutine(filePath);
    }

    @Override
    public void saveFitBookExerciseRoutine(ReadOnlyFitBookExerciseRoutine fitBookExerciseRoutine) throws IOException {
        saveFitBookExerciseRoutine(fitBookExerciseRoutine,
                fitBookExerciseRoutineStorage.getFitBookExerciseRoutineFilePath());
    }

    @Override
    public void saveFitBookExerciseRoutine(ReadOnlyFitBookExerciseRoutine fitBookExerciseRoutine,
            Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        fitBookExerciseRoutineStorage.saveFitBookExerciseRoutine(fitBookExerciseRoutine, filePath);
    }
}
