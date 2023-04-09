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
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.dengue.commons.exceptions.DataConversionException;
import seedu.dengue.model.DengueHotspotTracker;
import seedu.dengue.model.ReadOnlyDengueHotspotTracker;
import seedu.dengue.model.person.exceptions.PersonHasNullAttributesException;
import seedu.dengue.testutil.TypicalPersons;

public class CsvDengueHotspotTrackerStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test",
            "data", "CsvDengueHotspotTrackerStorageTest");

    private static final String TYPICAL_PERSONS_FILE = "typicalPersonsDengueHotspotTracker.csv";

    @TempDir
    public Path testFolder;

    @Test
    public void readDengueHotspotTracker_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readDengueHotspotTracker(null));
    }

    private Optional<ReadOnlyDengueHotspotTracker> readDengueHotspotTracker(String filePath)
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
    public void readDengueHotspotTracker_typicalPersonsFile_success() throws Exception {
        Optional<ReadOnlyDengueHotspotTracker> dataFromFile =
                readDengueHotspotTracker(TYPICAL_PERSONS_FILE);
        if (dataFromFile.isPresent()) {
            ReadOnlyDengueHotspotTracker dengueHotspotTrackerFromFile = dataFromFile.get();
            DengueHotspotTracker typicalPersonsDengueHotspotTracker = TypicalPersons.getTypicalDengueHotspotTracker();
            assertEquals(dengueHotspotTrackerFromFile, typicalPersonsDengueHotspotTracker);
        } else {
            assert false;
        }
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readDengueHotspotTracker("NonExistentFile.csv").isPresent());
    }

    @Test
    public void read_notCsvFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, ()
                -> readDengueHotspotTracker("notCsvFormatDengueHotspotTracker.csv"));
    }

    @Test
    public void read_notCorrectHeaders_exceptionThrown() {
        assertThrows(PersonHasNullAttributesException.class, ()
                -> readDengueHotspotTracker("notCorrectHeadersDengueHotspotTracker.csv"));
    }

    @Test
    public void readDengueHotspotTracker_invalidPersonDengueHotspotTracker_throwDataConversionException() {
        assertThrows(DataConversionException.class, ()
                -> readDengueHotspotTracker("invalidPersonDengueHotspotTracker.csv"));
    }

    @Test
    public void readDengueHotspotTracker_invalidAndValidPersonDengueHotspotTracker_throwDataConversionException() {
        assertThrows(DataConversionException.class, ()
                -> readDengueHotspotTracker("invalidAndValidPersonDengueHotspotTracker.csv"));
    }

    @Test
    public void readAndSaveDengueHotspotTracker_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempDengueHotspotTracker.csv");
        DengueHotspotTracker original = getTypicalDengueHotspotTracker();
        CsvDengueHotspotStorage csvDengueHotspotStorage = new CsvDengueHotspotStorage(filePath);

        // Save in new file and read back
        csvDengueHotspotStorage.saveDengueHotspotTracker(original, filePath);
        ReadOnlyDengueHotspotTracker readBack = csvDengueHotspotStorage.readDengueHotspotTracker(filePath).get();
        assertEquals(original, new DengueHotspotTracker(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        csvDengueHotspotStorage.saveDengueHotspotTracker(original, filePath);
        readBack = csvDengueHotspotStorage.readDengueHotspotTracker(filePath).get();
        assertEquals(original, new DengueHotspotTracker(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        csvDengueHotspotStorage.saveDengueHotspotTracker(original); // file path not specified
        readBack = csvDengueHotspotStorage.readDengueHotspotTracker().get(); // file path not specified
        assertEquals(original, new DengueHotspotTracker(readBack));
    }

    @Test
    public void saveDengueHotspotTracker_nullDengueHotspotTracker_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> saveDengueHotspotTracker(null, "SomeFile.csv"));
    }

    /**
     * Saves {@code dengueHotspotTracker} at the specified {@code filePath}.
     */
    private void saveDengueHotspotTracker(ReadOnlyDengueHotspotTracker dengueHotspotTracker, String filePath) {
        try {
            new CsvDengueHotspotStorage(Paths.get(filePath))
                    .saveDengueHotspotTracker(dengueHotspotTracker, addToTestDataPathIfNotNull(filePath));
        } catch (IOException e) {
            throw new AssertionError("There should not be an error writing to the file.", e);
        }
    }

    @Test
    public void saveDengueHotspotTracker_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> saveDengueHotspotTracker(new DengueHotspotTracker(), null));
    }
}
