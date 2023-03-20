package seedu.connectus.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.connectus.testutil.Assert.assertThrows;
import static seedu.connectus.testutil.TypicalPersons.ALICE;
import static seedu.connectus.testutil.TypicalPersons.HOON;
import static seedu.connectus.testutil.TypicalPersons.IDA;
import static seedu.connectus.testutil.TypicalPersons.getTypicalConnectUs;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.connectus.commons.exceptions.DataConversionException;
import seedu.connectus.model.ConnectUs;
import seedu.connectus.model.ReadOnlyConnectUs;

public class JsonConnectUsStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonConnectUsStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readConnectUs_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readConnectUs(null));
    }

    private java.util.Optional<ReadOnlyConnectUs> readConnectUs(String filePath) throws Exception {
        return new JsonConnectUsStorage(Paths.get(filePath)).readConnectUs(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readConnectUs("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readConnectUs("notJsonFormatConnectUs.json"));
    }

    @Test
    public void readConnectUs_invalidPersonConnectUs_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readConnectUs("invalidPersonConnectUs.json"));
    }

    @Test
    public void readConnectUs_invalidAndValidPersonConnectUs_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readConnectUs("invalidAndValidPersonConnectUs.json"));
    }

    @Test
    public void readAndSaveConnectUs_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempConnectUS.json");
        ConnectUs original = getTypicalConnectUs();
        JsonConnectUsStorage jsonConnectUsStorage = new JsonConnectUsStorage(filePath);

        // Save in new file and read back
        jsonConnectUsStorage.saveConnectUs(original, filePath);
        ReadOnlyConnectUs readBack = jsonConnectUsStorage.readConnectUs(filePath).get();
        assertEquals(original, new ConnectUs(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonConnectUsStorage.saveConnectUs(original, filePath);
        readBack = jsonConnectUsStorage.readConnectUs(filePath).get();
        assertEquals(original, new ConnectUs(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonConnectUsStorage.saveConnectUs(original); // file path not specified
        readBack = jsonConnectUsStorage.readConnectUs().get(); // file path not specified
        assertEquals(original, new ConnectUs(readBack));

    }

    @Test
    public void saveConnectUs_nullConnectUs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveConnectUs(null, "SomeFile.json"));
    }

    /**
     * Saves {@code connectUs} at the specified {@code filePath}.
     */
    private void saveConnectUs(ReadOnlyConnectUs connectUs, String filePath) {
        try {
            new JsonConnectUsStorage(Paths.get(filePath))
                    .saveConnectUs(connectUs, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveConnectUs_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveConnectUs(new ConnectUs(), null));
    }
}
