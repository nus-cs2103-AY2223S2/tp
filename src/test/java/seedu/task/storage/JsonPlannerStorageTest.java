package seedu.task.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.task.testutil.Assert.assertThrows;
import static seedu.task.testutil.TypicalDailyPlans.AUG22;
import static seedu.task.testutil.TypicalDailyPlans.JULY18;
import static seedu.task.testutil.TypicalDailyPlans.getTypicalPlanner;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.task.commons.exceptions.DataConversionException;
import seedu.task.model.Planner;
import seedu.task.model.ReadOnlyPlanner;

public class JsonPlannerStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonPlannerStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readPlanner_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readPlanner(null));
    }

    private Optional<ReadOnlyPlanner> readPlanner(String filePath) throws Exception {
        return new JsonPlannerStorage(Paths.get(filePath)).readPlanner(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readPlanner("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readPlanner("notJsonFormatPlanner.json"));
    }

    @Test
    public void readTaskBook_invalidDailyPlanPlanner_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readPlanner("invalidDailyPlanPlanner.json"));
    }

    @Test
    public void readTaskBook_invalidAndValidDailyPlanPlanner_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readPlanner("invalidAndValidPlanPlanner.json"));
    }

    @Test
    public void readAndSavePlanner_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempPlanner.json");
        Planner original = getTypicalPlanner();
        JsonPlannerStorage jsonPlannerStorage = new JsonPlannerStorage(filePath);

        // Save in new file and read back
        jsonPlannerStorage.savePlanner(original, filePath);
        ReadOnlyPlanner readBack = jsonPlannerStorage.readPlanner(filePath).get();
        assertEquals(original, new Planner(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addDailyPlan(JULY18);
        jsonPlannerStorage.savePlanner(original, filePath);
        readBack = jsonPlannerStorage.readPlanner(filePath).get();
        assertEquals(original, new Planner(readBack));

        // Save and read without specifying file path
        original.addDailyPlan(AUG22);
        jsonPlannerStorage.savePlanner(original); // file path not specified
        readBack = jsonPlannerStorage.readPlanner().get(); // file path not specified
        assertEquals(original, new Planner(readBack));
    }

    @Test
    public void savePlanner_nullPlanner_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePlanner(null, "SomeFile.json"));
    }

    private void savePlanner(ReadOnlyPlanner planner, String filePath) {
        try {
            new JsonPlannerStorage(Paths.get(filePath))
                    .savePlanner(planner, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void savePlanner_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> savePlanner(new Planner(), null));
    }
}
