package seedu.dengue.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.dengue.testutil.Assert.assertThrows;
import static seedu.dengue.testutil.TypicalPersons.ALICE;
import static seedu.dengue.testutil.TypicalPersons.HOON;
import static seedu.dengue.testutil.TypicalPersons.IDA;
import static seedu.dengue.testutil.TypicalPersons.getTypicalDengueHotspotTracker;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.dengue.commons.exceptions.DataConversionException;
import seedu.dengue.model.DengueHotspotTracker;
import seedu.dengue.model.ReadOnlyDengueHotspotTracker;

public class JsonDengueHotspotTrackerStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test",
            "data", "JsonDengueHotspotTrackerStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readDengueHotspotTracker_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readDengueHotspotTracker(null));
    }

    private java.util.Optional<ReadOnlyDengueHotspotTracker> readDengueHotspotTracker(String filePath)
            throws Exception {
        return new CsvDengueHotspotStorage(Paths.get(filePath))
                .readDengueHotspotTracker(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readDengueHotspotTracker("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, ()
                -> readDengueHotspotTracker("notJsonFormatDengueHotspotTracker.json"));
    }

    @Test
    public void readDengueHotspotTracker_invalidPersonDengueHotspotTracker_throwDataConversionException() {
        assertThrows(DataConversionException.class, ()
                -> readDengueHotspotTracker("invalidPersonDengueHotspotTracker.json"));
    }

    @Test
    public void readDengueHotspotTracker_invalidAndValidPersonDengueHotspotTracker_throwDataConversionException() {
        assertThrows(DataConversionException.class, ()
                -> readDengueHotspotTracker("invalidAndValidPersonDengueHotspotTracker.json"));
    }

    @Test
    public void readAndSaveDengueHotspotTracker_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempDengueHotspotTracker.json");
        DengueHotspotTracker original = getTypicalDengueHotspotTracker();
        CsvDengueHotspotStorage jsonDengueHotspotStorage = new CsvDengueHotspotStorage(filePath);

        // Save in new file and read back
        jsonDengueHotspotStorage.saveDengueHotspotTracker(original, filePath);
        ReadOnlyDengueHotspotTracker readBack = jsonDengueHotspotStorage.readDengueHotspotTracker(filePath).get();
        assertEquals(original, new DengueHotspotTracker(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonDengueHotspotStorage.saveDengueHotspotTracker(original, filePath);
        readBack = jsonDengueHotspotStorage.readDengueHotspotTracker(filePath).get();
        assertEquals(original, new DengueHotspotTracker(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonDengueHotspotStorage.saveDengueHotspotTracker(original); // file path not specified
        readBack = jsonDengueHotspotStorage.readDengueHotspotTracker().get(); // file path not specified
        assertEquals(original, new DengueHotspotTracker(readBack));

    }

    @Test
    public void saveDengueHotspotTracker_nullDengueHotspotTracker_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> saveDengueHotspotTracker(null, "SomeFile.json"));
    }

    /**
     * Saves {@code dengueHotspotTracker} at the specified {@code filePath}.
     */
    private void saveDengueHotspotTracker(ReadOnlyDengueHotspotTracker dengueHotspotTracker, String filePath) {
        try {
            new CsvDengueHotspotStorage(Paths.get(filePath))
                    .saveDengueHotspotTracker(dengueHotspotTracker, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveDengueHotspotTracker_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> saveDengueHotspotTracker(new DengueHotspotTracker(), null));
    }
}
