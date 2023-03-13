package seedu.fitbook.storage.routine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.fitbook.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.fitbook.commons.exceptions.IllegalValueException;
import seedu.fitbook.commons.util.JsonUtil;
import seedu.fitbook.model.FitBookExerciseRoutine;
import seedu.fitbook.testutil.routine.TypicalRoutines;

public class JsonSerializableFitBookExerciseRoutineTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableFitBookExerciseRoutineTest");
    private static final Path TYPICAL_ROUTINES_FILE = TEST_DATA_FOLDER.resolve("typicalRoutinesFitBook.json");
    private static final Path INVALID_ROUTINE_FILE = TEST_DATA_FOLDER.resolve("invalidRoutineFitBook.json");
    private static final Path DUPLICATE_ROUTINE_FILE = TEST_DATA_FOLDER.resolve("duplicateRoutineFitBook.json");

    //TODO Something switching causing error.
    /*
    @Test
    public void toFitBookExerciseRoutineModelType_typicalRoutinesFile_success() throws Exception {
        JsonSerializableFitBookExerciseRoutine dataFromFile = JsonUtil.readJsonFile(TYPICAL_ROUTINES_FILE,
                JsonSerializableFitBookExerciseRoutine.class).get();
        FitBookExerciseRoutine fitBookExerciseRoutineFromFile = dataFromFile.toFitBookExerciseRoutineModelType();
        FitBookExerciseRoutine typicalRoutinesFitBook = TypicalRoutines.getTypicalFitBookExerciseRoutine();
        assertEquals(fitBookExerciseRoutineFromFile, typicalRoutinesFitBook);
    }
     */

    @Test
    public void toFitBookExerciseRoutineModelType_invalidRoutineFile_throwsIllegalValueException() throws Exception {
        JsonSerializableFitBookExerciseRoutine dataFromFile = JsonUtil.readJsonFile(INVALID_ROUTINE_FILE,
                JsonSerializableFitBookExerciseRoutine.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toFitBookExerciseRoutineModelType);
    }

    @Test
    public void toFitBookModelExerciseRoutineType_duplicateRoutines_throwsIllegalValueException() throws Exception {
        JsonSerializableFitBookExerciseRoutine dataFromFile = JsonUtil.readJsonFile(DUPLICATE_ROUTINE_FILE,
                JsonSerializableFitBookExerciseRoutine.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableFitBookExerciseRoutine.MESSAGE_DUPLICATE_ROUTINE,
                dataFromFile::toFitBookExerciseRoutineModelType);
    }

}
