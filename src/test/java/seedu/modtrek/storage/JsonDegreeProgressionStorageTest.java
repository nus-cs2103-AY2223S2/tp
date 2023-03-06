package seedu.modtrek.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.modtrek.testutil.Assert.assertThrows;
import static seedu.modtrek.testutil.TypicalModules.CS2030S;
import static seedu.modtrek.testutil.TypicalModules.CS2040S;
import static seedu.modtrek.testutil.TypicalModules.getTypicalDegreeProgression;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.modtrek.commons.exceptions.DataConversionException;
import seedu.modtrek.model.DegreeProgression;
import seedu.modtrek.model.ReadOnlyDegreeProgression;

public class JsonDegreeProgressionStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonDegreeProgressionStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readDegreeProgression_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readDegreeProgression(null));
    }

    private java.util.Optional<ReadOnlyDegreeProgression> readDegreeProgression(String filePath) throws Exception {
        return new JsonDegreeProgressionStorage(Paths.get(filePath))
                .readDegreeProgression(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readDegreeProgression("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readDegreeProgression("notJsonFormatDegreeProgression.json"));
    }

    @Test
    public void readDegreeProgression_invalidModuleDegreeProgression_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readDegreeProgression("invalidModuleDegreeProgression.json"));
    }

    @Test
    public void readDegreeProgression_invalidAndValidModuleDegreeProgression_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readDegreeProgression(
                "invalidAndValidModuleDegreeProgression.json"));
    }

    @Test
    public void readAndSaveDegreeProgression_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempDegreeProgression.json");
        DegreeProgression original = getTypicalDegreeProgression();
        JsonDegreeProgressionStorage jsonDegreeProgressionStorage = new JsonDegreeProgressionStorage(filePath);

        // Save in new file and read back
        jsonDegreeProgressionStorage.saveDegreeProgression(original, filePath);
        ReadOnlyDegreeProgression readBack = jsonDegreeProgressionStorage.readDegreeProgression(filePath).get();
        assertEquals(original, new DegreeProgression(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addModule(CS2030S);
        original.removeModule(CS2030S);
        jsonDegreeProgressionStorage.saveDegreeProgression(original, filePath);
        readBack = jsonDegreeProgressionStorage.readDegreeProgression(filePath).get();
        assertEquals(original, new DegreeProgression(readBack));

        // Save and read without specifying file path
        original.addModule(CS2040S);
        jsonDegreeProgressionStorage.saveDegreeProgression(original); // file path not specified
        readBack = jsonDegreeProgressionStorage.readDegreeProgression().get(); // file path not specified
        assertEquals(original, new DegreeProgression(readBack));

    }

    @Test
    public void saveDegreeProgression_nullDegreeProgression_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveDegreeProgression(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveDegreeProgression(ReadOnlyDegreeProgression addressBook, String filePath) {
        try {
            new JsonDegreeProgressionStorage(Paths.get(filePath))
                    .saveDegreeProgression(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveDegreeProgression_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveDegreeProgression(new DegreeProgression(), null));
    }
}
