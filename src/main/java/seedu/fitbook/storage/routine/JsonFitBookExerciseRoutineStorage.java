package seedu.fitbook.storage.routine;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.fitbook.commons.core.LogsCenter;
import seedu.fitbook.commons.exceptions.DataConversionException;
import seedu.fitbook.commons.exceptions.IllegalValueException;
import seedu.fitbook.commons.util.FileUtil;
import seedu.fitbook.commons.util.JsonUtil;
import seedu.fitbook.model.ReadOnlyFitBookExerciseRoutine;

/**
 * A class to access FitBook Exercise Routine data stored as a json file on the hard disk.
 */
public class JsonFitBookExerciseRoutineStorage implements FitBookExerciseRoutineStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonFitBookExerciseRoutineStorage.class);

    private Path filePath;

    public JsonFitBookExerciseRoutineStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getFitBookExerciseRoutineFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyFitBookExerciseRoutine> readFitBookExerciseRoutine() throws DataConversionException {
        return readFitBookExerciseRoutine(filePath);
    }

    /**
     * Similar to {@link #readFitBookExerciseRoutine()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyFitBookExerciseRoutine> readFitBookExerciseRoutine(Path filePath)
            throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableFitBookExerciseRoutine> jsonFitBookExerciseRoutine = JsonUtil.readJsonFile(
                filePath, JsonSerializableFitBookExerciseRoutine.class);
        if (!jsonFitBookExerciseRoutine.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonFitBookExerciseRoutine.get().toFitBookExerciseRoutineModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveFitBookExerciseRoutine(ReadOnlyFitBookExerciseRoutine fitBookExerciseRoutine) throws IOException {
        saveFitBookExerciseRoutine(fitBookExerciseRoutine, filePath);
    }

    /**
     * Similar to {@link #saveFitBookExerciseRoutine(ReadOnlyFitBookExerciseRoutine)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveFitBookExerciseRoutine(ReadOnlyFitBookExerciseRoutine fitBookExerciseRoutine,
            Path filePath) throws IOException {
        requireNonNull(fitBookExerciseRoutine);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableFitBookExerciseRoutine(fitBookExerciseRoutine), filePath);
    }

}
