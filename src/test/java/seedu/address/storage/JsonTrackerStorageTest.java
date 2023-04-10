package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyTracker;
import seedu.address.model.Tracker;
import seedu.address.model.module.Module;
import seedu.address.testutil.TypicalModules;

public class JsonTrackerStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonTrackerStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readTracker_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readTracker(null));
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readTracker("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readTracker("notJsonFormatTracker.json"));
    }

    @Test
    public void readTracker_invalidModuleTracker_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTracker("invalidModuleTracker.json"));
    }

    @Test
    public void readTracker_invalidAndValidModuleTracker_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readTracker("invalidAndValidModuleTracker.json"));
    }

    @Test
    public void readAndSaveTracker_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempTracker.json");
        Tracker original = TypicalModules.getTypicalTracker();
        JsonTrackerStorage jsonTrackerStorage = new JsonTrackerStorage(filePath);

        // Save in new file and read back
        jsonTrackerStorage.saveTracker(original, filePath);
        ReadOnlyTracker readBack = jsonTrackerStorage.readTracker(filePath).get();
        assertEquals(original, new Tracker(readBack));

        // Modify data, overwrite exiting file, and read back
        Module moduleToAdd = TypicalModules.getCs2107();
        Module moduleToRemove = TypicalModules.getSt2334();

        original.addModule(moduleToAdd);
        original.removeModule(moduleToRemove);
        jsonTrackerStorage.saveTracker(original, filePath);
        readBack = jsonTrackerStorage.readTracker(filePath).get();
        assertEquals(original, new Tracker(readBack));

        // Save and read without specifying file path
        original.addModule(moduleToRemove); // some modification to make sure that a write did occur
        jsonTrackerStorage.saveTracker(original); // file path not specified
        readBack = jsonTrackerStorage.readTracker().get(); // file path not specified
        assertEquals(original, new Tracker(readBack));
    }

    @Test
    public void saveTracker_nullTracker_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTracker(null, "SomeFile.json"));
    }

    @Test
    public void saveTracker_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveTracker(new Tracker(), null));
    }

    private java.util.Optional<ReadOnlyTracker> readTracker(String filePath) throws Exception {
        return new JsonTrackerStorage(Paths.get(filePath)).readTracker(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    /**
     * Saves {@code tracker} at the specified {@code filePath}.
     */
    private void saveTracker(ReadOnlyTracker tracker, String filePath) {
        try {
            new JsonTrackerStorage(Paths.get(filePath))
                    .saveTracker(tracker, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }
}
