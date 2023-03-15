package seedu.fitbook.storage.routine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.fitbook.testutil.Assert.assertThrows;
import static seedu.fitbook.testutil.routine.TypicalRoutines.ARMS;
import static seedu.fitbook.testutil.routine.TypicalRoutines.HIIT;
import static seedu.fitbook.testutil.routine.TypicalRoutines.JUMP;
import static seedu.fitbook.testutil.routine.TypicalRoutines.getTypicalFitBookExerciseRoutine;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.fitbook.commons.exceptions.DataConversionException;
import seedu.fitbook.model.FitBookExerciseRoutine;
import seedu.fitbook.model.ReadOnlyFitBookExerciseRoutine;

public class JsonFitBookExerciseRoutineStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonFitBookExerciseRoutineStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readFitBookExerciseRoutine_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readFitBookExerciseRoutine(null));
    }

    private Optional<ReadOnlyFitBookExerciseRoutine> readFitBookExerciseRoutine(String filePath) throws Exception {
        return new JsonFitBookExerciseRoutineStorage(Paths.get(filePath))
                .readFitBookExerciseRoutine(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readFitBookExerciseRoutine("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () ->
                readFitBookExerciseRoutine("notJsonFormatFitBookExerciseRoutine.json"));
    }

    @Test
    public void readFitBookExerciseRoutine_invalidExerciseRoutineFitBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readFitBookExerciseRoutine("invalidRoutineFitBookExerciseRoutine.json"));
    }

    @Test
    public void readFitBookExerciseRoutine_invalidAndValidExerciseRoutineFitBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readFitBookExerciseRoutine("invalidAndValidRoutineFitBookExerciseRoutine.json"));
    }

    @Test
    public void readAndSaveFitBookExerciseRoutine_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempFitBook.json");
        FitBookExerciseRoutine original = getTypicalFitBookExerciseRoutine();
        JsonFitBookExerciseRoutineStorage jsonFitBookExerciseRoutineStorage =
                new JsonFitBookExerciseRoutineStorage(filePath);

        // Save in new file and read back
        jsonFitBookExerciseRoutineStorage.saveFitBookExerciseRoutine(original, filePath);
        ReadOnlyFitBookExerciseRoutine readBack =
                jsonFitBookExerciseRoutineStorage.readFitBookExerciseRoutine(filePath).get();
        assertEquals(original, new FitBookExerciseRoutine(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addRoutine(HIIT);
        original.removeRoutine(JUMP);
        jsonFitBookExerciseRoutineStorage.saveFitBookExerciseRoutine(original, filePath);
        readBack = jsonFitBookExerciseRoutineStorage.readFitBookExerciseRoutine(filePath).get();
        assertEquals(original, new FitBookExerciseRoutine(readBack));

        // Save and read without specifying file path
        original.addRoutine(ARMS);
        jsonFitBookExerciseRoutineStorage.saveFitBookExerciseRoutine(original); // file path not specified
        readBack = jsonFitBookExerciseRoutineStorage.readFitBookExerciseRoutine().get(); // file path not specified
        assertEquals(original, new FitBookExerciseRoutine(readBack));

    }

    @Test
    public void saveFitBookExerciseRoutine_nullFitBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFitBookExerciseRoutine(null, "SomeFile.json"));
    }

    /**
     * Saves {@code fitBookExerciseRoutine} at the specified {@code filePath}.
     */
    private void saveFitBookExerciseRoutine(ReadOnlyFitBookExerciseRoutine fitBookExerciseRoutine, String filePath) {
        try {
            new JsonFitBookExerciseRoutineStorage(Paths.get(filePath))
                    .saveFitBookExerciseRoutine(fitBookExerciseRoutine, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveFitBookExerciseRoutine_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveFitBookExerciseRoutine(new FitBookExerciseRoutine(),
                null));
    }
}
