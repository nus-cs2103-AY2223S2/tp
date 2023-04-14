package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.CS1231S_LEC;
import static seedu.address.testutil.TypicalModules.CS2030S_LAB;
import static seedu.address.testutil.TypicalModules.CS2106_TUT;
import static seedu.address.testutil.TypicalModules.getTypicalModuleTracker;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ModuleTracker;
import seedu.address.model.ReadOnlyModuleTracker;

public class JsonModuleTrackerStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonModuleTrackerStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readModuleTracker_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readModuleTracker(null));
    }

    private java.util.Optional<ReadOnlyModuleTracker> readModuleTracker(String filePath) throws Exception {
        return new JsonModuleTrackerStorage(Paths.get(filePath))
                .readModuleTracker(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readModuleTracker("NonExistentFile.json").isPresent());
    }

    /*
    // To fix later if possible
    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () ->
                readModuleTracker("notJsonFormatModuleTracker.json"));
    }

     */

    @Test
    public void readModuleTracker_invalidModuleModuleTracker_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readModuleTracker("invalidModuleModuleTracker.json"));
    }

    @Test
    public void readModuleTracker_invalidAndValidModuleModuleTracker_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readModuleTracker("invalidAndValidModuleModuleTracker.json"));
    }

    @Test
    public void readAndSaveModuleTracker_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempModuleTracker.json");
        ModuleTracker original = getTypicalModuleTracker();
        JsonModuleTrackerStorage jsonModuleTrackerStorage = new JsonModuleTrackerStorage(filePath);

        // Save in new file and read back
        jsonModuleTrackerStorage.saveModuleTracker(original, filePath);
        ReadOnlyModuleTracker readBack = jsonModuleTrackerStorage.readModuleTracker(filePath).get();
        assertEquals(original, new ModuleTracker(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addModule(CS1231S_LEC);
        original.removeModule(CS2106_TUT);
        jsonModuleTrackerStorage.saveModuleTracker(original, filePath);
        readBack = jsonModuleTrackerStorage.readModuleTracker(filePath).get();
        assertEquals(original, new ModuleTracker(readBack));

        // Save and read without specifying file path
        original.addModule(CS2030S_LAB);
        jsonModuleTrackerStorage.saveModuleTracker(original); // file path not specified
        readBack = jsonModuleTrackerStorage.readModuleTracker().get(); // file path not specified
        assertEquals(original, new ModuleTracker(readBack));

    }

    @Test
    public void saveModuleTracker_nullModuleTracker_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveModuleTracker(null, "SomeFile.json"));
    }

    /**
     * Saves {@code moduleTracker} at the specified {@code filePath}.
     */
    private void saveModuleTracker(ReadOnlyModuleTracker moduleTracker, String filePath) {
        try {
            new JsonModuleTrackerStorage(Paths.get(filePath))
                    .saveModuleTracker(moduleTracker, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveModuleTracker_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveModuleTracker(new ModuleTracker(), null));
    }
}
